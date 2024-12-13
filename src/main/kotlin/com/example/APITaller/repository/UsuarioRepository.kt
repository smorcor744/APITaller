package com.example.APITaller.repository

import com.example.APITaller.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {

    // Implementar una derived query para obtener a un usuario por su nombre
    fun findByUsername(userName: String?): Optional<Usuario>
}