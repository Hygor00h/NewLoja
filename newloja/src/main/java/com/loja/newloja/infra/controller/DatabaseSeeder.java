package com.loja.newloja.infra.controller;



import com.loja.newloja.infra.model.entity.usuario.EnderecoEntity;
import com.loja.newloja.infra.model.entity.usuario.RoleEntity;
import com.loja.newloja.infra.model.entity.usuario.Usuarios;
import com.loja.newloja.infra.model.enums.RoleUser;
import com.loja.newloja.infra.model.enums.SexoEnum;
import com.loja.newloja.infra.model.enums.UfEnum;
import com.loja.newloja.infra.repository.RolesRepository;
import com.loja.newloja.infra.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;
	private final RolesRepository rolesRepository;
	private final PasswordEncoder encoder;

	public DatabaseSeeder(UsuarioRepository usuarioRepository, RolesRepository rolesRepository, PasswordEncoder encoder) {
		this.usuarioRepository = usuarioRepository;
		this.rolesRepository = rolesRepository;
		this.encoder = encoder;
	}


	@Override
	public void run(String... args) throws Exception {

		String adminEmail = "admin@newloja.com";

		if(usuarioRepository.findByEmail(adminEmail).isEmpty()){

			RoleEntity roleUser = rolesRepository.findByName(RoleUser.ROLE_USER)
							.orElseThrow(()-> new RuntimeException("Role User not found"));

			RoleEntity roleAdmin = rolesRepository.findByName(RoleUser.ROLE_ADMIN)
							.orElseThrow(()-> new RuntimeException("Role Admin not found"));


			EnderecoEntity endereco = new EnderecoEntity();
			endereco.setRua("ruaAve");
			endereco.setNumero("12345");
			endereco.setBairro("Bairro");
			endereco.setUf(UfEnum.BA);
			endereco.setCidade("Ralador");
			endereco.setCep("12345-000");

			Usuarios user = new Usuarios();
			user.setEmail(adminEmail);
			user.setName("adminn");
			user.setPassword(encoder.encode("admin123"));
			user.setCpf("1234567890");
			user.setContato("0098765432");
			user.setDataDeNascimento(LocalDate.parse("2000-06-01"));
			user.setSexo(SexoEnum.MASCULINO);
			user.setConsentimentoTermos(Boolean.TRUE);
			user.setEndereco(endereco);


			Set<RoleEntity> roles = new HashSet<>(Set.of(roleUser, roleAdmin));
			user.setRoles(roles);

			usuarioRepository.save(user);
		}else {
		}
	}
}
