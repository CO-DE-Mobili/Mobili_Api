package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record MotoristaDto(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String telefone,
        String idade,
        MultipartFile url_img
) {}
