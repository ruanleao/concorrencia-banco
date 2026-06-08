package com.banco.service;

import com.banco.domain.ContaBancaria;
import com.banco.domain.ContaBancariaVersionada;
import com.banco.repository.ContaBancariaRepository;
import com.banco.repository.ContaBancariaVersionadaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ContaService {

    private final ContaBancariaRepository contaRepo;
    private final ContaBancariaVersionadaRepository contaVersionadaRepo;

    public ContaService(ContaBancariaRepository contaRepo, ContaBancariaVersionadaRepository contaVersionadaRepo) {
        this.contaRepo = contaRepo;
        this.contaVersionadaRepo = contaVersionadaRepo;
    }

    // --- PARTE 1: Sem Controle (Problema) ---
    @Transactional
    public void depositar(Long id, BigDecimal valor) {
        ContaBancaria conta = contaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo().add(valor));
        contaRepo.save(conta);
    }

    @Transactional
    public void sacar(Long id, BigDecimal valor) {
        ContaBancaria conta = contaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepo.save(conta);
    }

    // --- PARTE 2: Com Controle Otimista (Solução) ---
    @Transactional
    public void depositarVersionado(Long id, BigDecimal valor) {
        ContaBancariaVersionada conta = contaVersionadaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        conta.setSaldo(conta.getSaldo().add(valor));
        contaVersionadaRepo.save(conta);
    }

    @Transactional
    public void sacarVersionado(Long id, BigDecimal valor) {
        ContaBancariaVersionada conta = contaVersionadaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaVersionadaRepo.save(conta);
    }
}
