package com.example.demo.TarefaDTO;

import java.time.LocalDateTime;

public record TarefaDTO(
        String titulo,
        String descricao,
        String categoria,
        String prioridade,
        LocalDateTime dataHora
) {
}