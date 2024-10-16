package com.mongodb.crud;

import com.mongodb.crud.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> obterUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPeloId(@PathVariable String id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/nome={nome}")
    public List<Usuario> obterPeloNome(@PathVariable String nome) {
        return usuarioService.findByName(nome);
    }

    @GetMapping("/last")
    public Usuario obterUltimo() {
        return usuarioService.findById(CookieService.getCookie());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody UsuarioDTO response) {
        usuarioService.save(response.nome(), response.email(), response.cep());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuario(@PathVariable String id) {
        usuarioService.deleteById(id);
    }

    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuariosPelosIds(@RequestParam("lista") List<String> ids) {
        ids.forEach(id -> usuarioService.deleteById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteraUsuario(@RequestBody UsuarioDTO usuario, @PathVariable String id) {
        usuarioService.editUser(id, usuario.nome(), usuario.email(), usuario.cep());
    }
}
