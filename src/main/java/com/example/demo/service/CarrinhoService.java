package com.example.demo.service;

import com.example.demo.model.Carrinho;
import com.example.demo.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepositorio;

    public List<Carrinho> obterTodosCarrinhos() {
        return carrinhoRepositorio.findAll();
    }

    public Optional<Carrinho> obterCarrinhoPorId(Long id) {
        return carrinhoRepositorio.findById(id);
    }

    public Carrinho salvarCarrinho(Carrinho carrinho) {
        return carrinhoRepositorio.save(carrinho);
    }

    public Optional<Carrinho> atualizarCarrinho(Long id, Carrinho detalhesCarrinho) {
        return carrinhoRepositorio.findById(id).map(carrinho -> {
            carrinho.setUsuario(detalhesCarrinho.getUsuario());
            carrinho.setProdutos(detalhesCarrinho.getProdutos());
            return carrinhoRepositorio.save(carrinho);
        });
    }

    public boolean deletarCarrinho(Long id) {
        return carrinhoRepositorio.findById(id).map(carrinho -> {
            carrinhoRepositorio.delete(carrinho);
            return true;
        }).orElse(false);
    }
}

