package com.cielo.bootcamp.desafio.service;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaJuridicaEntity;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.repository.PessoaJuridicaRepository;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class PessoaJuridicaService {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;
    private final Validacao validacao;

    private final Converter converter;

    public PessoaJuridicaService(Converter converter, Validacao validacao) {
        this.converter = converter;
        this.validacao = validacao;
    }

    public PessoaJuridicaDTO cadastrarPessoaJuridica(PessoaJuridicaDTO pessoaJuridicaDTO) throws DesafioException {
        String validado = validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        if (!validado.isEmpty())
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa juridica", validado);

        if(pessoaJuridicaRepository.existsByEmail(pessoaJuridicaDTO.getEmail()))
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa juridica", "Email já cadastrado");

        if(pessoaJuridicaRepository.existsByNrCpf(pessoaJuridicaDTO.getNrCpf()))
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa juridica", "CPF já cadastrado");

        if(pessoaJuridicaRepository.existsByCnpj(pessoaJuridicaDTO.getNrCpf()))
            throw new DesafioException("400", "Ocorreu um erro ao cadastrar pessoa juridica", "cnpj já cadastrado");

        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.saveAndFlush(converter.toPessoaJuridicaEntity(pessoaJuridicaDTO));
        return converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
    }

    public PessoaJuridicaDTO buscarPessoaJuridicaPorCnpj(String cnpj) {
        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.findByCnpj(cnpj);
        return converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
    }

    public PessoaJuridicaDTO buscarPessoaJuridicaEmail(String email) {
        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.findByEmail(email);
        return converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
    }


    public PessoaJuridicaDTO buscarPessoaJuridicaPorUUID(UUID uuid) {
        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.findByUuid(uuid);
        return converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
    }

    public PessoaJuridicaDTO alterarPessoaJuridica(PessoaJuridicaDTO entity, PessoaJuridicaDTO pessoaJuridicaDTO) {
        pessoaJuridicaDTO = parse(entity, pessoaJuridicaDTO);
        String validado = validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        if (!validado.isEmpty())
            throw new DesafioException("400", "Ocorreu um erro ao alterar pessoa fisica", validado);

        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.saveAndFlush(converter.toPessoaJuridicaEntity(pessoaJuridicaDTO));
        return converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
    }

    public PessoaJuridicaDTO parse(PessoaJuridicaDTO atual, PessoaJuridicaDTO pessoaJuridicaDTO) {
        PessoaJuridicaDTO pessoaJuridica = new PessoaJuridicaDTO();
        pessoaJuridica.setUuid(pessoaJuridicaDTO.getUuid());
        if (Objects.isNull(pessoaJuridicaDTO.getCdMCC()) && !pessoaJuridicaDTO.getCdMCC().isEmpty())
            pessoaJuridica.setCdMCC(pessoaJuridicaDTO.getCdMCC());
        else pessoaJuridica.setCdMCC(atual.getCdMCC());

        if (Objects.isNull(pessoaJuridicaDTO.getCnpj()) && !pessoaJuridicaDTO.getCnpj().isEmpty())
            pessoaJuridica.setCnpj(pessoaJuridicaDTO.getCnpj());
        else pessoaJuridica.setCnpj(atual.getCnpj());

        if (Objects.isNull(pessoaJuridicaDTO.getNrCpf()) && !pessoaJuridicaDTO.getNrCpf().isEmpty())
            pessoaJuridica.setNrCpf(pessoaJuridicaDTO.getNrCpf());
        else pessoaJuridica.setNrCpf(atual.getNrCpf());

        if (Objects.isNull(pessoaJuridicaDTO.getEmail()) && !pessoaJuridicaDTO.getEmail().isEmpty())
            pessoaJuridica.setEmail(pessoaJuridicaDTO.getEmail());
        else pessoaJuridica.setEmail(atual.getEmail());

        if (Objects.isNull(pessoaJuridicaDTO.getNmContatoEstabelecimento()) && !pessoaJuridicaDTO.getNmContatoEstabelecimento().isEmpty())
            pessoaJuridica.setNmContatoEstabelecimento(pessoaJuridicaDTO.getNmContatoEstabelecimento());
        else pessoaJuridica.setNmContatoEstabelecimento(atual.getNmContatoEstabelecimento());

        if (Objects.isNull(pessoaJuridicaDTO.getNmRazaoSocial()) && !pessoaJuridicaDTO.getNmRazaoSocial().isEmpty())
            pessoaJuridica.setNmRazaoSocial(pessoaJuridicaDTO.getNmRazaoSocial());
        else pessoaJuridica.setNmRazaoSocial(atual.getNmRazaoSocial());

        return pessoaJuridica;
    }

    public void excluirPessoaJuridica(UUID uuid){
        pessoaJuridicaRepository.deleteById(uuid);
    }


}