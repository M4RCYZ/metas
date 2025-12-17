package com.example.demo.controller;

import com.example.demo.model.Nota;
import com.example.demo.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "*")
public class NotaController {

    @Autowired
    private NotaRepository repository;

    @GetMapping
    public List<Nota> listar() {
        return repository.findAllByOrderByDataAtualizacaoDesc();
    }

    @PostMapping
    public Nota salvar(@RequestBody Nota nota) {
        nota.setDataAtualizacao(LocalDateTime.now());
        return repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota atualizar(@PathVariable Long id, @RequestBody Nota novosDados) {
        return repository.findById(id).map(nota -> {
            nota.setTitulo(novosDados.getTitulo());
            nota.setConteudo(novosDados.getConteudo());
            nota.setDataAtualizacao(LocalDateTime.now());
            return repository.save(nota);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}