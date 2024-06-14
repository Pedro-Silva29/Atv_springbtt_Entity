package com.example.demo.service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepositorio;

    public List<Categoria> obterTodasCategorias() {
        return categoriaRepositorio.findAll();
    }

    public Optional<Categoria> obterCategoriaPorId(Long id) {
        return categoriaRepositorio.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public Optional<Categoria> atualizarCategoria(Long id, Categoria detalhesCategoria) {
        return categoriaRepositorio.findById(id).map(categoria -> {
            categoria.setNome(detalhesCategoria.getNome());
            return categoriaRepositorio.save(categoria);
        });
    }

    public boolean deletarCategoria(Long id) {
        return categoriaRepositorio.findById(id).map(categoria -> {
            categoriaRepositorio.delete(categoria);
            return true;
        }).orElse(false);
    }
}
