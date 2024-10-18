package com.mongodb.crud.controller;

import com.mongodb.crud.config.CookieInterceptor;
import com.mongodb.crud.model.CookieClass;
import com.mongodb.crud.service.UsuarioService;
import com.mongodb.crud.dto.UsuarioDTO;
import com.mongodb.crud.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    //TODO: A injeção via autowired é antiga e não mais recomendada
    // Feito

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //TODO: Como tu já tá dentro do contexto de Usuario, fica redundante repetir "Usuarios". Ex: getAll()
    //TODO: Prioriza sempre usar inglês - se for pra escrever em português, coloca tudo em português
    @GetMapping
    public List<Usuario> obterUsuarios() {
        return usuarioService.findAll();
    }

    //TODO: ResponseEntity é uma maneira antiga e defasada que não vaz mais sentido. Responde sempre o próprio objeto
    // Se precisar mudar o verbo de resposta, usa o @ResponseStatus()
    // Feito
    @GetMapping("/{id}")
    public Usuario obterUsuarioPeloId(@PathVariable String id) {
        return usuarioService.findById(id);
    }

    //TODO: Cria uma classe pros ExceptionHandlers - nunca na controller
    // Feito

    //TODO: @PathVariable é quando a variavel faz parte da url. Ex: /users/123/details
    // @RequestParam não é declarado na url. Ex: @RequestParam("nome") String nome
    // Nesse caso de buscar pelo nome, o correto é usar o @RequestParam
    // Feito
    @GetMapping("/nome")
    public List<Usuario> obterPeloNome(@RequestParam("nome") String nome) {
        return usuarioService.findByName(nome);
    }

    @GetMapping("/last")
    public Usuario obterUltimo() {
        return usuarioService.findById(CookieClass.getCookie());
    }

    //TODO: "response" como nome da variavel tá estranho.
    // Envia o objeto inteiro pra dentro da service. Ex: usuarioService.save(request)
    // Feito
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody UsuarioDTO usuario) {
        usuarioService.save(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuario(@PathVariable String id) {
        usuarioService.deleteBySingleId(id);
    }

    //TODO: Evita qualquer tipo de lógica dentro da controller, loops, ifs.
    // Envia a lista inteira de ids pra service e ela se encarrega do resto.
    // Feito
    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaUsuariosPelosIds(@RequestParam("lista") List<String> ids) {
        usuarioService.deleteByIdsList(ids);
    }

    //TODO: Envia o objeto inteiro pra dentro da service. Ex: usuarioService.editUser(id, request)
    // Feito
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alteraUsuario(@RequestBody UsuarioDTO usuario, @PathVariable String id) {
        usuarioService.editUser(id, usuario);
    }
}
