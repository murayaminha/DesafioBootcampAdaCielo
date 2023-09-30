package com.cielo.bootcamp.desafio.controller;

import com.cielo.bootcamp.desafio.converter.Converter;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.service.PessoaJuridicaService;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PessoaJuridicaController.class)
public class PessoaJuridicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaJuridicaService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Mock
    Converter converter;

    @Spy
    Validacao validacao;

    @Autowired
    private PessoaJuridicaController controller;

    PessoaJuridicaDTO pessoaJuridicaDTO;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);



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
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    void cadastrarCliente(){

        when(service.cadastrarPessoaJuridica(any())).thenReturn(pessoaJuridicaDTO);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),200);
    }

    @Test
    void cadastrarClienteErro(){
        pessoaJuridicaDTO = new PessoaJuridicaDTO();

        when(service.cadastrarPessoaJuridica(pessoaJuridicaDTO)).thenThrow(DesafioException.class);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),400);
    }

    @Test
    void consultarClienteUUID(){


        when(service.buscarPessoaJuridicaPorUUID(any())).thenReturn(pessoaJuridicaDTO);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),200);

    }

    @Test
    void consultarClienteCPF(){


        when(service.buscarPessoaJuridicaPorCnpj(any())).thenReturn(pessoaJuridicaDTO);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),200);

    }
    @Test
    void consultarClienteEmail(){


        when(service.buscarPessoaJuridicaEmail(any())).thenReturn(pessoaJuridicaDTO);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),200);

    }

    @Test
    void alterarCliente(){


        when(service.alterarPessoaJuridica(any(),any())).thenReturn(pessoaJuridicaDTO);
        assertEquals(controller.cadastrarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),200);
    }

    @Test
    void alterarClienteErro(){
        pessoaJuridicaDTO = new PessoaJuridicaDTO();

        when(service.alterarPessoaJuridica(any(),any())).thenThrow(DesafioException.class);
        assertEquals(controller.alterarPessoaJuridica(pessoaJuridicaDTO).getStatusCode().value(),400);
    }

    @Test
    void deletarCliente(){

        assertEquals(controller.deletarPessoaJuridica(pessoaJuridicaDTO.getUuid()).getStatusCode().value(),204);
    }


}

