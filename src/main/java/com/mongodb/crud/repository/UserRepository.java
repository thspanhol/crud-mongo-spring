package com.mongodb.crud.repository;

import com.mongodb.crud.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByNameContainingIgnoreCase(String nome);

}
