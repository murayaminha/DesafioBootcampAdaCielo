package com.cielo.bootcamp.desafio.service;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaJuridicaEntity;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.repository.PessoaFisicaRepository;
import com.cielo.bootcamp.desafio.repository.PessoaJuridicaRepository;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PessoaJuridicaServiceTest {
    @InjectMocks
    PessoaJuridicaService service;
    @Mock
    Converter converter;
    @Mock
    Validacao validacao;

    @Mock
    PessoaJuridicaRepository repository;
    private PessoaJuridicaEntity pessoaJuridicaEntity;
    private PessoaJuridicaDTO pessoaJuridicaDTO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

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
    void salvarPessoaJuridica() {
        pessoaJuridicaEntity.setUuid(UUID.randomUUID());
        when(repository.saveAndFlush(any())).thenReturn(pessoaJuridicaEntity);
        when(validacao.validacaoPessoaJuridica(pessoaJuridicaDTO)).thenReturn("");
        service.cadastrarPessoaJuridica(pessoaJuridicaDTO);
    }

    @Test
    void salvarPessoaJuridicaDeveEstourarExcecao() {

        pessoaJuridicaEntity.setUuid(UUID.randomUUID());
        when(validacao.validacaoPessoaJuridica(pessoaJuridicaDTO)).thenReturn("não passou na validação");
        assertThrows(DesafioException.class, () -> {
            service.cadastrarPessoaJuridica(pessoaJuridicaDTO);
        });
    }


    @Test
    void consultarPessoaJuridica() {

        pessoaJuridicaEntity.setUuid(UUID.randomUUID());
        when(repository.findByEmail(any())).thenReturn(pessoaJuridicaEntity);
        when(repository.findByUuid(any())).thenReturn(pessoaJuridicaEntity);
        when(repository.findByCnpj(any())).thenReturn(pessoaJuridicaEntity);
        assertNotNull (service.buscarPessoaJuridicaPorUUID(pessoaJuridicaEntity.getUuid()));
        assertNotNull (service.buscarPessoaJuridicaEmail(pessoaJuridicaEntity.getEmail()));
        assertNotNull (service.buscarPessoaJuridicaPorCnpj(pessoaJuridicaEntity.getNrCpf()));

    }

    @Test
    void consultarPessoaJuridicaDeveEstourarExcecao() {

        pessoaJuridicaEntity.setUuid(UUID.randomUUID());

        when(repository.findByEmail(any())).thenReturn(null);
        when(repository.findByUuid(any())).thenReturn(null);
        when(repository.findByCnpj(any())).thenReturn(null);
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaJuridicaPorCnpj(pessoaJuridicaDTO.getNrCpf());
        });
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaJuridicaPorUUID(pessoaJuridicaDTO.getUuid());
        });
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaJuridicaEmail(pessoaJuridicaDTO.getEmail());
        });
    }




    @Test
    void alterarPessoaJuridica() {

        pessoaJuridicaEntity.setUuid(UUID.randomUUID());
        when(repository.saveAndFlush(any())).thenReturn(pessoaJuridicaEntity);
        when(validacao.validacaoPessoaJuridica(pessoaJuridicaDTO)).thenReturn("");
        service.alterarPessoaJuridica(pessoaJuridicaDTO,pessoaJuridicaDTO);
    }

    @Test
    void alterarrPessoaJuridicaDeveEstourarExcecao() {

        pessoaJuridicaEntity.setUuid(UUID.randomUUID());
        when(validacao.validacaoPessoaJuridica(pessoaJuridicaDTO)).thenReturn("não passou na validação");
        assertThrows(DesafioException.class, () -> {
            service.alterarPessoaJuridica(pessoaJuridicaDTO,pessoaJuridicaDTO);
        });
    }
}
