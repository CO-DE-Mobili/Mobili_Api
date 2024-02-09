package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.ServicoDto;
import com.senai.Mobili.Models.ServicoModel;
import com.senai.Mobili.Repositories.ServicoRepository;
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
@RequestMapping(value = "/servico", produces = {"application/json"})
public class ServicoController {


    @Autowired
    FileUploadService fileUploadService;

    @Autowired
    ServicoRepository servicoRepository;

    @GetMapping
    public ResponseEntity<List<ServicoModel>> ListarServico() {
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUsuarioId(@PathVariable(value = "id") UUID id) {
        Optional<ServicoModel> usuarioBuscado = servicoRepository.findById(id);

        if (usuarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");

        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> cadastrarServico(@ModelAttribute @Valid ServicoDto dadosRecebidos) {
        ServicoModel servicoModel = new ServicoModel();
        BeanUtils.copyProperties(dadosRecebidos, servicoModel);

        String urlImagem;

        try {
            urlImagem = fileUploadService.fazerUpload(dadosRecebidos.imagem());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        servicoModel.setUrl_img(urlImagem);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoRepository.save(servicoModel));
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarServico(@PathVariable(value = "id") UUID id, @ModelAttribute @Valid ServicoDto servicoDto) {

        Optional<ServicoModel> servicoBuscado = servicoRepository.findById(id);

        if (servicoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");

        }

        ServicoModel servicoModel = servicoBuscado.get();
        BeanUtils.copyProperties(servicoDto,servicoModel);

        String urlImg;

        try {
            urlImg = fileUploadService.fazerUpload(servicoDto.imagem());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        servicoModel.setUrl_img(urlImg);

        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.save(servicoModel));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deletarUsuario (@PathVariable(value = "id") UUID id){

        Optional<ServicoModel> servicoBuscado = servicoRepository.findById(id);

        if (servicoBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");

        }

        servicoRepository.delete(servicoBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
    }

}


