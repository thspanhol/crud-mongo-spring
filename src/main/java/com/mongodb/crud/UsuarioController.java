package com.mongodb.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obterUsuarios(@RequestBody Usuario response) {
        System.out.println(response);
        System.out.println(response.getId());
        System.out.println(response.getNome());
        System.out.println(response.getEmail());
        System.out.println(response.getCep());
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obterUsuarioPeloId(@PathVariable String id) {
        Optional<Usuario> usuarioPeloId = usuarioService.findById(id);
        if (usuarioPeloId.isPresent()) {
            return usuarioPeloId;
        }
        return null;
    }

    @GetMapping("/nome={nome}")
    public List<Usuario> obterPeloNome(@PathVariable String nome) {
        return usuarioService.findByName(nome);
    }

    @PostMapping("/{nome}/{email}/{cep}")
    public void cadastrarUsuario(@PathVariable String nome, @PathVariable String email, @PathVariable String cep) {
        usuarioService.save(nome, email, cep);
    }

    @DeleteMapping("/{id}")
    public void deletaUsuario(@PathVariable String id) {
        usuarioService.deleteById(id);
    }

    @DeleteMapping("/ids={id}")
    public void deletaUsuariosPelosIds(@PathVariable String id) {
        String[] ids = id.split(",");
        Arrays.stream(ids).forEach(i -> usuarioService.deleteById(i));
    }

    @PutMapping("/{id}/{nome}/{email}/{cep}")
    public void alteraUsuario(@PathVariable String id, @PathVariable String nome, @PathVariable String email, @PathVariable String cep) {
        usuarioService.editUser(id, nome, email, cep);
    }
}
