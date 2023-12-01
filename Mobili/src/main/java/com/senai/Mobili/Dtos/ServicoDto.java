package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ServicoDto(

        @NotBlank String nome,
        @NotBlank String foto,

        String servico,

        String descricao,

        String valor,

        String duracao_parceria,

        MultipartFile img


) {
}
