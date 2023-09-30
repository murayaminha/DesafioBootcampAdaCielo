package com.cielo.bootcamp.desafio.converter;

import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import com.cielo.bootcamp.desafio.entity.PessoaJuridicaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ConverterTest {
    @InjectMocks
    private Converter converter;

    private PessoaJuridicaEntity pessoaJuridicaEntity;
    private PessoaJuridicaDTO pessoaJuridicaDTO;
    private PessoaFisicaEntity pessoaFisicaEntity;
    private PessoaFisicaDTO pessoaFisicaDTO;

    @Spy
    private final ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void init() {
        pessoaFisicaEntity = PessoaFisicaEntity.builder()
                .nomeCliente("nome do cliente")
                .cdMCC("1234")
                .email("umemail@mail.com")
                .nrCpf("19100000000")
                .build();
        pessoaFisicaDTO = PessoaFisicaDTO.builder()
                .nomeCliente("nome do cliente")
                .cdMCC("1234")
                .email("umemail@mail.com")
                .nrCpf("19100000000")
                .build();

        pessoaJuridicaEntity = PessoaJuridicaEntity.builder()
                .cdMCC("1234")
                .cnpj("13235043000106")
                .email("outroEmail@email.com")
                .nmContatoEstabelecimento("contato estabelecimento")
                .nmRazaoSocial("razão social")
                .nrCpf("19100000000")
                .build();

        pessoaJuridicaDTO = PessoaJuridicaDTO.builder()
                .cdMCC("1234")
                .cnpj("13235043000106")
                .email("outroEmail@email.com")
                .nmContatoEstabelecimento("contato estabelecimento")
                .nmRazaoSocial("razão social")
                .nrCpf("19100000000")
                .build();

    }


@Test
void converterPessoaFisicaDTO(){
    PessoaFisicaDTO pessoaFisica = converter.toPessoaFisicaDTO(pessoaFisicaEntity);
    assertThat(pessoaFisica.getCdMCC()).isEqualTo(pessoaFisicaEntity.getCdMCC());
    assertThat(pessoaFisica.getEmail()).isEqualTo(pessoaFisicaEntity.getEmail());
    assertThat(pessoaFisica.getNomeCliente()).isEqualTo(pessoaFisicaEntity.getNomeCliente());
    assertThat(pessoaFisica.getNrCpf()).isEqualTo(pessoaFisicaEntity.getNrCpf());
    }



    @Test
    void converterPessoaFisicaEntity(){
        PessoaFisicaEntity pessoaFisica = converter.toPessoaFisicaEntity(pessoaFisicaDTO);
        assertThat(pessoaFisica.getCdMCC()).isEqualTo(pessoaFisicaDTO.getCdMCC());
        assertThat(pessoaFisica.getEmail()).isEqualTo(pessoaFisicaDTO.getEmail());
        assertThat(pessoaFisica.getNomeCliente()).isEqualTo(pessoaFisicaDTO.getNomeCliente());
        assertThat(pessoaFisica.getNrCpf()).isEqualTo(pessoaFisicaDTO.getNrCpf());
    }

    @Test
    void converterPessoaJuridicaDTO(){
        PessoaJuridicaDTO pessoaJuridica = converter.toPessoaJuridicaDTO(pessoaJuridicaEntity);
        assertThat(pessoaJuridica.getCdMCC()).isEqualTo(pessoaJuridicaEntity.getCdMCC());
        assertThat(pessoaJuridica.getEmail()).isEqualTo(pessoaJuridicaEntity.getEmail());
        assertThat(pessoaJuridica.getNrCpf()).isEqualTo(pessoaJuridicaEntity.getNrCpf());
        assertThat(pessoaJuridica.getCnpj()).isEqualTo(pessoaJuridicaEntity.getCnpj());
        assertThat(pessoaJuridica.getNmContatoEstabelecimento()).isEqualTo(pessoaJuridicaEntity.getNmContatoEstabelecimento());
        assertThat(pessoaJuridica.getNmRazaoSocial()).isEqualTo(pessoaJuridicaEntity.getNmRazaoSocial());

    }



    @Test
    void converterpessoaJuridicaEntity(){
        PessoaJuridicaEntity pessoaJuridica = converter.toPessoaJuridicaEntity(pessoaJuridicaDTO);
        assertThat(pessoaJuridica.getCdMCC()).isEqualTo(pessoaJuridicaDTO.getCdMCC());
        assertThat(pessoaJuridica.getEmail()).isEqualTo(pessoaJuridicaDTO.getEmail());
        assertThat(pessoaJuridica.getNrCpf()).isEqualTo(pessoaJuridicaDTO.getNrCpf());
        assertThat(pessoaJuridica.getCnpj()).isEqualTo(pessoaJuridicaDTO.getCnpj());
        assertThat(pessoaJuridica.getNmContatoEstabelecimento()).isEqualTo(pessoaJuridicaDTO.getNmContatoEstabelecimento());
        assertThat(pessoaJuridica.getNmRazaoSocial()).isEqualTo(pessoaJuridicaDTO.getNmRazaoSocial());

    }

}

