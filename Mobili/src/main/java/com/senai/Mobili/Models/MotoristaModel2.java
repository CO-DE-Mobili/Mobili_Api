package com.senai.Mobili.Models;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_motorista")
public class MotoristaModel2 implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usurio",nullable = false)

    private UUID id;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private String idade;

}