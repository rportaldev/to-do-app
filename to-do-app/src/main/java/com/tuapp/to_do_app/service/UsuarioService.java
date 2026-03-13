package com.tuapp.to_do_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuapp.to_do_app.dto.LoginDTO;
import com.tuapp.to_do_app.dto.RegistroDTO;
import com.tuapp.to_do_app.model.Usuario;
import com.tuapp.to_do_app.repository.UsuarioRepository;

@Service
public class UsuarioService {

	 @Autowired
	 private UsuarioRepository repository;
	 
	//REGISTRAR 
	public Usuario registrar(RegistroDTO dto) {
	    // 1. CREA UN OBJETO USUARIO NUEVO
	    Usuario usuario = new Usuario();
	    
	    // 2. ASIGNA DATOS QUE LLEGARON DEL DTO
	    usuario.setNombre(dto.getNombre());
	    usuario.setEmail(dto.getEmail());
	    
	    // 3. ENCRIPTA LA CONTRASEÑA ANTES DE GUARDAR
	    // NUNCA SE GUARDA EN TEXTO PLANO
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    usuario.setPassword(encoder.encode(dto.getPassword()));
	    
	    // 4. GUARDA EN LA BASE DE DATOS Y DEVUELVE EL USUARIO
	    return repository.save(usuario);
	}
	
	
	
	//LOGIN
	public Optional<Usuario> login(LoginDTO dto) {
	    // 1. BUSCA EL USUARIO POR EL EMAIL
	    Optional<Usuario> usuario = repository.findByEmail(dto.getEmail());
	    
	    // 2. VERIFICA QUE EXISTE Y LA CONTRASEÑA CORRECTA
	    if(usuario.isPresent()) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        boolean passwordCorrecta = encoder.matches(
	            dto.getPassword(),           // CONTRASEÑA QUE ESCRIBIO
	            usuario.get().getPassword()  // CONTRASEÑA ENCRIPTADA EN LA BD
	        );
	        if(passwordCorrecta) {
	            return usuario; // LOGIN EXITOSO
	        }
	    }
	    // 3. SI NO EXISTE O CONTRASEÑA INCORRECTA DEVUELVE VACIO
	    return Optional.empty();
	}
}
