package com.mongodb.crud.dto;

//TODO: Usa o @Builder do lombok
// Feito (Na classe Usuario)
public record UserDTO(
        String id,
        String name,
        String email,
        String cep
) {
}
