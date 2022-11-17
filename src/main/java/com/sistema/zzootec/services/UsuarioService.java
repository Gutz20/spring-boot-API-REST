package com.sistema.zzootec.services;

import com.sistema.zzootec.models.Usuario;
import com.sistema.zzootec.models.UsuarioRol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    public Usuario obtenerUsuario(String username);
    public Usuario update(Long id, Usuario usuario);
    public void eliminarUsuario(Long usuarioId);
    public Page<Usuario> findAll(Pageable pageable);
    public Usuario findById(Long id);

    public List<Usuario> findUsersByRol(String rol);
}
