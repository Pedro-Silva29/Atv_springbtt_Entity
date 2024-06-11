package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioServico;

    @GetMapping
    public List<Usuario> obterTodosUsuarios() {
        return usuarioServico.buscarTodosUsuarios();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long usuarioId) {
        return usuarioServico.buscarUsuarioPorId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioServico.salvarUsuario(usuario);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody Usuario detalhesUsuario) {
        return usuarioServico.atualizarUsuario(usuarioId, detalhesUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId) {
        if (usuarioServico.deletarUsuario(usuarioId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
