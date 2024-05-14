package taskmasters.v1.authapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import taskmasters.v1.authapi.dto.AuthDTO;
import taskmasters.v1.authapi.services.implementation.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authenticationService;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String auth(@RequestBody AuthDTO authDTO){

        var userAuthenticationToken = new UsernamePasswordAuthenticationToken(authDTO.login(), authDTO.password());
        authenticationManager.authenticate(userAuthenticationToken);
        return authenticationService.getToken(authDTO);
    }
}
