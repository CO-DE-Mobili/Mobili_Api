package com.senai.Mobili.Controllers;


import com.senai.Mobili.Dtos.ParceiroDto;
import com.senai.Mobili.Models.ParceiroModel;
import com.senai.Mobili.Repositories.ParceiroRepository;
import com.senai.Mobili.Services.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/parceiro", produces = {"application/json"})
public class ParceiroController {
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ParceiroRepository parceiroRepository;

    @GetMapping
    public ResponseEntity<List<ParceiroModel>> listarParceiros(){
        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarUsuarioId(@PathVariable(value = "idUsuario")UUID id){
        Optional<ParceiroModel> usuarioBuscado = parceiroRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastraUsuario(@RequestBody @Valid ParceiroDto dadosRecebidos){
        if(parceiroRepository.findByEmail(dadosRecebidos.email()) != null ){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");

        }
       ParceiroModel parceiroModel = new ParceiroModel();
        BeanUtils.copyProperties(dadosRecebidos,parceiroModel);

        String senhaCript = new BCryptPasswordEncoder().encode(dadosRecebidos.senha());
        parceiroModel.setSenha(senhaCript);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(dadosRecebidos.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        parceiroModel.setUrlImg(urlimg);

        return ResponseEntity.status(HttpStatus.CREATED).body(parceiroRepository.save(parceiroModel));
    }

    @PutMapping(value = "/{idParceiro}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarParceiro(@PathVariable(value = "idParceiro")UUID id,@ModelAttribute @Valid ParceiroDto parceiroDto){

        Optional<ParceiroModel> parceiroBuscado = parceiroRepository.findById(id);

        if (parceiroBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        ParceiroModel parceiroModel = parceiroBuscado.get();
        BeanUtils.copyProperties(parceiroDto,parceiroModel);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(parceiroDto.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        parceiroModel.setUrlImg(urlimg);


        return ResponseEntity.status(HttpStatus.OK).body(parceiroRepository.save(parceiroModel));
    }

    @DeleteMapping("/{idParceiro}")
    public ResponseEntity<Object> deletarParceiro(@PathVariable(value = "idParceiro")UUID id){
        Optional<ParceiroModel> parceiroBuscado = parceiroRepository.findById(id);

        if (parceiroBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
       parceiroRepository.delete(parceiroBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parceiro deletado com sucesso");
    }
}
