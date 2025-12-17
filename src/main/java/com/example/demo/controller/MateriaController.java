package com.example.demo.controller;

import com.example.demo.model.Materia;
import com.example.demo.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
@CrossOrigin("*")
public class MateriaController {

    @Autowired
    private MateriaRepository repository;

    // 1. GET: Agora traz só as ativas (Esconde as deletadas da tela principal)
    @GetMapping
    public List<Materia> listarTodas() {
        return repository.findByAtivaTrue();
    }

    // 2. NOVO GET: Traz apenas as "Lixeira/Histórico"
    @GetMapping("/arquivadas")
    public List<Materia> listarArquivadas() {
        return repository.findByAtivaFalse();
    }

    // 3. POST: Salvar (continua igual)
    @PostMapping
    public Materia criar(@RequestBody Materia materia) {
        materia.setAtiva(true); // Garante que nasce ativa
        return repository.save(materia);
    }

    // 4. DELETE: Agora faz o "Soft Delete" (Marca como false)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Materia materia = repository.findById(id).orElse(null);
        if (materia != null) {
            materia.setAtiva(false); // NÃO APAGA, SÓ DESATIVA
            repository.save(materia);
        }
    }

    // 5. RESTAURAR (Opcional: para o botão de desfazer)
    @PutMapping("/{id}")
    public Materia atualizar(@PathVariable Long id, @RequestBody Materia novosDados) {
        return repository.findById(id).map(m -> {
            m.setNome(novosDados.getNome());
            m.setDescricao(novosDados.getDescricao());
            m.setCorHex(novosDados.getCorHex());
            return repository.save(m);
        }).orElse(null);
    }

    @PatchMapping("/{id}/restaurar")
    public void restaurar(@PathVariable Long id) {
        var materia = repository.findById(id).orElse(null);
        if (materia != null) {
            materia.setAtiva(true); // Reativa a matéria
            repository.save(materia);
        }
    }

}