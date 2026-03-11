package com.tuapp.to_do_app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column(nullable = false,
			length = 50)
	private String nombre;
	
	@Column(nullable = false,
			length = 50)
	private String password;
	
	@Column(unique = true,
			nullable = false,
			length = 50)
	private String email;
	
	@Column(name = "fecha_creacion",
			nullable = false)
	private LocalDateTime fechaCreacion = LocalDateTime.now();
}
