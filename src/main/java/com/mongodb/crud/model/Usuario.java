package com.mongodb.crud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
