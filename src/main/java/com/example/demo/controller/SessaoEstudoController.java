package com.example.demo.controller;

import com.example.demo.model.SessaoEstudo;
import com.example.demo.repository.SessaoEstudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessoes")
public class SessaoEstudoController {

    @Autowired
    private SessaoEstudoRepository repository;

    @GetMapping
    public List<SessaoEstudo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public SessaoEstudo criar(@RequestBody SessaoEstudo sessao) {
        return repository.save(sessao);
    }
}