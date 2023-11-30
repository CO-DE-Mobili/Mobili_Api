package com.senai.Mobili.Controllers;

import com.senai.Mobili.Dtos.CategoriaDto;
import com.senai.Mobili.Models.CategoriaModel;
import com.senai.Mobili.Repositories.CategoriaRepository;
import com.senai.Mobili.Services.FileuploadServices;
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
@RequestMapping(value = "/categoria", produces = {"application/json"})

public class CategoriaController {

        @Autowired
        CategoriaRepository categoriaRepository;

        @Autowired
    FileuploadServices fileuploadServices;

        @GetMapping
        public ResponseEntity<List<CategoriaModel>> listarCategoria() {
            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
        }

        @GetMapping("/{idCategoria}")
    public ResponseEntity<Object> buscarCategoriaId(@PathVariable(value = "idCategoria")UUID id) {
            Optional<CategoriaModel> categoriaBuscado = categoriaRepository.findById(id);

            if (categoriaBuscado.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body("categoria nao encontrado");

            }
            return ResponseEntity.status(HttpStatus.OK).body(categoriaBuscado.get());
        }

        @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> cadastrarCategoria(@ModelAttribute @Valid CategoriaDto dadosRecebidos){
            CategoriaModel categoriaModel = new CategoriaModel();
            BeanUtils.copyProperties(dadosRecebidos, categoriaModel);

            String urlImg;

            try {
                urlImg = fileuploadServices.fazerUpload(dadosRecebidos.img());

            }catch (IOException e){
                throw new RuntimeException(e);
            }

            categoriaModel.setUrl_img(urlImg);

            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoriaModel));
        }

        @PutMapping(value = "/{idCategoria}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editarCategoria(@PathVariable(value = "idCategoria")UUID id,@ModelAttribute @Valid CategoriaDto categoriaDto){
            Optional<CategoriaModel> categoriaBuscado = categoriaRepository.findById(id);

            if (categoriaBuscado.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrada");
            }

            CategoriaModel categoriaModel = categoriaBuscado.get();
            BeanUtils.copyProperties(categoriaDto, categoriaModel);

            String urlImg;

            try {
                urlImg = fileuploadServices.fazerUpload(categoriaDto.img());

            }catch (IOException e){
                throw new RuntimeException(e);
            }

            categoriaModel.setUrl_img(urlImg);

            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoriaModel));
        }

        @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Object> deletarCategoria(@PathVariable(value = "idCategoria") UUID id){
            Optional<CategoriaModel> categoriaBuscado = categoriaRepository.findById(id);

            if (categoriaBuscado.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrada");
            }

            categoriaRepository.delete(categoriaBuscado.get());
            return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso");
        }


    }
