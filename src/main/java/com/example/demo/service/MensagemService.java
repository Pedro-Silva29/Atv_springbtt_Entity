package com.example.demo.service;

import com.example.demo.model.Mensagem;
import com.example.demo.repository.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemService  {
    @Autowired
    private MensagemRepository mensagemRepositorio;

    public List<Mensagem> obterTodasMensagens() {
        return mensagemRepositorio.findAll();
    }

    public Optional<Mensagem> obterMensagemPorId(Long id) {
        return mensagemRepositorio.findById(id);
    }

    public Mensagem salvarMensagem(Mensagem mensagem) {
        return mensagemRepositorio.save(mensagem);
    }

    public Optional<Mensagem> atualizarMensagem(Long id, Mensagem detalhesMensagem) {
        return mensagemRepositorio.findById(id).map(mensagem -> {
            mensagem.setTexto(detalhesMensagem.getTexto());
            mensagem.setUsuario(detalhesMensagem.getUsuario());
            return mensagemRepositorio.save(mensagem);
        });
    }

    public boolean deletarMensagem(Long id) {
        return mensagemRepositorio.findById(id).map(mensagem -> {
            mensagemRepositorio.delete(mensagem);
            return true;
        }).orElse(false);
    }
}
