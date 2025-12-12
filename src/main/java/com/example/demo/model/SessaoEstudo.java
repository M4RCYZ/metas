package com.example.demo.model; // Ajuste para seu pacote

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessoes_estudo")
public class SessaoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;

    public Long getMinutosEstudados() {
        if (inicio != null && fim != null) {
            return java.time.Duration.between(inicio, fim).toMinutes();
        }
        return 0L;
    }
}