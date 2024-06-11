package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obterTodosPedidos() {
        return pedidoService.obterTodosPedidos();
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Pedido> obterPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.obterPedidoPorId(pedidoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody Pedido pedido) {
        return pedidoService.salvarPedido(pedido);
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long pedidoId, @RequestBody Pedido detalhesPedido) {
        return pedidoService.atualizarPedido(pedidoId, detalhesPedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long pedidoId) {
        if (pedidoService.deletarPedido(pedidoId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

