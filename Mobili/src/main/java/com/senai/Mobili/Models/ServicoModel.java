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
    private static final long serialVersinUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_servico", nullable = false)
    private UUID id;

    private String nome;
}
