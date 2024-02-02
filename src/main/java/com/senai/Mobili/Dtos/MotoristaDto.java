package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

public record MotoristaDto(
    @Email String email,
    String senha,
    MultipartFile img
) {}
