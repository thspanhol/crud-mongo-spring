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
        if (name == null || email == null || cep == null) {
            throw new UserNotFoundException("Favor informar nome, email e cep do usuário para cadastra-lo.");
        } else {
            long id = this.usuarioRepository.count() + 1;
            Usuario novoUsuario = new Usuario(String.valueOf(id), name, email, cep);
            this.usuarioRepository.insert(novoUsuario);
        }
    }

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
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            this.usuarioRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    public void editUser(String id, String name, String email, String cep) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            Usuario editUsuario = new Usuario(id, name, email, cep);
            this.usuarioRepository.save(editUsuario);
        } else {
            throw new UserNotFoundException("Usuário não encontrado com ID: " + id);
        }
    }

    public List<Usuario> findByName(String nome) {
        List<Usuario> usuario = this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
        return usuario;
    }
}
