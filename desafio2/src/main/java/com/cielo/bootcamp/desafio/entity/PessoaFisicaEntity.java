package com.cielo.bootcamp.desafio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tb_empresa_pessoa_fisica")
public class PessoaFisicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_pj")
    private UUID uuid;

    @NotNull
    @Column(name = "nm_cliente")
    private String nomeCliente;

    @Email
    @NotNull
    @Column(name = "ds_email")
    private String email;

    @NotNull
    @Column(name = "cd_Merchant_Category_Code")
    private String cdMCC;

    @NotNull
    @CPF
    @Column(name = "nr_cpf")
    private String nrCpf;

}
