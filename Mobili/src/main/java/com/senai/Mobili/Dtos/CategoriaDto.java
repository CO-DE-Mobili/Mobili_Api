package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record CategoriaDto(
        @NotBlank String nome,
        @NotBlank String senha,
        @NotBlank String email,
        @NotBlank String endereco,
        String cep,
        String tipo_categoria,

        MultipartFile img
) {}
