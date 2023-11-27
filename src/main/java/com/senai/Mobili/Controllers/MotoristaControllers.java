package com.senai.Mobili.Controllers;

import com.senai.Mobili.Models.MotoristaModel2;
import com.senai.Mobili.Repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/motorista", produces = {"application/json"})
public class MotoristaControllers {

    @Autowired
    MotoristaRepository motoristaRepository;

    @GetMapping
    public ResponseEntity<List<MotoristaModel2>> listarMotorista(){
        return ResponseEntity.status(HttpStatus.OK).body(motoristaRepository.findAll());
    }


}
