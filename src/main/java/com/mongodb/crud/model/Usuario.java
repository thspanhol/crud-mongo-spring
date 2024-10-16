package com.mongodb.crud.model;

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

    public Usuario(String nome, String email, String cep) {
        this.nome = nome;
        this.email = email;
        this.cep = cep;
    }

    public Usuario() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
