package com.mongodb.crud.dto;

import com.mongodb.crud.repository.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

public record UserDTO(
        @NotBlank(message = "Necessário informar o nome.")
        @Pattern(regexp = "^[A-Z][a-z]+( [a-z]{1,2})?( [A-Z][a-z]+)+$", message = "Nomes e sobrenomes devem começar com letras maiusculas, podendo conter apenas 'de' ou 'da' iniciando com minusculas e com apenas um espaço entre os nomes.")
        String name,
        @NotBlank(message = "Necessário informar o email.")
        @Email(message = "O email deve ser válido")
        String email,
        @NotBlank(message = "Necessário informar o cep.")
        @Pattern(regexp = "^\\d{8}$", message = "O CEP deve ter 8 dígitos numéricos")
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
