package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.LoginDto;
import com.senai.Mobili.Dtos.TokenDto;
import com.senai.Mobili.Models.ParceiroModel;
import com.senai.Mobili.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto dadosLogin) {
        System.out.println(dadosLogin);
        var usernamePassword = new UsernamePasswordAuthenticationToken(dadosLogin.email(), dadosLogin.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((ParceiroModel) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(token));
    }
}
