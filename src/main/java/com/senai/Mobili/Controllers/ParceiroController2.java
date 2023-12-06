package com.senai.Mobili.Controllers;


import com.senai.Mobili.Dtos.ParceiroDto;
import com.senai.Mobili.Models.ParceiroModel2;
import com.senai.Mobili.Repositories.ParceiroRepositories2;
import com.senai.Mobili.Services.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/parceiro", produces = {"application/json"})
public class ParceiroController2 {


    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ParceiroRepositories2 parceiroRepositories2;

    @GetMapping
    public ResponseEntity<List<ParceiroModel2>> listarParceiros(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepositories2.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarUsuarioId(@PathVariable(value = "idUsuario")UUID id){
        Optional<ParceiroModel2> usuarioBuscado = parceiroRepositories2.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> cadastraUsuario(@ModelAttribute @Valid ParceiroDto dadosRecebidos){
        if(parceiroRepositories2.findByEmail(dadosRecebidos.email()) != null ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");

        }
       ParceiroModel2 parceiroModel2 = new ParceiroModel2();
        BeanUtils.copyProperties(dadosRecebidos,parceiroModel2);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(dadosRecebidos.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        parceiroModel2.setUrlImg(urlimg);

        return ResponseEntity.status(HttpStatus.CREATED).body(parceiroRepositories2.save(parceiroModel2));
    }

    @PutMapping(value = "/{idParceiro}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarParceiro(@PathVariable(value = "idParceiro")UUID id,@ModelAttribute @Valid ParceiroDto parceiroDto){

        Optional<ParceiroModel2> parceiroBuscado = parceiroRepositories2.findById(id);

        if (parceiroBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        ParceiroModel2 parceiroModel2 = parceiroBuscado.get();
        BeanUtils.copyProperties(parceiroDto,parceiroModel2);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(parceiroDto.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        parceiroModel2.setUrlImg(urlimg);


        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepositories2.save(parceiroModel2));
    }

    @DeleteMapping("/{idParceiro}")
    public ResponseEntity<Object> deletarParceiro(@PathVariable(value = "idParceiro")UUID id){
        Optional<ParceiroModel2> parceiroBuscado = parceiroRepositories2.findById(id);

        if (parceiroBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
       parceiroRepositories2.delete(parceiroBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parceiro deletado com sucesso");
    }
}
