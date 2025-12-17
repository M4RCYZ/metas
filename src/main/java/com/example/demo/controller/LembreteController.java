package com.example.demo.controller;

import com.example.demo.model.Lembrete;
import com.example.demo.repository.LembreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
@CrossOrigin("*")
public class LembreteController {

    @Autowired
    private LembreteRepository repository;

    @GetMapping
    public List<Lembrete> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Lembrete criar(@RequestBody Lembrete lembrete) {
        return repository.save(lembrete);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}