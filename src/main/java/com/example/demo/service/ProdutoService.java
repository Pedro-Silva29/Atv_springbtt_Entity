package com.example.demo.service;

import com.example.demo.repository.ProdutoRepository;
import com.example.demo.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepositorio;

    public List<Produto> obterTodosProdutos() {
        return produtoRepositorio.findAll();
    }

    public Optional<Produto> obterProdutoPorId(Long id) {
        return produtoRepositorio.findById(id);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepositorio.save(produto);
    }

    public Optional<Produto> atualizarProduto(Long id, Produto detalhesProduto) {
        return produtoRepositorio.findById(id).map(produto -> {
            produto.setNome(detalhesProduto.getNome());
            produto.setPreco(detalhesProduto.getPreco());
            produto.setDescricao(detalhesProduto.getDescricao());
            produto.setCategoria(detalhesProduto.getCategoria());
            return produtoRepositorio.save(produto);
        });
    }

    public boolean deletarProduto(Long id) {
        return produtoRepositorio.findById(id).map(produto -> {
            produtoRepositorio.delete(produto);
            return true;
        }).orElse(false);
    }
}
