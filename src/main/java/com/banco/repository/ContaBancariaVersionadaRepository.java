package com.banco.repository;

import com.banco.domain.ContaBancariaVersionada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaVersionadaRepository extends JpaRepository<ContaBancariaVersionada, Long> {
}
