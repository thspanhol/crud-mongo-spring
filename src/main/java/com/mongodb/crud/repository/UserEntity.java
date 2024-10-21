package com.mongodb.crud.repository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    //@Indexed(unique = true)
    private String name;
    private String email;
    private String cep;
}
