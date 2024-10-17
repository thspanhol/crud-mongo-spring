package com.mongodb.crud.service;

import com.mongodb.crud.config.UserNotFoundException;
import com.mongodb.crud.dto.UsuarioDTO;
import com.mongodb.crud.model.Usuario;
import com.mongodb.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void save(UsuarioDTO usuario) {
        //TODO: Essa validação deve ser feita por meio do Spring Validator: https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b
//        if (usuario.nome() == null || usuario.email() == null || usuario.cep() == null) {
//            throw new UserNotFoundException("Favor informar nome, email e cep do usuário para cadastra-lo.");
//        } else {
            //TODO: O id é criado pelo próprio repositório, não sendo necessário popular
            // ---- Mongo repository cria obj id por padrão
            long id = this.usuarioRepository.count() + 1;
            Usuario novoUsuario = new Usuario(String.valueOf(id), usuario.nome(), usuario.email(), usuario.cep());
            this.usuarioRepository.insert(novoUsuario);
        //}
    }

    //TODO: Remover métodos não utilizados
    // Feito

    public Usuario findById(String id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public void deleteBySingleId(String id) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        // Feito
        this.usuarioRepository.findById(id)
                .map(usuario -> {
                    this.usuarioRepository.deleteById(id);
                    return usuario;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public void deleteByIdsList(List<String> ids) {
        ids.forEach(this::deleteBySingleId);
    }

    public void editUser(String id, UsuarioDTO usuario) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        // Feito
        Usuario editUsuario = new Usuario(id, usuario.nome(), usuario.email(), usuario.cep());
        this.usuarioRepository.findById(id)
                .map(user -> {
                    this.usuarioRepository.save(editUsuario);
                    return user;
                })
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));;
    }

    public List<Usuario> findByName(String nome) {
        //TODO: a variável usuario é redundante. Pode retornar direto o resultado do repository
        // Feito
        return this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
