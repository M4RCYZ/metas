package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data; // Se estiver usando Lombok
// ou imports manuais de Getter/Setter

@Entity
@Data // Se não usar Lombok, gere os Getters e Setters manualmente para 'descricao'
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // --- NOVO CAMPO ---
    @Column(columnDefinition = "TEXT")
    private String descricao;
    // ------------------

    private String corHex;

    @Column(nullable = false)
    private boolean ativa = true;

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}