package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.MotoristaDto;
import com.senai.Mobili.Dtos.ParceiroDto;
import com.senai.Mobili.Models.MotoristaModel;
import com.senai.Mobili.Repositories.MotoristaRepository;
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
@RequestMapping(value = "/motorista", produces = {"application/json"})
public class MotoristaController {
    @Autowired
    MotoristaRepository motoristaRepository;

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping
    public ResponseEntity<List<MotoristaModel>> listarMotoristas(){
        return ResponseEntity.status(HttpStatus.OK).body(motoristaRepository.findAll());
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Object> buscarUsuarioId(@PathVariable(value = "idUsuario") UUID id){
        Optional<MotoristaModel> usuarioBuscado = motoristaRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarMotorista(@RequestBody @Valid ParceiroDto dadosRecebidos){
        if(motoristaRepository.findByEmail(dadosRecebidos.email()) != null ){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");

        }
        MotoristaModel motoristaModel = new MotoristaModel();
        BeanUtils.copyProperties(dadosRecebidos,motoristaModel);

        String senhaCript = new BCryptPasswordEncoder().encode(dadosRecebidos.senha());
        motoristaModel.setSenha(senhaCript);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(dadosRecebidos.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        motoristaModel.setUrlImg(urlimg);

        return ResponseEntity.status(HttpStatus.CREATED).body(motoristaRepository.save(motoristaModel));
    }

    @PutMapping(value = "/{idMotorista}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarMotorista(@PathVariable(value = "idMotorista")UUID id,@ModelAttribute @Valid MotoristaDto motoristaDto){

        Optional<MotoristaModel> motoristaBuscado = motoristaRepository.findById(id);

        if (motoristaBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        MotoristaModel motoristaModel = motoristaBuscado.get();
        BeanUtils.copyProperties(motoristaDto,motoristaModel);

        String urlimg;
        try{

            urlimg = fileUploadService.fazerUpload(motoristaDto.img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        motoristaModel.setUrlImg(urlimg);


        return ResponseEntity.status(HttpStatus.OK).body(motoristaRepository.save(motoristaModel));
    }

    @DeleteMapping("/{idMotorista}")
    public ResponseEntity<Object> deletarMotorista(@PathVariable(value = "idMotorista")UUID id){
        Optional<MotoristaModel> motoristaBuscado = motoristaRepository.findById(id);

        if (motoristaBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }
        motoristaRepository.delete(motoristaBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Motorista deletado com sucesso");
    }

}
