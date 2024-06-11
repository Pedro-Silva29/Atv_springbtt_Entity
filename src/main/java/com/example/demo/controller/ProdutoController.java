package com.example.demo.controller;

import com.example.demo.service.ProdutoService;
import com.example.demo.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoServico;

    @GetMapping
    public List<Produto> obterTodosProdutos() {
        return produtoServico.obterTodosProdutos();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> obterProdutoPorId(@PathVariable Long produtoId) {
        return produtoServico.obterProdutoPorId(produtoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoServico.salvarProduto(produto);
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long produtoId, @RequestBody Produto detalhesProduto) {
        return produtoServico.atualizarProduto(produtoId, detalhesProduto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long produtoId) {
        if (produtoServico.deletarProduto(produtoId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
