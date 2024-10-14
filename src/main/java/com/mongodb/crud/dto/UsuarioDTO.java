package com.mongodb.crud.dto;

public record UsuarioDTO(
        String id,
        String nome,
        String email,
        String cep
) {
}
