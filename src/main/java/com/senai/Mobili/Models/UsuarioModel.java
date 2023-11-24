package com.senai.Mobili.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


public class UsuarioModel implements Serializable {
    private String nome;
    private String email;

    private String senha;
    private String telefone;
}
