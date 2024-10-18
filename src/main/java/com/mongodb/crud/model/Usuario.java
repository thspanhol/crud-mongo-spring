package com.mongodb.crud.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@Builder
//@RequiredArgsConstructor
//@Document(collation = "nova-colecao")
public class Usuario {
    private String id;
     private String nome;
     private String email;
     private String cep;

    public Usuario(String id, String nome, String email, String cep) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }
}
