package com.banco.controller;


import com.banco.dto.OperacaoRequest;
import com.banco.service.ContaService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    // Endpoints Parte 1
    @PostMapping("/{id}/deposito")
    public ResponseEntity<String> depositar(@PathVariable Long id, @Valid @RequestBody OperacaoRequest request) {
        service.depositar(id, request.valor());
        return ResponseEntity.ok("Depósito realizado com sucesso (Sem Lock)");
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<String> sacar(@PathVariable Long id, @Valid @RequestBody OperacaoRequest request) {
        service.sacar(id, request.valor());
        return ResponseEntity.ok("Saque realizado com sucesso (Sem Lock)");
    }

    // Endpoints Parte 2
    @PostMapping("/versionada/{id}/deposito")
    public ResponseEntity<String> depositarVersionado(@PathVariable Long id, @Valid @RequestBody OperacaoRequest request) {
        service.depositarVersionado(id, request.valor());
        return ResponseEntity.ok("Depósito realizado com sucesso (Lock Otimista)");
    }

    @PostMapping("/versionada/{id}/saque")
    public ResponseEntity<String> sacarVersionado(@PathVariable Long id, @Valid @RequestBody OperacaoRequest request) {
        service.sacarVersionado(id, request.valor());
        return ResponseEntity.ok("Saque realizado com sucesso (Lock Otimista)");
    }
}
