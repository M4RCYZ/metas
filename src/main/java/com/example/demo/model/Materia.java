package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data // Getters, Setters, ToString
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "cor_hex")
    private String corHex;

    @Column(name = "meta_horas_semana")
    private Integer metaHorasSemana;
}