package com.sistema.zzootec.controllers;

import com.sistema.zzootec.models.Rol;
import com.sistema.zzootec.models.Usuario;
import com.sistema.zzootec.models.UsuarioRol;
import com.sistema.zzootec.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Page<Usuario> listarUsuarios(Pageable pageable) {
        return usuarioService.findAll(pageable);
    }

    @GetMapping("/rol/{rol}")
    public List<Usuario> listarUsuariosPorRol(@PathVariable("rol") String nombreRol) {
        return usuarioService.findUsersByRol(nombreRol);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{usuarioId}")
    public Usuario obtenerUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.findById(usuarioId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioService.obtenerUsuario(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Usuario guardarAdmin(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(1L);
        rol.setRolNombre("ADMIN");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {
        usuario.setPerfil("default.png");

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

    @PutMapping("/{id}")
    public Usuario editarUsuario(@PathVariable("id") Long usuarioId, @RequestBody Usuario usuario){
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        return usuarioService.update(usuarioId, usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.eliminarUsuario(usuarioId);
    }

}
