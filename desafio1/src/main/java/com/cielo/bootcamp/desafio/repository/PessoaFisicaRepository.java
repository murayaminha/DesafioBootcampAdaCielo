package com.cielo.bootcamp.desafio.repository;

import com.cielo.bootcamp.desafio.entity.PessoaFisicaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, UUID> {

    PessoaFisicaEntity findByNrCpf(String cpf);

    PessoaFisicaEntity findByEmail(String email);

    PessoaFisicaEntity findByUuid(UUID uuid);

    Boolean existsByNrCpf(String nrCpf);

    Boolean existsByEmail(String email) ;
}
