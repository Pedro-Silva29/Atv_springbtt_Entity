package com.example.demo.service;

import com.example.demo.repository.PedidoRepository;
import com.example.demo.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepositorio;

    public List<Pedido> obterTodosPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Optional<Pedido> obterPedidoPorId(Long id) {
        return pedidoRepositorio.findById(id);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    public Optional<Pedido> atualizarPedido(Long id, Pedido detalhesPedido) {
        return pedidoRepositorio.findById(id).map(pedido -> {
            pedido.setEndereco(detalhesPedido.getEndereco());
            pedido.setValorTotal(detalhesPedido.getValorTotal());
            pedido.setUsuario(detalhesPedido.getUsuario());
            pedido.setProdutos(detalhesPedido.getProdutos());
            return pedidoRepositorio.save(pedido);
        });
    }

    public boolean deletarPedido(Long id) {
        return pedidoRepositorio.findById(id).map(pedido -> {
            pedidoRepositorio.delete(pedido);
            return true;
        }).orElse(false);
    }
}
