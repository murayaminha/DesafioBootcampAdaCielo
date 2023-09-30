package com.cielo.bootcamp.desafio.validacao;

import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.valicao.Validacao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ValidacaoTest {

    @InjectMocks
    private Validacao validacao;
    private PessoaJuridicaDTO pessoaJuridicaDTO;
    private PessoaFisicaDTO pessoaFisicaDTO;



    @BeforeAll
    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);


        pessoaFisicaDTO = PessoaFisicaDTO.builder()
                .nomeCliente("nome do cliente")
                .cdMCC("1234")
                .email("umemail@mail.com")
                .nrCpf("191.000.000-00")
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
    void validarPessoaJuridica() {
        assert (validacao.validacaoPessoaJuridica(pessoaJuridicaDTO)).isEmpty();
    }

    @Test
    void validarPessoaFisica() {
        assert (validacao.validacaoPessoaFisica(pessoaFisicaDTO)).isEmpty();
    }

    @Test
    void validarPessoaJuridicaCnpjInvalido() {
        pessoaJuridicaDTO.setCnpj("1");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O cnpj da pessoa juridica é obrigatório e deve ter os 14 digitos, devendo ser enviados 0 a esquerda caso houver",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }

    @Test
    void validarPessoaJuridicaCnpjInvalidoLetras() {
        pessoaJuridicaDTO.setCnpj("1aa");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O cnpj da pessoa juridica é obrigatório e deve ter os 14 digitos, devendo ser enviados 0 a esquerda caso houver",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }

    @Test
    void validarPessoaJuridicaCnpjInvalidoQuantidadeCaracteres() {
        pessoaJuridicaDTO.setCnpj("25.026.474/0001-331");
        String retornoValidacao = validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O cnpj da pessoa juridica é obrigatório e deve ter os 14 digitos, devendo ser enviados 0 a esquerda caso houver",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }
    @Test
    void validarPessoaJuridicaCpfInvalido() {
        pessoaJuridicaDTO.setNrCpf("1");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O cpf da pessoa juridica é obrigatório e deve ter os 11 digitos, devendo ser enviados 0 a esquerda caso houver",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }

    @Test
    void validarPessoaJuridicaEmail() {
        pessoaJuridicaDTO.setEmail("1");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }

    @Test
    void validarPessoaJuridicaEmailInvalido() {

        pessoaJuridicaDTO.setEmail("emial@@e");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail(null);
        init();
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("email@.com");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("email");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("email.com");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("email@email.");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setEmail("e mail@email.");
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
    }

    @Test
    void validarPessoaJuridicaNome() {
        init();
        pessoaJuridicaDTO.setNmContatoEstabelecimento("1");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);",retornoValidacao);
        assert !(retornoValidacao).isEmpty();


    }

    @Test
    void validarPessoaJuridicaNomeOutro() {
        pessoaJuridicaDTO.setNmContatoEstabelecimento("um nome com mais de 50 caracteresssssssssssssssssss");
        String retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O nome do contato do estabelecimento da pessoa juridica é obrigatório e deve ter no máximo 50 caracteres;",retornoValidacao);
        assert !(retornoValidacao).isEmpty();
        pessoaJuridicaDTO.setNmContatoEstabelecimento("");
         retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O nome do contato do estabelecimento da pessoa juridica é obrigatório e deve ter no máximo 50 caracteres;",retornoValidacao);
        pessoaJuridicaDTO.setNmContatoEstabelecimento(null);
        retornoValidacao =validacao.validacaoPessoaJuridica(pessoaJuridicaDTO);
        assertEquals("O nome do contato do estabelecimento da pessoa juridica é obrigatório e deve ter no máximo 50 caracteres;",retornoValidacao);

    }

    @Test
    void validarPessoaFisicaCompletamenteInvalida() {
        pessoaFisicaDTO = new PessoaFisicaDTO();
        assert (validacao.validacaoPessoaFisica(pessoaFisicaDTO)).equals("O nome da pessoa fisica é obrigatório e deve ter no máximo 50 caracteres;O cpf da pessoa fisica é obrigatório e deve ter os 11 digitos, devendo ser enviados 0 a esquerda caso houver;O e-mail da pessoa fisica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);O mcc da pessoa fisica é obrigatório e deve ter no máximo 4 números;");
    }
    @Test
    void validarPessoaJuridicaCompletamenteInvalida() {
        pessoaJuridicaDTO = new PessoaJuridicaDTO();
        assertEquals (validacao.validacaoPessoaJuridica(pessoaJuridicaDTO),"O nome do contato do estabelecimento da pessoa juridica é obrigatório e deve ter no máximo 50 caracteres;O cpf da pessoa juridica é obrigatório e deve ter os 11 digitos, devendo ser enviados 0 a esquerda caso houver;O e-mail da pessoa juridica é obrigatório e deve conter '@' e '.' ex(seuemail@mail.com);O mcc da pessoa juridica é obrigatório e deve ter no máximo 4 números;O cnpj da pessoa juridica é obrigatório e deve ter os 14 digitos, devendo ser enviados 0 a esquerda caso houver;O razão social da pessoa juridica é obrigatória e deve ter no máximo 50 caracteres;");
    }


    @Test
    void validarCpf() {
        assertFalse(validacao.validarCpf("19100000000"));
    }

    @Test
    void validarCpfNull() {
        assertTrue(validacao.validarCpf(null));
    }

    @Test
    void validarCpfEmpty() {
        assertTrue(validacao.validarCnpj(""));
    }

    @Test
    void validarCpfInvalido() {
        assertTrue (validacao.validarCpf(""));
        assertTrue (validacao.validarCpf("1"));
        assertTrue (validacao.validarCpf("1910000000000"));
        assertTrue (validacao.validarCpf("191.000.000.000"));
        assertTrue (validacao.validarCpf("191.000.000"));
        assertTrue (validacao.validarCpf("abc"));

    }

    @Test
    void validarNomes() {
        assertFalse (validacao.validarNomes("um nome qualquer"));
    }

    @Test
    void validarNomesNull() {
        assertTrue(validacao.validarNomes(null));
    }

    @Test
    void validarNomesEmpty() {
        assertTrue (validacao.validarNomes(""));
    }

    @Test
    void validarNomesInvalido() {
        assertTrue (validacao.validarNomes("um nome com mais de 50 caracteresssssssssssssssssss"));
    }


    @Test
    void validarEmail() {
        assertFalse (validacao.validarEmail("umEmail@mail.com"));
    }

    @Test
    void validarEmailNull() {
        assertTrue(validacao.validarEmail(null));
    }

    @Test
    void validarEmailEmpty() {
        assertTrue (validacao.validarEmail(""));
    }

    @Test
    void validarEmailInvalido() {
        assertTrue (validacao.validarEmail("umEmailmail.com"));
    }

    @Test
    void validarMCC() {
        assertFalse(validacao.validarMCC("1234"));
    }

    @Test
    void validarMCCNull() {
        assertTrue (validacao.validarMCC(null));
    }

    @Test
    void validarMCCEmpty() {
        assertTrue (validacao.validarMCC(""));
    }

    @Test
    void validarMCCInvalido() {
        assertTrue (validacao.validarMCC("12345"));
    }


    @Test
    void validarCnpj() {
        assertFalse(validacao.validarCnpj("71.429.415/0001-23"));
    }

    @Test
    void validarCnpjNull() {
        assertTrue (validacao.validarCnpj(null));
    }

    @Test
    void validarCnpjEmpty() {
        assertTrue (validacao.validarCnpj(""));
    }

    @Test
    void validarCnpjInvalido() {
        assertTrue (validacao.validarCnpj("71.429.415/0001-2"));
    }


}
