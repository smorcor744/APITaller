package com.example.APITaller.controller

import com.example.APITaller.error.exception.BadRequestException
import com.example.APITaller.model.Servicios
import com.example.APITaller.service.ServiciosService
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
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
    fun postNewServicio (
        @RequestBody newServicios: Servicios
    ) : ResponseEntity<Servicios?>? {
        if (newServicios.nombre.isNullOrBlank() && newServicios.descripcion.isNullOrBlank()) {
            throw BadRequestException("El nombre y la descripción son obligatorios")

        }else if (serviciosService.findServicioByName(newServicios.nombre) != null) {

            throw BadRequestException("Ya existe un servicio con este nombre")
        }
        serviciosService.createServicio(newServicios)
        return ResponseEntity(newServicios, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllServicios ()
    : ResponseEntity<List<Servicios>> {
        val servicios = serviciosService.findAllServicios()
        return ResponseEntity(servicios, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getServicioById (
        @PathVariable id : String
    )  : ResponseEntity<Servicios?>? {
        val servicios = serviciosService.findServicioById(id)
        return ResponseEntity(servicios, HttpStatus.CREATED)
    }

    @GetMapping("?nombre={nombre}")
    fun getServicioByName (
        @PathVariable nombre : String
    ) : ResponseEntity<Servicios?>? {
        val servicios = serviciosService.findServicioByName(nombre)
        return ResponseEntity(servicios, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateServicio (
        @PathVariable id: String,
        authentication: Authentication
    ) : ResponseEntity<Servicios?>? {
        val servicio = serviciosService.findServicioByName(authentication.name)
            ?: throw BadRequestException("No existe un servicio con ese nombre")

        val servicios = serviciosService.updateServicio(id,servicio)

        return ResponseEntity(servicios, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteServicioById (
        @PathVariable id : String,
        authentication: Authentication
    ) : ResponseEntity<String?>? {
        serviciosService.findServicioById(id) ?: throw BadRequestException("No existe un servicio con ese id")
        val servicio = serviciosService.deleteServicio(id)
        if (!servicio) {
            throw BadRequestException("No se pudo eliminar el servicio")
        }
        return ResponseEntity("Servicio eliminado correctamente", HttpStatus.OK)
    }
}