package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.MotoristaDto;
import com.senai.Mobili.Models.MotoristaModel;
import com.senai.Mobili.Repositories.MotoristaRepository;
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
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(value = "/motorista", produces = {"application/json"})

public class MotoristaController {

    @Autowired
    MotoristaRepository MotoristaRepository;

    @Autowired
    FileUploadService fileUploadService;

    @GetMapping
    public ResponseEntity<List<MotoristaModel>> listarMotorista() {
        return ResponseEntity.status(HttpStatus.OK).body(MotoristaRepository.findAll());
    }

    @GetMapping("/{idMotorista}")
    public ResponseEntity<Object> buscarMotoristaId(@PathVariable(value = "idMotorista")UUID id) {
        Optional<MotoristaModel> MotoristaBuscado = MotoristaRepository.findById(id);

        if (MotoristaBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("motorista nao encontrado");

        }
        return ResponseEntity.status(HttpStatus.OK).body(MotoristaBuscado.get());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> cadastrarMotorista(@ModelAttribute @Valid MotoristaDto dadosRecebidos){
        if (MotoristaRepository.findByEmail(dadosRecebidos.email()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ja cadastrado");
        }
        MotoristaModel MotoristaModel = new MotoristaModel();
        BeanUtils.copyProperties(dadosRecebidos, MotoristaModel);

        String urlImg;

        try {
            urlImg = FileUploadService.fazerUpload(dadosRecebidos.url_img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        MotoristaModel.setUrl_img(urlImg);

        return ResponseEntity.status(HttpStatus.CREATED).body(MotoristaRepository.save(MotoristaModel));
    }

    @PutMapping(value = "/{idMotorista}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarMotorista(@PathVariable(value = "idMotorista")UUID id,@ModelAttribute @Valid MotoristaDto MotoristaDto){
        Optional<MotoristaModel> MotoristaBuscado = MotoristaRepository.findById(id);

        if (MotoristaBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista nao encontrada");
        }

        MotoristaModel MotoristaModel = MotoristaBuscado.get();
        BeanUtils.copyProperties(MotoristaDto, MotoristaModel);

        String urlImg;

        try {
            urlImg = FileUploadService.fazerUpload(MotoristaDto.url_img());

        }catch (IOException e){
            throw new RuntimeException(e);
        }

        MotoristaModel.setUrl_img(urlImg);

        return ResponseEntity.status(HttpStatus.OK).body(MotoristaRepository.save( MotoristaModel));
    }

    @DeleteMapping("/{idMotorista}")
    public ResponseEntity<Object> deletarMotorista(@PathVariable(value = "idMotorista") UUID id){
        Optional<MotoristaModel> MotoristaBuscado = MotoristaRepository.findById(id);

        if (MotoristaBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista nao encontrado");
        }

        MotoristaRepository.delete(MotoristaBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Motorista deletado com sucesso");
    }


}