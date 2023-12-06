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
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<Object> criarEmpresa(@RequestBody @Valid EmpresaDto empresaDto) {
        EmpresaModel novaEmpresa;
        if (empresaRepository.findByCnpj(empresaDto.cnpj()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        novaEmpresa = new EmpresaModel();
        BeanUtils.copyProperties(empresaDto, novaEmpresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaRepository.save(novaEmpresa));
    }


    @PutMapping("/{id}") // id que vai ser passado na url
    public ResponseEntity<Object> editarEmpresa(@PathVariable(value = "id") UUID id, @RequestBody @Valid EmpresaDto empresaDto) {
        Optional<EmpresaModel> empresaBuscado = empresaRepository.findById(id);
        if (empresaBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }
        EmpresaModel empresaModel = empresaBuscado.get();
        BeanUtils.copyProperties(empresaDto, empresaModel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(empresaRepository.save(empresaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarEmpresa(@PathVariable(value = "id") UUID id, @RequestBody @Valid EmpresaDto empresaDto) {
        Optional<EmpresaModel> empresaBuscado = empresaRepository.findById(id);
        if (empresaBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }
        empresaRepository.delete(empresaBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Empresa deletada com sucesso!");
    }


    @GetMapping("/{id}")

    public ResponseEntity<Object> buscarEmpresa(@PathVariable(value = "id") UUID id){
        Optional<EmpresaModel> empresaBuscado = empresaRepository.findById(id);
        if (empresaBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(empresaBuscado.get());
    }
}