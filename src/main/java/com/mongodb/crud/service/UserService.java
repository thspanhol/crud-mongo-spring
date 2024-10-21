package com.mongodb.crud.service;

import com.mongodb.crud.config.UserNotFoundException;
import com.mongodb.crud.dto.UserDTO;
import com.mongodb.crud.model.User;
import com.mongodb.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(UserDTO user) {
        //TODO: Essa validação deve ser feita por meio do Spring Validator: https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b
//        if (usuario.nome() == null || usuario.email() == null || usuario.cep() == null) {
//            throw new UserNotFoundException("Favor informar nome, email e cep do usuário para cadastra-lo.");
//        } else {
        //TODO: O id é criado pelo próprio repositório, não sendo necessário popular
        // ---- Mongo repository cria obj id por padrão
        List<User> users = userRepository.findAll();
        long id = Integer.valueOf(users.getLast().getId()) + 1;
        User novoUsuario = User.builder()
                .id(String.valueOf(id))
                .name(user.name())
                .email(user.email())
                .cep(user.cep())
                .build();
        this.userRepository.insert(novoUsuario);
        //}
    }

    //TODO: Remover métodos não utilizados
    // Feito

    public User findById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void deleteBySingleId(String id) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        // Feito
        this.userRepository.findById(id)
                .map(usuario -> {
                    this.userRepository.deleteById(id);
                    return usuario;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public void deleteByIdsList(List<String> ids) {
        ids.forEach(this::deleteBySingleId);
    }

    public void editUser(String id, UserDTO user) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        // Feito
        User editUsuario = new User(id, user.name(), user.email(), user.cep());
        this.userRepository.findById(id)
                .map(u -> {
                    this.userRepository.save(editUsuario);
                    return u;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
        ;
    }

    public List<User> findByName(String name) {
        //TODO: a variável usuario é redundante. Pode retornar direto o resultado do repository
        // Feito
        return this.userRepository.findByNameContainingIgnoreCase(name);
    }
}
