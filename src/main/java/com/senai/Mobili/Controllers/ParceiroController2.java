package com.senai.Mobili.Controllers;


import com.senai.Mobili.Models.ParceiroModel2;
import com.senai.Mobili.Repositories.ParceiroRepositories2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/parceiro", produces = {"application/json"})
public class ParceiroController2 {

    @Autowired
    ParceiroRepositories2 parceiroRepositories2;

    @GetMapping
    public ResponseEntity<List<ParceiroModel2>> listarParceiros(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepositories2.findAll());
    }
}
