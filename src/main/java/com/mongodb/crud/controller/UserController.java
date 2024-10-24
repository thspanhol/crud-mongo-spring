package com.mongodb.crud.controller;

import com.mongodb.crud.service.CookieService;
import com.mongodb.crud.service.UserService;
import com.mongodb.crud.dto.UserDTO;
import com.mongodb.crud.repository.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping
    public List<UserEntity> findAll(@RequestParam(value = "name", required = false) String name) {
        System.out.println(name);
        if (name != null) {
            return userService.findByName(name);
        }
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/last")
    public UserEntity findLastSearch() {
        return userService.findById(cookieService.getCookie());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody UserDTO user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.deleteBySingleId(id);
    }

    @DeleteMapping("/ids")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByIdsList(@RequestParam("list") List<String> ids) {
        userService.deleteByIdsList(ids);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDTO usuario, @PathVariable String id) {
        userService.editUser(id, usuario);
    }
}
