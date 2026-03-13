package com.tuapp.to_do_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuapp.to_do_app.dto.LoginDTO;
import com.tuapp.to_do_app.dto.RegistroDTO;
import com.tuapp.to_do_app.model.Usuario;
import com.tuapp.to_do_app.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@RequestBody RegistroDTO dto) {
		
		try {
	        Usuario usuario = service.registrar(dto);
	        return ResponseEntity.status(201).body(usuario);
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(400).body("Error al registrar: " + e.getMessage());
	    }
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO dto){
		
		Optional<Usuario> usuario = service.login(dto);
	    
	    if(usuario.isPresent()) {
	        return ResponseEntity.ok(usuario.get());
	    }
	    return ResponseEntity.status(401).body("Email o contraseña incorrectos");
	}
}
