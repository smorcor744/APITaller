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
    fun createCita(cita: Citas): Citas? {
        // Validamos que el usuario exista
        val usuarioExistente = usuarioRepository.findById(cita.usuario?.id!!)
        if (usuarioExistente.isEmpty) {
            throw IllegalArgumentException("El usuario con ID \${cita.usuario?.id} no existe")
        }

        // Validamos que el servicio exista
        val servicioExistente = servicioRepository.findById(cita.servicio?.id!!)
        if (servicioExistente.isEmpty) {
            throw IllegalArgumentException("El servicio con ID \${cita.servicio?.id} no existe")
        }

        // Guardamos la cita en la base de datos
        return citaRepository.save(cita)
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
        val idLong = id.toLongOrNull() ?: return null
        return citaRepository.findById(idLong).orElse(null)
    }

    /**
     * MÉTODO PARA ACTUALIZAR UNA CITA
     */
    fun updateCita(id: String, cita: Citas): Citas? {
        val idLong = id.toLongOrNull() ?: return null

        // Verificamos si la cita existe
        val citaExistente = citaRepository.findById(idLong)
        if (citaExistente.isEmpty) {
            throw IllegalArgumentException("La cita con ID \${idLong} no existe")
        }

        // Validamos que el usuario exista
        val usuarioExistente = usuarioRepository.findById(cita.usuario?.id!!)
        if (usuarioExistente.isEmpty) {
            throw IllegalArgumentException("El usuario con ID \${cita.usuario?.id} no existe")
        }

        // Validamos que el servicio exista
        val servicioExistente = servicioRepository.findById(cita.servicio?.id!!)
        if (servicioExistente.isEmpty) {
            throw IllegalArgumentException("El servicio con ID \${cita.servicio?.id} no existe")
        }

        // Actualizamos los datos de la cita existente
        val citaActualizada = citaExistente.get().apply {
            this.usuario = cita.usuario
            this.servicio = cita.servicio
            this.fechaCita = cita.fechaCita
            this.estado = cita.estado
        }

        // Guardamos la cita actualizada
        return citaRepository.save(citaActualizada)
    }

    /**
     * MÉTODO PARA ELIMINAR UNA CITA
     */
    fun deleteCita(id: String): Boolean {
        val idLong = id.toLongOrNull() ?: return false

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
        val idUsuarioLong = idUsuario.toLongOrNull() ?: return emptyList()
        return citaRepository.findByUsuarioId(idUsuarioLong)
    }
}
