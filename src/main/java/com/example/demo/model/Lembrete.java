package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;
    private String cor; // Ex: #ff4757
    private LocalDate data; // A data do lembrete (yyyy-MM-dd)

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}