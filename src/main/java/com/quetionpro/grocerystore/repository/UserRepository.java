package com.quetionpro.grocerystore.repository;

import com.quetionpro.grocerystore.entitites.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,String> {

    Optional<User> findByEmail(String email);


}
