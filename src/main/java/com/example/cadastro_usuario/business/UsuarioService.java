package com.example.cadastro_usuario.business;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cadastro_usuario.infrastructure.entitys.Usuario;
import com.example.cadastro_usuario.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario salvar(Usuario usuario) {
        return repo.saveAndFlush(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return repo.findbyEmail(email);
    }

    public void deletarUsuarioPorEmail(String email) {
        repo.deleteByEmail(email);
    }

    //atualizar dado especifico passsando o objeto completo pra evitar "sobreescrição" do resto das informações
    public Usuario atualizarUsuarioPorID(Long id, Usuario usuario) {
        Usuario usuarioEntity = repo.findById(id)
            .orElseThrow( ()-> new RuntimeException("Usuario não encontrado"));

        Usuario usuarioAtualizado = usuarioEntity.toBuilder()
            .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
            .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
            .build();
    
        return repo.saveAndFlush(usuarioAtualizado);
    }
}
