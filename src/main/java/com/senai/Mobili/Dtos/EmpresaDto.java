package com.senai.Mobili.Dtos;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public record EmpresaDto(
        String nome,
        String razao_social,
        String cnpj,
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf,
        String ddd,
        String descricao,
        Time hora_abertura,
        Time hora_fechamento
) {
}
