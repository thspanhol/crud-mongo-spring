package com.mongodb.crud.dto;

import com.mongodb.crud.repository.UserEntity;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank(message = "Necessário informar o nome.")
        String name,
        @NotBlank(message = "Necessário informar o email.")
        String email,
        @NotBlank(message = "Necessário informar o cep.")
        String cep
) {
        public UserEntity retornaUser() {
                return UserEntity.builder()
                        .name(this.name())
                        .email(this.email())
                        .cep(this.cep())
                        .build();
        }

        public UserEntity retornaUser(String id) {
                return UserEntity.builder()
                        .id(id)
                        .name(this.name())
                        .email(this.email())
                        .cep(this.cep())
                        .build();
        }
}
