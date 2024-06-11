package com.example.demo.controller;

import com.example.demo.model.Mensagem;
import com.example.demo.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    @Autowired
    private MensagemService mensagemServico;

    @GetMapping
    public List<Mensagem> obterTodasMensagens() {
        return mensagemServico.obterTodasMensagens();
    }

    @GetMapping("/{mensagemId}")
    public ResponseEntity<Mensagem> obterMensagemPorId(@PathVariable Long mensagemId) {
        return mensagemServico.obterMensagemPorId(mensagemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mensagem criarMensagem(@RequestBody Mensagem mensagem) {
        return mensagemServico.salvarMensagem(mensagem);
    }

    @PutMapping("/{mensagemId}")
    public ResponseEntity<Mensagem> atualizarMensagem(@PathVariable Long mensagemId, @RequestBody Mensagem detalhesMensagem) {
        return mensagemServico.atualizarMensagem(mensagemId, detalhesMensagem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{mensagemId}")
    public ResponseEntity<Void> deletarMensagem(@PathVariable Long mensagemId) {
        if (mensagemServico.deletarMensagem(mensagemId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

