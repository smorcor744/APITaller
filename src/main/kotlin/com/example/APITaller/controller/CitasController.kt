package com.example.APITaller.controller

import com.example.APITaller.model.Citas
import com.example.APITaller.model.Servicios
import com.example.APITaller.service.CitasService
import com.example.APITaller.service.UsuarioService
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/citas")
class CitasController {


    @Autowired
    private lateinit var usuarioService: UsuarioService

    @Autowired
    private lateinit var citasService: CitasService


    @PostMapping
    fun postNewCitas (
        @RequestBody newCita: Citas
    ) : ResponseEntity<Citas?>? {
        citasService.createCita(newCita)
        return ResponseEntity(newCita, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllCitas () : ResponseEntity<List<Citas>?>?  {
        val citas = citasService.findAllCitas()
        return ResponseEntity(citas, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCitaById (
        @PathVariable id : String,
        authentication: Authentication
    ) : ResponseEntity<Citas?>? {
        val usuario = usuarioService.findByName(authentication.name)
        var citas: Citas? = null

        if (usuario != null) {
            if (usuario.id == id.toLongOrNull() || usuario.roles == "ROLE_ADMIN") {

                citas = citasService.findCitaById(id)
            }
        }
        return ResponseEntity(citas, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateCita (
        @RequestBody cita: Citas,
        authentication: Authentication
    ) : ResponseEntity<Citas?>? {
        if (authentication.name != cita.usuario?.username || cita.usuario?.roles != "ROLE_ADMIN") {
            throw IllegalArgumentException("No puedes modificar una cita que no te pertenece")
        }
        citasService.updateCita(cita.id.toString(),cita)
        return ResponseEntity(cita, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCitaById (
        @PathVariable id : String,
        authentication: Authentication
    ) : ResponseEntity<String?>? {
        if (authentication.name != citasService.findCitaById(id)?.usuario?.username || citasService.findCitaById(id)?.usuario?.roles != "ROLE_ADMIN") {
            throw IllegalArgumentException("No puedes eliminar una cita que no te pertenece")
        }
        citasService.deleteCita(id)
        return ResponseEntity("Cita borrada correctamente", HttpStatus.OK)
    }
}