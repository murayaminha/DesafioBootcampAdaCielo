package com.cielo.bootcamp.desafio.controller;

import com.cielo.bootcamp.desafio.Fila.Fila;
import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import com.cielo.bootcamp.desafio.exception.DesafioException;
import com.cielo.bootcamp.desafio.service.PessoaFisicaService;
import io.swagger.annotations.Api;
import jakarta.websocket.server.PathParam;
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

@Api(tags ="Pessoa Fisica")
@RestController
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController {

@Autowired
PessoaFisicaService service;

    private Fila fila = new Fila();

    @PostMapping
    public ResponseEntity cadastrarPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO){

        try {
            PessoaFisicaDTO pf = service.cadastrarPessoaFisica(pessoaFisicaDTO);
            fila.inserirDado(pessoaFisicaDTO);
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

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPessoaFisicaPorCpf(@PathVariable("cpf")String cpf){
        try{
            PessoaFisicaDTO pessoaFisicaDTO = service.buscarPessoaFisicaPorCpf(cpf);
            return ResponseEntity.ok().body(pessoaFisicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("email/{email}")
    public ResponseEntity buscarPessoaFisicaPorEmail(@PathVariable("email")String email){
        try{
            PessoaFisicaDTO pessoaFisicaDTO = service.buscarPessoaFisicaEmail(email);
            return ResponseEntity.ok().body(pessoaFisicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }


    @GetMapping("/uuid/{uuid}")
    public ResponseEntity buscarPessoaFisicaPorUUID(@PathVariable("uuid") UUID uuid){
        try{
            PessoaFisicaDTO pessoaFisicaDTO = service.buscarPessoaFisicaPorUUID(uuid);
            return ResponseEntity.ok().body(pessoaFisicaDTO);
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping
    public ResponseEntity alterarPessoaFisica(@RequestBody PessoaFisicaDTO pessoaFisicaDTO){
        PessoaFisicaDTO pessoaFisicaAtual;
        try{
            pessoaFisicaAtual = service.buscarPessoaFisicaPorUUID(pessoaFisicaDTO.getUuid());
            fila.inserirDado(pessoaFisicaDTO);
        }catch (Exception e){
            Map resposta = new HashMap<>();
            resposta.put("Não foi possivel alterar o Cliente","Cliente não localizado");
            return ResponseEntity.badRequest().body(resposta);
        }

        try {
             pessoaFisicaDTO = service.alterarPessoaFisica(pessoaFisicaAtual,pessoaFisicaDTO);
        }catch (DesafioException e){
            Map resposta = new HashMap<>();
            resposta.put(e.getMessage(),e.getCause());
            return ResponseEntity.badRequest().body(resposta);
        }
        return ResponseEntity.ok().body(pessoaFisicaDTO);

    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletarPessoaFisica(@PathVariable UUID uuid){

        PessoaFisicaDTO pessoaFisicaAtual;
        try{
            pessoaFisicaAtual = service.buscarPessoaFisicaPorUUID(uuid);
            fila.inserirDado(pessoaFisicaAtual);
        }catch (Exception e){
            Map resposta = new HashMap<>();
            resposta.put("Não foi possivel excluir o Cliente","Cliente não localizado");
            return ResponseEntity.badRequest().body(resposta);
        }

        service.excluirPessoaFisica(uuid);

        return ResponseEntity.noContent().build();

    }


}
