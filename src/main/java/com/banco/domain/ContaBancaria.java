package com.banco.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titular;
    private BigDecimal saldo = BigDecimal.ZERO;

    public ContaBancaria() {}

    public ContaBancaria(String titular, BigDecimal saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public Long getId() { return id; }
    public String getTitular() { return titular; }
    public BigDecimal getSaldo() { return saldo; }
    
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
