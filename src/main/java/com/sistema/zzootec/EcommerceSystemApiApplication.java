package com.sistema.zzootec;

import com.sistema.zzootec.exceptions.UsuarioFoundException;
import com.sistema.zzootec.models.Rol;
import com.sistema.zzootec.models.Usuario;
import com.sistema.zzootec.models.UsuarioRol;
import com.sistema.zzootec.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class EcommerceSystemApiApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSystemApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Usuario usuario = new Usuario();
			usuario.setNombre("Joe");
			usuario.setApellido("Gutierrez");
			usuario.setUsername("guz");
			usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
			usuario.setEmail("guz@gmail.com");
			usuario.setTelefono("938626150");
			usuario.setPerfil("foto.png");

			Rol rol = new Rol();
			rol.setRolId(1L);
			rol.setRolNombre("ADMIN");

			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuarioRoles.add(usuarioRol);

			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
			System.out.println(usuarioGuardado.getUsername());
		} catch (UsuarioFoundException exception) {
			exception.printStackTrace();
		}
	}
}
