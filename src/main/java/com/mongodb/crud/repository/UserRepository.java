package com.mongodb.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    List<UserEntity> findByNameContainingIgnoreCase(String nome);

}
