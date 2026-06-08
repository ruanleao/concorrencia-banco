package com.banco.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record OperacaoRequest(
        @NotNull(message = "O valor não pode ser nulo") 
        @Positive(message = "O valor deve ser maior que zero") 
        BigDecimal valor
) {}
