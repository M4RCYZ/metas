package com.example.demo.controller;

// --- GARANTA QUE OS IMPORTS ESTEJAM ASSIM ---
import com.example.demo.TarefaDTO.TarefaDTO;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;
// --------------------------------------------

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody TarefaDTO dados) {
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(dados.titulo());
        novaTarefa.setDescricao(dados.descricao());
        novaTarefa.setCategoria(dados.categoria());
        novaTarefa.setPrioridade(dados.prioridade()); // <--- ESSA LINHA É ESSENCIAL
        novaTarefa.setDataHora(dados.dataHora());

        repository.save(novaTarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    // ... (mantenha os outros métodos de delete/patch se tiver) ...
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa novosDados) {
        return repository.findById(id).map(t -> {
            t.setTitulo(novosDados.getTitulo());
            t.setPrioridade(novosDados.getPrioridade());
            t.setDescricao(novosDados.getDescricao());
            return repository.save(t);
        }).orElse(null);
    }
    
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Tarefa> finalizarTarefa(@PathVariable Long id) {
        return repository.findById(id).map(tarefa -> {
            // Inverte o status (se estava false vira true, e vice-versa)
            tarefa.setConcluido(!tarefa.isConcluido());
            repository.save(tarefa);
            return ResponseEntity.ok(tarefa);
        }).orElse(ResponseEntity.notFound().build());
    }

}