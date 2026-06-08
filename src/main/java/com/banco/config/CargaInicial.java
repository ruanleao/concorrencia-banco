package com.banco.config;

import com.banco.domain.ContaBancaria;
import com.banco.domain.ContaBancariaVersionada;
import com.banco.repository.ContaBancariaRepository;
import com.banco.repository.ContaBancariaVersionadaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class CargaInicial implements CommandLineRunner {

    private final ContaBancariaRepository repo1;
    private final ContaBancariaVersionadaRepository repo2;

    // O Spring Boot injeta automaticamente os repositórios aqui
    public CargaInicial(ContaBancariaRepository repo1, ContaBancariaVersionadaRepository repo2) {
        this.repo1 = repo1;
        this.repo2 = repo2;
    }

    @Override
    public void run(String... args) {
        // Cria a conta para a Parte 1 (Sem Lock) com saldo inicial de 1000.00
        repo1.save(new ContaBancaria("Conta Sem Lock", new BigDecimal("1000.00")));

        // Cria a conta para a Parte 2 (Lock Otimista) com saldo inicial de 1000.00
        repo2.save(new ContaBancariaVersionada("Conta Com Lock", new BigDecimal("1000.00")));

        System.out.println("------------------------------------------------------------");
        System.out.println("Contas iniciais criadas com sucesso! ID 1 disponível para ambas.");
        System.out.println("Saldo inicial configurado: R$ 1000.00");
        System.out.println("------------------------------------------------------------");
    }
}