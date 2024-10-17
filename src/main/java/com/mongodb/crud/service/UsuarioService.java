package com.mongodb.crud.service;

import com.mongodb.crud.config.UserNotFoundException;
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

    public void save(String name, String email, String cep) {
        //TODO: Essa validação deve ser feita por meio do Spring Validator: https://medium.com/@himani.prasad016/validations-in-spring-boot-e9948aa6286b
        if (name == null || email == null || cep == null) {
            throw new UserNotFoundException("Favor informar nome, email e cep do usuário para cadastra-lo.");
        } else {
            //TODO: O id é criado pelo próprio repositório, não sendo necessário popular
            long id = this.usuarioRepository.count() + 1;
            Usuario novoUsuario = new Usuario(String.valueOf(id), name, email, cep);
            this.usuarioRepository.insert(novoUsuario);
        }
    }

    //TODO: Remover métodos não utilizados
    public long count() {
        return this.usuarioRepository.count();
    }

    public Usuario findById(String id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com ID: " + id));
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public void deleteById(String id) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            this.usuarioRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    public void editUser(String id, String name, String email, String cep) {
        //TODO: Reescreve essa lógica usando o Optional retornado pelo findById() e encadeando .map() e .orElseThrow()
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario editUsuario = new Usuario(id, name, email, cep);
            this.usuarioRepository.save(editUsuario);
        } else {
            throw new UserNotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    public List<Usuario> findByName(String nome) {
        //TODO: a variável usuario é redundante. Pode retornar direto o resultado do repository
        List<Usuario> usuario = this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
        return usuario;
    }
}
