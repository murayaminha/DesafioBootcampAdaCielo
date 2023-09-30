package com.cielo.bootcamp.desafio.controller;

import com.cielo.bootcamp.desafio.Fila.Fila;
import com.cielo.bootcamp.desafio.dto.PessoaFisicaDTO;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="Fila")
@RestController
@RequestMapping("/fila")
public class FilaController {

    Fila fila;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity buscarPessoaFisicaPorCpf(@PathVariable("cpf")String cpf){
        try{
            return ResponseEntity.ok().body(fila.consumirFila());
        }
        catch (Exception e ){
            return ResponseEntity.notFound().build();
        }

    }

}
