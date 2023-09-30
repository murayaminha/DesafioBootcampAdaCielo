package com.cielo.bootcamp.desafio.converter;

import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import com.cielo.bootcamp.desafio.entity.PessoaJuridicaEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Converter {

    private final ModelMapper modelMapper;


    public PessoaJuridicaDTO toPessoaJuridicaDTO(PessoaJuridicaEntity pessoaJuridicaEntity) {
        return this.modelMapper.map(pessoaJuridicaEntity, PessoaJuridicaDTO.class);
    }

    public PessoaJuridicaEntity toPessoaJuridicaEntity(PessoaJuridicaDTO pessoaJuridicaDTO) {
        return this.modelMapper.map(pessoaJuridicaDTO, PessoaJuridicaEntity.class);
    }

    public PessoaFisicaDTO toPessoaFisicaDTO(PessoaFisicaEntity pessoaFisicaEntity) {
        return this.modelMapper.map(pessoaFisicaEntity, PessoaFisicaDTO.class);
    }

    public PessoaFisicaEntity toPessoaFisicaEntity(PessoaFisicaDTO pessoaFisicaDTO) {
        return this.modelMapper.map(pessoaFisicaDTO, PessoaFisicaEntity.class);
    }
}
