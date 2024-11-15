package com.quetionpro.grocerystore.service;

import com.quetionpro.grocerystore.dtos.CreateOrder;
import com.quetionpro.grocerystore.dtos.OrderEvent;
import com.quetionpro.grocerystore.dtos.OrderItemDTO;
import com.quetionpro.grocerystore.entitites.Order;
import com.quetionpro.grocerystore.entitites.OrderItem;
import com.quetionpro.grocerystore.entitites.Product;
import com.quetionpro.grocerystore.interfaces.Observer;
import com.quetionpro.grocerystore.interfaces.OrderService;
import com.quetionpro.grocerystore.interfaces.ProductService;
import com.quetionpro.grocerystore.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final List<Observer> observers;

    OrderServiceImpl(ProductService productService,
                     OrderRepository orderRepository) {

        this.productService = productService;
        this.orderRepository = orderRepository;
        this.observers = Arrays.asList(productService);
    }

    @Override
    @Transactional
    public Order createOrder(CreateOrder order) {
        // Fetch available items and create a map of productId to Product
        Map<Integer, Product> availableItems = productService.getAvailableProducts()
                .stream()
                .collect(Collectors.toMap(
                        Product::getProductId,
                        product -> product
                ));

        // Filter valid order items and calculate the total price in a single pass
        List<OrderItem> validOrderItems = new ArrayList<>();
        List<Product> orderedProducts = new ArrayList<>();
        double totalPrice = 0.0;

        for (OrderItemDTO orderItem : order.getOrderItems()) {
            Product product = availableItems.get(orderItem.getGroceryItemId());
            if (product != null && product.getInventoryLevel() >= orderItem.getQuantity()) {
                OrderItem newOrderItem = mapOrderItemFromDto(product, orderItem);
                validOrderItems.add(newOrderItem);
                orderedProducts.add(product);
                totalPrice = Double.sum(totalPrice, product.getProductPrice());
            }
        }

        // Create the new Order
        Order newOrder = new Order();
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setOrderItems(validOrderItems); // Assuming Order has a setter for orderItems
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setTotalPrice(totalPrice);

        notifyAllObservers(new OrderEvent(validOrderItems, orderedProducts));
        orderRepository.save(newOrder);

        return newOrder;
    }

    private OrderItem mapOrderItemFromDto(Product Product, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDTO.getGroceryItemId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(Product.getProductPrice() * orderItem.getQuantity());
        return orderItem;
    }


    //observer pattern can be implemented here to update Product Repo or for notifications
    private void notifyAllObservers(OrderEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    private void addObservers(Observer observer) {
        observers.add(observer);
    }

}
