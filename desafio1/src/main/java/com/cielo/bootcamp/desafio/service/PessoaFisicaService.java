package com.cielo.bootcamp.desafio.service;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.repository.PessoaFisicaRepository;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;
    private final Converter converter;

    private final Validacao validacao;

    public PessoaFisicaService(Converter converter, Validacao validacao) {
        this.converter = converter;
        this.validacao = validacao;
    }

    public PessoaFisicaDTO cadastrarPessoaFisica(PessoaFisicaDTO pessoaFisicaDTO) throws DesafioException {
        String validado = validacao.validacaoPessoaFisica(pessoaFisicaDTO);
        if (!validado.isEmpty())
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa fisica", validado);
        if(pessoaFisicaRepository.existsByEmail(pessoaFisicaDTO.getEmail()))
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa fisica", "Email já cadastrado");

        if(pessoaFisicaRepository.existsByNrCpf(pessoaFisicaDTO.getNrCpf()))
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa fisica", "CPF já cadastrado");


        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.saveAndFlush(converter.toPessoaFisicaEntity(pessoaFisicaDTO));
        return converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    }

    public PessoaFisicaDTO buscarPessoaFisicaPorCpf(String cpf) {
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.findByNrCpf(cpf);
        return converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    }

    public PessoaFisicaDTO buscarPessoaFisicaEmail(String email) {
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.findByEmail(email);
        return converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    }


    public PessoaFisicaDTO buscarPessoaFisicaPorUUID(UUID uuid) {
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.findByUuid(uuid);
        return converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    }

    public PessoaFisicaDTO alterarPessoaFisica(PessoaFisicaDTO entity, PessoaFisicaDTO pessoaFisicaDTO) {
            pessoaFisicaDTO = parse(entity, pessoaFisicaDTO);
        String validado = validacao.validacaoPessoaFisica(pessoaFisicaDTO);
        if (!validado.isEmpty())
            throw new DesafioException("400", "Ocorreu um erro ao alterar pessoa fisica", validado);

        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.saveAndFlush(converter.toPessoaFisicaEntity(pessoaFisicaDTO));
        return converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    }

    public PessoaFisicaDTO parse(PessoaFisicaDTO atual, PessoaFisicaDTO pessoaFisicaDTO) {
        PessoaFisicaDTO pessoaFisica = new PessoaFisicaDTO();
        pessoaFisica.setUuid(pessoaFisicaDTO.getUuid());

        if (Objects.isNull(pessoaFisicaDTO.getCdMCC()) && !pessoaFisicaDTO.getCdMCC().isEmpty())
            pessoaFisica.setCdMCC(pessoaFisicaDTO.getCdMCC());
        else pessoaFisica.setCdMCC(atual.getCdMCC());

        if (Objects.isNull(pessoaFisicaDTO.getNomeCliente()) && !pessoaFisicaDTO.getNomeCliente().isEmpty())
            pessoaFisica.setNomeCliente(pessoaFisicaDTO.getNomeCliente());
        else pessoaFisica.setNomeCliente(atual.getNomeCliente());

        if (Objects.isNull(pessoaFisicaDTO.getNrCpf()) && !pessoaFisicaDTO.getNrCpf().isEmpty())
            pessoaFisica.setNrCpf(pessoaFisicaDTO.getNrCpf());
        else pessoaFisica.setNrCpf(atual.getNrCpf());

        if (Objects.isNull(pessoaFisicaDTO.getEmail()) && !pessoaFisicaDTO.getEmail().isEmpty())
            pessoaFisica.setEmail(pessoaFisicaDTO.getEmail());
        else pessoaFisica.setEmail(atual.getEmail());

        return pessoaFisica;
    }

    public void excluirPessoaFisica(UUID uuid){
        pessoaFisicaRepository.deleteById(uuid);
    }


}