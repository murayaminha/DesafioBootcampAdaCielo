package com.cielo.bootcamp.desafio.controller;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.service.PessoaFisicaService;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PessoaFisicaController.class)
public class PessoaFisicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaFisicaService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    Converter converter;

    @Spy
    Validacao validacao;

    @Autowired
    private PessoaFisicaController controller;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);


        pessoaFisicaDTO = PessoaFisicaDTO.builder()
                .nomeCliente("nome do cliente")
                .cdMCC("1234")
                .email("umemail@mail.com")
                .nrCpf("191.000.000-00")
                .build();


    }




    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }
     PessoaFisicaDTO pessoaFisicaDTO;

    @Test
    void cadastrarCliente(){

        when(service.cadastrarPessoaFisica(any())).thenReturn(pessoaFisicaDTO);
    assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),200);
    }

    @Test
    void cadastrarClienteErro(){
        pessoaFisicaDTO = new PessoaFisicaDTO();

        when(service.cadastrarPessoaFisica(pessoaFisicaDTO)).thenThrow(DesafioException.class);
        assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),400);
    }

    @Test
    void consultarClienteUUID(){


        when(service.buscarPessoaFisicaPorUUID(any())).thenReturn(pessoaFisicaDTO);
        assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),200);

    }

    @Test
    void consultarClienteCPF(){


        when(service.buscarPessoaFisicaPorCpf(any())).thenReturn(pessoaFisicaDTO);
        assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),200);

    }
    @Test
    void consultarClienteEmail(){


        when(service.buscarPessoaFisicaEmail(any())).thenReturn(pessoaFisicaDTO);
        assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),200);

    }

    @Test
    void alterarCliente(){


        when(service.alterarPessoaFisica(any(),any())).thenReturn(pessoaFisicaDTO);
        assertEquals(controller.cadastrarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),200);
    }

    @Test
    void alterarClienteErro(){
        pessoaFisicaDTO = new PessoaFisicaDTO();

        when(service.alterarPessoaFisica(any(),any())).thenThrow(DesafioException.class);
        assertEquals(controller.alterarPessoaFisica(pessoaFisicaDTO).getStatusCode().value(),400);
    }

    @Test
    void deletarCliente(){

        assertEquals(controller.deletarPessoaFisica(pessoaFisicaDTO.getUuid()).getStatusCode().value(),204);
    }


}
