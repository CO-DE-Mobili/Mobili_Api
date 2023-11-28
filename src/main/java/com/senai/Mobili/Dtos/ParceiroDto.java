package com.senai.Mobili.Dtos;

import jakarta.validation.constraints.Email;

public record ParceiroDto(String nome,
                          @Email String email,
                          String senha,
                          String telefone



) {}


