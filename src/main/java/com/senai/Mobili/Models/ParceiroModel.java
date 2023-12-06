package com.senai.Mobili.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_parceiro") //consumindo da tabela especifica do meu banco de dados//
public class ParceiroModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean aprovado;

    private String urlImg;




}