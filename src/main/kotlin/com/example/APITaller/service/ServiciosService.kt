package com.example.APITaller.service

import com.example.APITaller.model.Servicios

import com.example.APITaller.repository.ServiciosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ServiciosService {

    @Autowired
    private lateinit var serviciosRepository: ServiciosRepository

    /**
     * MÉTODO PARA CREAR UN NUEVO SERVICIO
     */
    fun createServicio(servicio: Servicios): Servicios {
        return serviciosRepository.save(servicio)
    }

    /**
     * MÉTODO PARA OBTENER TODOS LOS SERVICIOS
     */
    fun findAllServicios(): List<Servicios> {
        return serviciosRepository.findAll()
    }

    /**
     * MÉTODO PARA OBTENER UN SERVICIO POR ID
     */
    fun findServicioById(id: String): Servicios? {
        val idLong = id.toLongOrNull() ?: return null
        return serviciosRepository.findById(idLong).orElse(null)
    }

    /**
     * MÉTODO PARA ACTUALIZAR UN SERVICIO
     */
    fun updateServicio(id: String, servicio: Servicios): Servicios? {
        val idLong = id.toLongOrNull() ?: return null

        // Verificamos si el servicio existe
        val servicioExistente = serviciosRepository.findById(idLong)
        if (servicioExistente.isEmpty) {
            throw IllegalArgumentException("El servicio con ID \${idLong} no existe")
        }

        // Actualizamos los datos del servicio existente
        val servicioActualizado = servicioExistente.get().apply {
            this.nombre = servicio.nombre
            this.descripcion = servicio.descripcion
            this.precio = servicio.precio
        }

        // Guardamos el servicio actualizado
        return serviciosRepository.save(servicioActualizado)
    }

    /**
     * MÉTODO PARA ELIMINAR UN SERVICIO
     */
    fun deleteServicio(id: String): Boolean {
        val idLong = id.toLongOrNull() ?: return false

        return if (serviciosRepository.existsById(idLong)) {
            serviciosRepository.deleteById(idLong)
            true
        } else {
            false
        }
    }
}
