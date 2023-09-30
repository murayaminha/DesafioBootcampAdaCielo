package com.cielo.bootcamp.desafio.repository;

import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import com.cielo.bootcamp.desafio.entity.PessoaJuridicaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaEntity, UUID> {
    PessoaJuridicaEntity findByCnpj(String cnpj);

    PessoaJuridicaEntity findByEmail(String email);

    PessoaJuridicaEntity findByUuid(UUID uuid);

    Boolean existsByNrCpf(String nrCpf);

    Boolean existsByEmail(String email) ;

    Boolean existsByCnpj(String cnpj) ;

}
