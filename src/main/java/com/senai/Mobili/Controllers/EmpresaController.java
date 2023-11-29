package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.EmpresaDto;
import com.senai.Mobili.Models.EmpresaModel;
import com.senai.Mobili.Repositories.EmpresaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid EmpresaDto usuarioDto) {
        EmpresaModel novaEmpresa;

        if (empresaRepository.findByCnpj(usuarioDto.cnpj()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        novaEmpresa = new EmpresaModel();
        BeanUtils.copyProperties(usuarioDto, novaEmpresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaRepository.save(novaEmpresa));
    }
}
