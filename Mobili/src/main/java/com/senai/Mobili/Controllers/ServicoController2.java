package com.senai.Mobili.Controllers;

import com.senai.Mobili.Models.ServicoModel2;
import com.senai.Mobili.Repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/servico", produces = {"application/json"})
public class ServicoController2 {

    @Autowired
    ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<ServicoModel2>> ListarServico(){
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.findAll());
    }

}