package com.cielo.bootcamp.desafio.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaFisicaDTO implements Serializable {

    private UUID uuid;
    private String nomeCliente;
    private String email;
    private String cdMCC;
    private String nrCpf;


}
