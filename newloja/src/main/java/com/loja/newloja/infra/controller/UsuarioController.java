package com.loja.newloja.infra.controller;

import com.loja.newloja.infra.model.dto.*;
import com.loja.newloja.infra.security.JwtUtil;
import com.loja.newloja.infra.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/newloja")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;


	public UsuarioController(UsuarioService usuarioService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.jwtUtil = jwtUtil;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) throws Exception {
		LoginResponseDto login = usuarioService.login(loginDto);
		return ResponseEntity.ok(login);
	}

	@PostMapping("/register")
	public ResponseEntity<LoginResponseDto> cadastrar(@Valid @RequestBody UsuarioRequestDto usuarios){
		return ResponseEntity.ok(usuarioService.registrarUsuario(usuarios));
	}

//	@PutMapping("/perfil")
//	public ResponseEntity<UsuarioResponseDto> atualizarDados(@Valid @RequestBody UsuarioUpdateDto usuarioUpdateDto,
//																													 @RequestHeader("Authorization") String token) throws Exception {
//		String tokenLimpo = token.substring(7);
//		String email = jwtUtil.extrairEmailToken(tokenLimpo);
//		UsuarioResponseDto response = usuarioService.atulizarDadosUsuario(email, usuarioUpdateDto);
//
//		return ResponseEntity.ok(response);
//	}
}
