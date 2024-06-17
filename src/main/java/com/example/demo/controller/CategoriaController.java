package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaServico;

    @GetMapping
    public List<Categoria> obterTodasCategorias() {
        return categoriaServico.obterTodasCategorias();
    }

    //categoria_id
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Long categoriaId) {
        return categoriaServico.obterCategoriaPorId(categoriaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaServico.salvarCategoria(categoria);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long categoriaId, @RequestBody Categoria detalhesCategoria) {
        return categoriaServico.atualizarCategoria(categoriaId, detalhesCategoria)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long categoriaId) {
        if (categoriaServico.deletarCategoria(categoriaId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

