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
@Table(name = "tb_servico")
public class ServicoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1l;
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String nome;

    private String url_img;

    private String servico;

    private String descricao;

    private String valor;

    private String duracao_parceria;
}
