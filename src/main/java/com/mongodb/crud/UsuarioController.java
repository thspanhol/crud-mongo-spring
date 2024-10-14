package com.mongodb.crud;

import com.mongodb.crud.dto.UsuarioDTO;
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
    public List<Usuario> obterUsuarios(@RequestBody UsuarioDTO response) {
        System.out.println(response);
        System.out.println(response.id());
        System.out.println(response.nome());
        System.out.println(response.email());
        System.out.println(response.cep());
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

//    @GetMapping("/nome={nome}")
//    public List<Usuario> obterPeloNome(@PathVariable String nome) {
//        return usuarioService.findByName(nome);
//    }

    @GetMapping("/nome")
    public List<Usuario> obterPeloNome(@RequestBody String nome) {
        return usuarioService.findByName(nome);
    }

//    @PostMapping("/{nome}/{email}/{cep}")
//    public void cadastrarUsuario(@PathVariable String nome, @PathVariable String email, @PathVariable String cep) {
//        usuarioService.save(nome, email, cep);
//    }

    @PostMapping
    public void cadastrarUsuario(@RequestBody UsuarioDTO response) {
        usuarioService.save(response.nome(), response.email(), response.cep());
    }

//    @DeleteMapping("/{id}")
//    public void deletaUsuario(@PathVariable String id) {
//        usuarioService.deleteById(id);
//    }

    @DeleteMapping
    public void deletaUsuario(@RequestBody String id) {
        usuarioService.deleteById(id);
    }

//    @DeleteMapping("/ids={id}")
//    public void deletaUsuariosPelosIds(@PathVariable String id) {
//        String[] ids = id.split(",");
//        Arrays.stream(ids).forEach(i -> usuarioService.deleteById(i));
//    }

    @DeleteMapping("/ids")
    public void deletaUsuariosPelosIds(@RequestBody String ids) {
        String[] stringIds = ids.split(",");
        Arrays.stream(stringIds).forEach(i -> usuarioService.deleteById(i));
    }

//    @PutMapping("/{id}/{nome}/{email}/{cep}")
//    public void alteraUsuario(@PathVariable String id, @PathVariable String nome, @PathVariable String email, @PathVariable String cep) {
//        usuarioService.editUser(id, nome, email, cep);
//    }

    @PutMapping
    public void alteraUsuario(@RequestBody UsuarioDTO usuario) {
        usuarioService.editUser(usuario.id(), usuario.nome(), usuario.email(), usuario.cep());
    }
}
