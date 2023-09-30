package com.cielo.bootcamp.desafio.controller;

import com.cielo.bootcamp.desafio.Fila.Fila;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.dto.PessoaJuridicaDTO;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.service.PessoaJuridicaService;
import io.swagger.annotations.Api;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="Pessoa Juridica")
@RestController
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {

@Autowired
PessoaJuridicaService service;

private Fila fila = new Fila();

    @PostMapping
    public ResponseEntity cadastrarPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO){

        try {
            PessoaJuridicaDTO pf = service.cadastrarPessoaJuridica(pessoaJuridicaDTO);
            fila.inserirDado(pessoaJuridicaDTO);
            return ResponseEntity.ok().body(pf);
        }catch (DesafioException e){
            Map resposta = new HashMap<>();
            resposta.put(e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body(e.toString());
        }catch (Exception e){
            Map resposta = new HashMap<>();
            resposta.put(e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity buscarPessoaJuridicaPorCpf(@PathVariable("cnpj")String cnpj){
        try{
            PessoaJuridicaDTO pessoaJuridicaDTO = service.buscarPessoaJuridicaPorCnpj(cnpj);
            return ResponseEntity.ok().body(pessoaJuridicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("email/{email}")
    public ResponseEntity buscarPessoaJuridicaPorEmail(@PathVariable("email")String email){
        try{
            PessoaJuridicaDTO pessoaJuridicaDTO = service.buscarPessoaJuridicaEmail(email);
            return ResponseEntity.ok().body(pessoaJuridicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/uuid/{uuid}")
    public ResponseEntity buscarPessoaJuridicaPorUUID(@PathVariable("uuid") UUID uuid){
        try{
            PessoaJuridicaDTO pessoaJuridicaDTO = service.buscarPessoaJuridicaPorUUID(uuid);
            return ResponseEntity.ok().body(pessoaJuridicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping
    public ResponseEntity alterarPessoaJuridica(@RequestBody PessoaJuridicaDTO pessoaJuridicaDTO){
        PessoaJuridicaDTO pessoaJuridicaAtual;
        try{
            pessoaJuridicaAtual = service.buscarPessoaJuridicaPorUUID(pessoaJuridicaDTO.getUuid());
            fila.inserirDado(pessoaJuridicaDTO);
        }catch (Exception e){
            Map resposta = new HashMap<>();
            resposta.put("Não foi possivel alterar o Cliente","Cliente não localizado");
            return ResponseEntity.badRequest().body(resposta);
        }

        try {
            pessoaJuridicaDTO = service.alterarPessoaJuridica(pessoaJuridicaAtual,pessoaJuridicaDTO);
        }catch (DesafioException e){
            Map resposta = new HashMap<>();
            resposta.put(e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body(resposta);
        }
        return ResponseEntity.ok().body(pessoaJuridicaDTO);

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletarPessoaJuridica(@PathVariable UUID uuid){

        PessoaJuridicaDTO pessoaJuridicaAtual;
        try{
            pessoaJuridicaAtual = service.buscarPessoaJuridicaPorUUID(uuid);
            fila.inserirDado(pessoaJuridicaAtual);
        }catch (Exception e){
            Map resposta = new HashMap<>();
            resposta.put("Não foi possivel excluir o Cliente","Cliente não localizado");
            return ResponseEntity.badRequest().body(resposta);
        }

        service.excluirPessoaJuridica(uuid);

        return ResponseEntity.noContent().build();

    }


}
