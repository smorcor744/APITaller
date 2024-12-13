package com.example.APITaller.repository

import com.example.APITaller.model.Citas
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
interface CitasRepository : JpaRepository<Citas, Long> {

    fun findByUsuarioId(usuarioId: Long): List<Citas>
}