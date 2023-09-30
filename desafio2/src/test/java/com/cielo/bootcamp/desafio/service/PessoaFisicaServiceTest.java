package com.cielo.bootcamp.desafio.service;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.repository.PessoaFisicaRepository;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PessoaFisicaServiceTest {

    @InjectMocks
    PessoaFisicaService service;

    @Mock
    Converter converter;

    @Spy
    Validacao validacao;

    @Mock
    ModelMapper modelMapper;

    @Mock
    PessoaFisicaRepository repository;

    private PessoaFisicaEntity pessoaFisicaEntity;
    private PessoaFisicaDTO pessoaFisicaDTO;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);

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


    }

@Test
    void salvarPessoaFisica() {
        pessoaFisicaEntity.setUuid(UUID.randomUUID());
        when(repository.saveAndFlush(any())).thenReturn(pessoaFisicaEntity);
        when(validacao.validacaoPessoaFisica(pessoaFisicaDTO)).thenReturn("");
        service.cadastrarPessoaFisica(pessoaFisicaDTO);
    }

    @Test
    void salvarPessoaFisicaDeveEstourarExcecao() {

            pessoaFisicaEntity.setUuid(UUID.randomUUID());
            when(validacao.validacaoPessoaFisica(pessoaFisicaDTO)).thenReturn("não passou na validação");
            assertThrows(DesafioException.class, () -> {
                service.cadastrarPessoaFisica(pessoaFisicaDTO);
            }); 
    }


    @Test
    void consultarPessoaFisica() {

        pessoaFisicaEntity.setUuid(UUID.randomUUID());
        when(repository.findByEmail(any())).thenReturn(pessoaFisicaEntity);
        when(repository.findByUuid(any())).thenReturn(pessoaFisicaEntity);
        when(repository.findByNrCpf(any())).thenReturn(pessoaFisicaEntity);
        assertNotNull (service.buscarPessoaFisicaPorUUID(pessoaFisicaEntity.getUuid()));
        assertNotNull (service.buscarPessoaFisicaEmail(pessoaFisicaEntity.getEmail()));
        assertNotNull (service.buscarPessoaFisicaPorCpf(pessoaFisicaEntity.getNrCpf()));

    }

    @Test
    void consultarPessoaFisicaDeveEstourarExcecao() {

        pessoaFisicaEntity.setUuid(UUID.randomUUID());

        when(repository.findByEmail(any())).thenReturn(null);
        when(repository.findByUuid(any())).thenReturn(null);
        when(repository.findByNrCpf(any())).thenReturn(null);
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaFisicaPorCpf(pessoaFisicaDTO.getNrCpf());
        });
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaFisicaPorUUID(pessoaFisicaDTO.getUuid());
        });
        assertThrows(DesafioException.class, () -> {
            service.buscarPessoaFisicaEmail(pessoaFisicaDTO.getEmail());
        });
    }




    @Test
    void alterarPessoaFisica() {

        pessoaFisicaEntity.setUuid(UUID.randomUUID());
        when(repository.saveAndFlush(any())).thenReturn(pessoaFisicaEntity);
        when(validacao.validacaoPessoaFisica(pessoaFisicaDTO)).thenReturn("");
        service.alterarPessoaFisica(pessoaFisicaDTO,pessoaFisicaDTO);
    }

    @Test
    void alterarrPessoaFisicaDeveEstourarExcecao() {

        pessoaFisicaEntity.setUuid(UUID.randomUUID());
        when(validacao.validacaoPessoaFisica(pessoaFisicaDTO)).thenReturn("não passou na validação");
        assertThrows(DesafioException.class, () -> {
            service.alterarPessoaFisica(pessoaFisicaDTO,pessoaFisicaDTO);
        });
    }
}
