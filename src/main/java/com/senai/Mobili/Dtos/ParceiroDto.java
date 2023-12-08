package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

public record ParceiroDto(String nome,
                          @Email String email,
                          String senha,
                          String telefone,
                          MultipartFile img
) {}


