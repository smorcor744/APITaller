package com.example.APITaller.controller

import com.example.APITaller.error.exception.NotAuthorizedException
import com.example.APITaller.error.exception.NotFoundException
import com.example.APITaller.model.Citas
import com.example.APITaller.service.CitasService
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/citas")
class CitasController {

    @Autowired
    private lateinit var citasService: CitasService

    @PostMapping
    fun postNewCitas(@RequestBody newCita: Citas): ResponseEntity<Citas> {

        val cita = citasService.createCita(newCita)
        return ResponseEntity(cita, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllCitas(): ResponseEntity<List<Citas>> {
        val citas = citasService.findAllCitas()
        return ResponseEntity(citas, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getCitaById(
        @PathVariable id: String,
        authentication: Authentication): ResponseEntity<Citas> {
        val cita = citasService.findCitaById(id) ?: throw NotFoundException("Cita no encontrada")
         return ResponseEntity(cita, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateCita(
        @PathVariable id: String,
        @RequestBody cita: Citas,
        authentication: Authentication): ResponseEntity<Citas> {
        val citaOld = citasService.findCitaById(id) ?: throw NotFoundException("Cita no encontrada")

        if (!hasAccess(authentication, citaOld)) {
            throw NotAuthorizedException("No puedes modificar esta cita")
        }
        val updatedCita = citasService.updateCita(id, cita)
        return ResponseEntity(updatedCita, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCitaById(
        @PathVariable id: String,
        authentication: Authentication
    ): ResponseEntity<String> {
        val cita = citasService.findCitaById(id) ?: throw NotFoundException("Cita no encontrada")
        if (!hasAccess(authentication, cita)) {
            throw NotAuthorizedException("No puedes eliminar esta cita")
        }
        citasService.deleteCita(id)
        return ResponseEntity("Cita eliminada correctamente", HttpStatus.OK)
    }

    private fun hasAccess(authentication: Authentication, cita: Citas): Boolean {
        return authentication.name == cita.usuario?.username || cita.usuario?.roles == "ROLE_ADMIN"
    }
}
