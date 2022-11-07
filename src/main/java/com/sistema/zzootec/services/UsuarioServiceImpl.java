package com.sistema.zzootec.services;

import com.sistema.zzootec.exceptions.UsuarioFoundException;
import com.sistema.zzootec.models.Usuario;
import com.sistema.zzootec.models.UsuarioRol;
import com.sistema.zzootec.repository.RolRepository;
import com.sistema.zzootec.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioLocal != null) {
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        } else {
            for (UsuarioRol usuarioRol : usuarioRoles) {
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario update(Long id, Usuario nuevoUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setUsername(nuevoUsuario.getUsername());
                    usuario.setNombre(nuevoUsuario.getNombre());
                    usuario.setApellido(nuevoUsuario.getApellido());
                    usuario.setEmail(nuevoUsuario.getEmail());
                    usuario.setTelefono(nuevoUsuario.getTelefono());
                    usuario.setPassword(nuevoUsuario.getPassword());
                    return usuarioRepository.save(usuario);
                })
                .orElseGet(() -> {
                    nuevoUsuario.setId(id);
                    return usuarioRepository.save(nuevoUsuario);
                });
    }

}
