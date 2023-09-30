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
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tb_empresa_pessoa_juridica")
public class PessoaJuridicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid_pj")
    private UUID uuid;

    @NotNull
    @CNPJ
    @Column(name = "ds_cnpj")
    private String cnpj;

    @NotNull
    @Column(name = "nm_razao_social")
    private String nmRazaoSocial;

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

    @NotNull
    @Column(name = "nm_contato_estabelecimento")
    private String nmContatoEstabelecimento;


}
