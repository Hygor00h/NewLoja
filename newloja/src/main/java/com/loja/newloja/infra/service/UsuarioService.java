package com.loja.newloja.infra.service;

import com.loja.newloja.infra.exceptions.ConflictException;
import com.loja.newloja.infra.exceptions.ResourceNotFoundException;
import com.loja.newloja.infra.mapper.UsuarioMapper;
import com.loja.newloja.infra.model.dto.LoginDto;
import com.loja.newloja.infra.model.dto.LoginResponseDto;
import com.loja.newloja.infra.model.dto.UsuarioRequestDto;
import com.loja.newloja.infra.model.entity.usuario.RoleEntity;
import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import com.loja.newloja.infra.model.enums.RoleUser;
import com.loja.newloja.infra.repository.RolesRepository;
import com.loja.newloja.infra.repository.UsuarioRepository;
import com.loja.newloja.infra.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.management.relation.RoleInfoNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService {

	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;
	private final UsuarioRepository usuarioRepository;
	private final RolesRepository rolesRepository;
	private final UsuarioMapper usuarioMapper;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, RolesRepository rolesRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.usuarioRepository = usuarioRepository;
		this.rolesRepository = rolesRepository;
		this.usuarioMapper = usuarioMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public LoginResponseDto login (LoginDto dto) throws Exception{

		try {
			Authentication authentication = authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

			String token = jwtUtil.generateToken(authentication);

			return new LoginResponseDto(token, "Bearer");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Email ou senha invalida");
		} catch (Exception e) {
			throw new RoleInfoNotFoundException("Ocorreu um erro interno ao processar o seu login. Tente novamente mais tarde.");
		}
	}


	@Transactional
	public LoginResponseDto registrarUsuario (@NonNull UsuarioRequestDto dto) {

		if(Boolean.FALSE.equals(dto.getConsentimentoTermos())){
			throw new IllegalArgumentException("O consentimento dos termos é obrigatório para realizar o cadastro");
		}

		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			throw new IllegalArgumentException("A senha é obrigatória.");
		}

		emailExistente(dto.getEmail());

		RoleEntity user = rolesRepository.findByName(RoleUser.ROLE_USER)
						.orElseThrow(()-> new ResourceNotFoundException("A permissão ROLE_USER não foi encontrada no banco de dados"));

		Usuarios novoUsuario = usuarioMapper.toEntity(dto);

		novoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));

		Set<RoleEntity> roles = new HashSet<>();
		roles.add(user);
		novoUsuario.setRoles(roles);

		Usuarios usuario = usuarioRepository.save(novoUsuario);

		Authentication  authentication = new UsernamePasswordAuthenticationToken(
						usuario.getUsername(),
						null,
						usuario.getAuthorities());

		String token = jwtUtil.generateToken(authentication);
		return new LoginResponseDto(token, "Bearer");
	}


	public void emailExistente(String email) {
		if (usuarioRepository.existsByEmail(email)) {
			throw new ConflictException("Email já cadastrado!");
		}
	}
}
