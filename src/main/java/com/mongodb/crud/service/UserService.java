package com.mongodb.crud.service;

import com.mongodb.crud.config.exceptions.UserNotFoundException;
import com.mongodb.crud.dto.UserDTO;
import com.mongodb.crud.repository.UserEntity;
import com.mongodb.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDTO user) {
        this.userRepository.insert(user.retornaUser());
    }

    public UserEntity findById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public void deleteBySingleId(String id) {
        this.userRepository.findById(id)
                .map(usuario -> {
                    this.userRepository.deleteById(id);
                    return usuario;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public void deleteByIdsList(List<String> ids) {
        this.userRepository.deleteAllById(ids);
    }

    public void editUser(String id, UserDTO user) {
        this.userRepository.findById(id)
                //.ifPresentOrElse(u -> this.userRepository.save(user.retornaUser(id)), () -> {
                //    throw new UserNotFoundException("Usuário não encontrado com ID: " + id);
                // });
                .map(u -> this.userRepository.save(user.retornaUser(id)))
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));

    }

    public List<UserEntity> findByName(String name) {
        return this.userRepository.findByNameContainingIgnoreCase(name);
    }
}
