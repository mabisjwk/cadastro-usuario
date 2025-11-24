package com.example.cadastro_usuario.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cadastro_usuario.infrastructure.entitys.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findbyEmail(String email); //usar o optional quando o metodo nao existir no jparepository pq daí obrigda a criar uma exceção evitando o nullPointerException

    @Transactional //se der qualquer erro ele cancela a operação e nao exclui o email
    void deleteByEmail(String email);
}
