package com.mongodb.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void save(String name, String email, String cep) {
        long id = this.usuarioRepository.count() + 1;
        Usuario novoUsuario = new Usuario(String.valueOf(id), name, email, cep);
        this.usuarioRepository.save(novoUsuario);
    }

    public long count() {
        return this.usuarioRepository.count();
    }

    public Optional<Usuario> findById(String id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario;
        }
        return null;
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    public void deleteById(String id) {
        this.usuarioRepository.deleteById(id);
    }

    public void editUser(String id, String name, String email, String cep) {
        Usuario editUsuario = new Usuario(id, name, email, cep);
        this.usuarioRepository.save(editUsuario);
    }

    public List<Usuario> findByName(String nome) {
        List<Usuario> usuario = this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
        return usuario;
    }



    }
