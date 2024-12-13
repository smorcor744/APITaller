package com.example.APITaller.repository

import com.example.APITaller.model.Servicios
import com.example.APITaller.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ServiciosRepository : JpaRepository<Servicios, Long> {

    fun findByNombre(nombre: String?): Optional<Servicios>

}