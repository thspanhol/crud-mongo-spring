package com.mongodb.crud.controller;

import com.mongodb.crud.model.CookieClass;
import com.mongodb.crud.service.UserService;
import com.mongodb.crud.dto.UserDTO;
import com.mongodb.crud.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    //TODO: A injeção via autowired é antiga e não mais recomendada
    // Feito

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO: Como tu já tá dentro do contexto de Usuario, fica redundante repetir "Usuarios". Ex: getAll()
    //TODO: Prioriza sempre usar inglês - se for pra escrever em português, coloca tudo em português
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    //TODO: ResponseEntity é uma maneira antiga e defasada que não vaz mais sentido. Responde sempre o próprio objeto
    // Se precisar mudar o verbo de resposta, usa o @ResponseStatus()
    // Feito
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    //TODO: Cria uma classe pros ExceptionHandlers - nunca na controller
    // Feito

    //TODO: @PathVariable é quando a variavel faz parte da url. Ex: /users/123/details
    // @RequestParam não é declarado na url. Ex: @RequestParam("nome") String nome
    // Nesse caso de buscar pelo nome, o correto é usar o @RequestParam
    // Feito
    @GetMapping("/name")
    public List<User> findByName(@RequestParam("name") String nome) {
        return userService.findByName(nome);
    }

    @GetMapping("/last")
    public User findLastSearch() {
        return userService.findById(CookieClass.getCookie());
    }

    //TODO: "response" como nome da variavel tá estranho.
    // Envia o objeto inteiro pra dentro da service. Ex: usuarioService.save(request)
    // Feito
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserDTO usuario) {
        userService.save(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.deleteBySingleId(id);
    }

    //TODO: Evita qualquer tipo de lógica dentro da controller, loops, ifs.
    // Envia a lista inteira de ids pra service e ela se encarrega do resto.
    // Feito
    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIdsList(@RequestParam("list") List<String> ids) {
        userService.deleteByIdsList(ids);
    }

    //TODO: Envia o objeto inteiro pra dentro da service. Ex: usuarioService.editUser(id, request)
    // Feito
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserDTO usuario, @PathVariable String id) {
        userService.editUser(id, usuario);
    }
}
