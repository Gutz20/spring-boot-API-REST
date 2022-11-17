package com.sistema.zzootec.repository;

import com.sistema.zzootec.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
    @Query(value = "SELECT * FROM Usuarios U INNER JOIN Usuario_Rol UR ON U.id = UR.usuario_id INNER JOIN Roles R ON UR.rol_rol_id = R.rol_id WHERE R.rol_nombre = :nombreRol", nativeQuery = true)
    public List<Usuario> findUsersByRol(@Param("nombreRol") String rol);
}
