package com.cielo.bootcamp.desafio.valicao;

import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class Validacao {


    public String validacaoPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO){
        String erros = "";

        if(validarNomes(pessoaFisicaDTO.getNomeCliente()))
            erros = erros.concat("O nome da pessoa fisica é obrigatório e deve ter no máximo 50 caracteres;");

        if(validarCpf(pessoaFisicaDTO.getNrCpf()))
            erros = erros.concat("O cpf da pessoa fisica é obrigatório e deve ter os 11 digitos, devendo ser enviados 0 a esquerda caso houver;");

        if(validarEmail(pessoaFisicaDTO.getEmail()))
            erros = erros.concat("O e-mail da pessoa fisica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);");

        if(validarMCC(pessoaFisicaDTO.getCdMCC()))
            erros = erros.concat("O mcc da pessoa fisica é obrigatório e deve ter no máximo 4 números;");

        return erros;
    }

    public String validacaoPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO){
        String erros = "";

        if(validarNomes(pessoaJuridicaDTO.getNmContatoEstabelecimento()))
            erros = erros.concat("O nome do contato do estabelecimento da pessoa juridica é obrigatório e deve ter no máximo 50 caracteres;");

        if(validarCpf(pessoaJuridicaDTO.getNrCpf()))
            erros = erros.concat("O cpf da pessoa juridica é obrigatório e deve ter os 11 digitos, devendo ser enviados 0 a esquerda caso houver;");

        if(validarEmail(pessoaJuridicaDTO.getEmail()))
            erros = erros.concat("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);");

        if(validarMCC(pessoaJuridicaDTO.getCdMCC()))
            erros = erros.concat("O mcc da pessoa juridica é obrigatório e deve ter no máximo 4 números;");

        if (validarCnpj(pessoaJuridicaDTO.getCnpj()))
            erros = erros.concat("O cnpj da pessoa juridica é obrigatório e deve ter os 14 digitos, devendo ser enviados 0 a esquerda caso houver;");

        if (validarNomes(pessoaJuridicaDTO.getNmRazaoSocial()))
            erros = erros.concat("O razão social da pessoa juridica é obrigatória e deve ter no máximo 50 caracteres;");

        return erros;
    }


    public Boolean validarCpf(String cpf){
        if(Objects.isNull(cpf)|| cpf.isEmpty())
            return true;
        cpf = cpf.replaceAll("[^0-9]", "");

        return cpf.length()!=11;
    }

    public Boolean validarNomes(String nome){
        return (Objects.isNull(nome)|| nome.isEmpty() || nome.length()>50);

    }

    public Boolean validarEmail(String email){
        Pattern pattern = Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

        if(Objects.isNull(email)|| email.isEmpty())
            return true;

        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();

    }

    public boolean validarMCC(String mcc){
        if(Objects.isNull(mcc)|| mcc.isEmpty())
            return true;
        mcc.replaceAll("[^0123456789]", "");

        return mcc.length()!=4;
    }



    public boolean validarCnpj (String cnpj){
        if(Objects.isNull(cnpj)|| cnpj.isEmpty())
            return true;
        cnpj = cnpj.replaceAll("[^0-9]", "");

        return cnpj.length()!=14;
    }


}
