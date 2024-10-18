package com.mongodb.crud.repository;

import com.mongodb.crud.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

}
