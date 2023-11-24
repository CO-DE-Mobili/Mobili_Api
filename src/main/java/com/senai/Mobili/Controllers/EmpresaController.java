package com.senai.Mobili.Controllers;

import com.senai.Mobili.Models.EmpresaModel;
import com.senai.Mobili.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/empresas", produces = {"application/json"})
public class EmpresaController {
    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping
    public ResponseEntity<List<EmpresaModel>> listarEmpresas() {
        return ResponseEntity.status(HttpStatus.OK).body(empresaRepository.findAll());
    }
}
