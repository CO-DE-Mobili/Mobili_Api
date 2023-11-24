package com.senai.Mobili.Controllers;


import com.senai.Mobili.Models.UsuarioModel;
import com.senai.Mobili.Repositories.ParceiroRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/parceiros", produces = {"application/json"})
public class ParceiroController {
    @Autowired
    ParceiroRepositories parceiroRepositories;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepositories.findAll());
    }

}
