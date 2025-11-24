package com.example.cadastro_usuario.business;

import org.springframework.stereotype.Service;

import com.example.cadastro_usuario.infrastructure.entitys.Usuario;
import com.example.cadastro_usuario.infrastructure.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public void salvar(Usuario usuario) {
        repo.saveAndFlush(usuario); //salva e fecha a conexão com o banco de dados
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repo.findbyEmail(email).orElseThrow( 
            ()-> new RuntimeException("Email não encontrado.")
        );
    }

    public void deletarUsuarioPorEmail(String email) {
        repo.deleteByEmail(email);
    }

    //atualizar dado especifico passsando o objeto completo pra evitar "sobreescrição" do resto das informações
    public void atualizarUsuarioPorID(Long id, Usuario usuario) {
        Usuario usuarioEntity = repo.findById(id)
            .orElseThrow( ()-> new RuntimeException("Usuario não encontrado"));

        Usuario usuarioAtualizado = usuarioEntity.toBuilder()
            .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
            .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
            .build();
    
        repo.saveAndFlush(usuarioAtualizado);
    }
}
