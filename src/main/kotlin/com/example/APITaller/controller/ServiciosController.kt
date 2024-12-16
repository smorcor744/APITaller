package com.example.APITaller.controller

import com.example.APITaller.error.exception.BadRequestException
import com.example.APITaller.model.Servicios
import com.example.APITaller.service.ServiciosService
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/servicios")
class ServiciosController {

    @Autowired
    private lateinit var serviciosService: ServiciosService

    @PostMapping
    fun postNewServicio(@RequestBody newServicio: Servicios): ResponseEntity<Servicios> {
        if (newServicio.nombre.isNullOrBlank() || newServicio.descripcion.isNullOrBlank()) {
            throw BadRequestException("El nombre y la descripción son obligatorios")
        }

        if (serviciosService.findServicioByName(newServicio.nombre) != null) {
            throw BadRequestException("Ya existe un servicio con este nombre")
        }

        val createdServicio = serviciosService.createServicio(newServicio)
        return ResponseEntity(createdServicio, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllServicios(): ResponseEntity<List<Servicios>> {
        val servicios = serviciosService.findAllServicios()
        return ResponseEntity(servicios, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getServicioById(@PathVariable id: String): ResponseEntity<Servicios> {
        val servicio = serviciosService.findServicioById(id) ?: throw BadRequestException("Servicio no encontrado")
        return ResponseEntity(servicio, HttpStatus.OK)
    }

    @GetMapping("/nombre/{nombre}")
    fun getServicioByName(@PathVariable nombre: String): ResponseEntity<Servicios> {
        val servicio = serviciosService.findServicioByName(nombre) ?: throw BadRequestException("Servicio no encontrado")
        return ResponseEntity(servicio, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateServicio(
        @PathVariable id: String,
        @RequestBody servicio: Servicios,
        authentication: Authentication
    ): ResponseEntity<Servicios> {
        val updatedServicio = serviciosService.updateServicio(id, servicio)
        return ResponseEntity(updatedServicio, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteServicioById(
        @PathVariable id: String,
        authentication: Authentication
    ): ResponseEntity<String> {
        serviciosService.findServicioById(id) ?: throw BadRequestException("Servicio no encontrado")
        if (!serviciosService.deleteServicio(id)) {
            throw BadRequestException("No se pudo eliminar el servicio")
        }
        return ResponseEntity("Servicio eliminado correctamente", HttpStatus.OK)
    }
}
