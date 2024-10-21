package com.mongodb.crud.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
//@RequiredArgsConstructor
//@Document(collation = "nova-colecao")
public class User {
    private String id;
     private String name;
     private String email;
     private String cep;

    public User(String id, String nome, String email, String cep) {
        this.id = id;
        this.name = nome;
        this.email = email;
        this.cep = cep;
    }
}
