package taskmasters.v1.authapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskmasters.v1.authapi.dto.UserDTO;
import taskmasters.v1.authapi.services.implementation.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController{
    @Autowired
    private UserService userService;
    @PostMapping
    private UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/admin")
    private String getAdmin(){
        return "Permissão de Administrador";
    }

    @GetMapping("/user")
    private String getUser(){
        return "Permissão de Usuário";
    }


}
