package com.example.APITaller.service

import com.example.APITaller.model.Citas
import com.example.APITaller.repository.CitasRepository
import com.example.APITaller.repository.ServiciosRepository
import com.example.APITaller.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CitasService {

    @Autowired
    private lateinit var citaRepository: CitasRepository

    @Autowired
    private lateinit var servicioRepository: ServiciosRepository

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository

    /**
     * MÉTODO PARA CREAR UNA NUEVA CITA
     */
    fun createCita(cita: Citas): Citas {
        // Validamos que el usuario exista
        val usuarioId = cita.usuario?.id
            ?: throw IllegalArgumentException("El ID del usuario es obligatorio.")

        val usuarioExistente = usuarioRepository.findById(usuarioId)
        if (usuarioExistente.isEmpty) {
            throw IllegalArgumentException("El usuario con ID $usuarioId no existe")
        }

        // Validamos que el servicio exista
        val servicioId = cita.servicio?.id
            ?: throw IllegalArgumentException("El ID del servicio es obligatorio.")

        val servicioExistente = servicioRepository.findById(servicioId)
        if (servicioExistente.isEmpty) {
            throw IllegalArgumentException("El servicio con ID $servicioId no existe")
        }

        // Guardamos la cita en la base de datos
        val nuevaCita = citaRepository.save(cita)

        return nuevaCita
    }
    /**
     * MÉTODO PARA OBTENER TODAS LAS CITAS
     */
    fun findAllCitas(): List<Citas> {
        return citaRepository.findAll()
    }

    /**
     * MÉTODO PARA OBTENER UNA CITA POR ID
     */
    fun findCitaById(id: String): Citas? {
        val idLong = id.toLongOrNull() ?: throw IllegalArgumentException("ID inválido: $id")
        return citaRepository.findById(idLong).orElse(null)
    }

    /**
     * MÉTODO PARA ACTUALIZAR UNA CITA
     */
    fun updateCita(id: String, cita: Citas): Citas {
        val idLong = id.toLongOrNull() ?: throw IllegalArgumentException("ID inválido: $id")

        // Verificamos si la cita existe
        val citaExistente = citaRepository.findById(idLong).orElseThrow {
            IllegalArgumentException("La cita con ID $idLong no existe")
        }

        // Validamos que el usuario exista
        val usuarioId = cita.usuario?.id
            ?: throw IllegalArgumentException("El ID del usuario es obligatorio.")

        if (usuarioRepository.findById(usuarioId).isEmpty) {
            throw IllegalArgumentException("El usuario con ID $usuarioId no existe")
        }

        // Validamos que el servicio exista
        val servicioId = cita.servicio?.id
            ?: throw IllegalArgumentException("El ID del servicio es obligatorio.")

        if (servicioRepository.findById(servicioId).isEmpty) {
            throw IllegalArgumentException("El servicio con ID $servicioId no existe")
        }

        // Actualizamos los datos de la cita existente
        citaExistente.apply {
            this.usuario = cita.usuario
            this.servicio = cita.servicio
            this.fechaCita = cita.fechaCita
            this.estado = cita.estado
        }

        // Guardamos la cita actualizada
        return citaRepository.save(citaExistente)
    }

    /**
     * MÉTODO PARA ELIMINAR UNA CITA
     */
    fun deleteCita(id: String): Boolean {
        val idLong = id.toLongOrNull() ?: throw IllegalArgumentException("ID inválido: $id")

        return if (citaRepository.existsById(idLong)) {
            citaRepository.deleteById(idLong)
            true
        } else {
            false
        }
    }

    /**
     * MÉTODO PARA OBTENER TODAS LAS CITAS DE UN USUARIO
     */
    fun findCitasByUsuarioId(idUsuario: String): List<Citas> {
        val idUsuarioLong = idUsuario.toLongOrNull()
            ?: throw IllegalArgumentException("ID inválido: $idUsuario")

        val usuario = usuarioRepository.findById(idUsuarioLong)
        if (usuario.isEmpty) {
            throw IllegalArgumentException("El usuario con ID $idUsuarioLong no existe")
        }

        return citaRepository.findByUsuarioId(idUsuarioLong)
    }
}
