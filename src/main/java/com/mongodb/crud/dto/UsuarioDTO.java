package com.mongodb.crud.dto;

//TODO: Usa o @Builder do lombok
// Feito (Na classe Usuario)
public record UsuarioDTO(
        String id,
        String nome,
        String email,
        String cep
) {
}
