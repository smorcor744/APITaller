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
        @RequestBody cita: Citas
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @DeleteMapping("/{id}")
    fun deleteCitaById (
        @PathVariable id : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }
}