package com.tuapp.to_do_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuapp.to_do_app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
}
