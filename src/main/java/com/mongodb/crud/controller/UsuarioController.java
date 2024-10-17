package com.mongodb.crud.controller;

import com.mongodb.crud.model.CookieService;
import com.mongodb.crud.config.UserNotFoundException;
import com.mongodb.crud.service.UsuarioService;
import com.mongodb.crud.dto.UsuarioDTO;
import com.mongodb.crud.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    //TODO: A injeção via autowired é antiga e não mais recomendada
    @Autowired
    private UsuarioService usuarioService;

    //TODO: Como tu já tá dentro do contexto de Usuario, fica redundante repetir "Usuarios". Ex: getAll()
    //TODO: Prioriza sempre usar inglês - se for pra escrever em português, coloca tudo em português
    @GetMapping
    public List<Usuario> obterUsuarios() {
        return usuarioService.findAll();
    }

    //TODO: ResponseEntity é uma maneira antiga e defasada que não vaz mais sentido. Responde sempre o próprio objeto
    // Se precisar mudar o verbo de resposta, usa o @ResponseStatus()
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPeloId(@PathVariable String id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    //TODO: Cria uma classe pros ExceptionHandlers - nunca na controller
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //TODO: @PathVariable é quando a variavel faz parte da url. Ex: /users/123/details
    // @RequestParam não é declarado na url. Ex: @RequestParam("nome") String nome
    // Nesse caso de buscar pelo nome, o correto é usar o @RequestParam
    @GetMapping("/nome={nome}")
    public List<Usuario> obterPeloNome(@PathVariable String nome) {
        return usuarioService.findByName(nome);
    }

    @GetMapping("/last")
    public Usuario obterUltimo() {
        return usuarioService.findById(CookieService.getCookie());
    }

    //TODO: "response" como nome da variavel tá estranho.
    // Envia o objeto inteiro pra dentro da service. Ex: usuarioService.save(request)
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

    //TODO: Evita qualquer tipo de lógica dentro da controller, loops, ifs.
    // Envia a lista inteira de ids pra service e ela se encarrega do resto.
    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuariosPelosIds(@RequestParam("lista") List<String> ids) {
        ids.forEach(id -> usuarioService.deleteById(id));
    }

    //TODO: Envia o objeto inteiro pra dentro da service. Ex: usuarioService.editUser(id, request)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteraUsuario(@RequestBody UsuarioDTO usuario, @PathVariable String id) {
        usuarioService.editUser(id, usuario.nome(), usuario.email(), usuario.cep());
    }
}
