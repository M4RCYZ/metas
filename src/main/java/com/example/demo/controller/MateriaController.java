package com.example.demo.controller;

import com.example.demo.model.Materia;
import com.example.demo.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias") // Prefixo da rota: http://localhost:8080/materias
public class MateriaController {

    @Autowired
    private MateriaRepository repository;

    @GetMapping()
    public List<Materia> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Materia criar(@RequestBody Materia materia) {
        return repository.save(materia);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}