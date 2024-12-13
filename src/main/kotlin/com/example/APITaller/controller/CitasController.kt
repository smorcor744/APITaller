package com.example.APITaller.controller

import com.example.APITaller.model.Citas
import org.apache.tomcat.util.net.openssl.ciphers.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/citas")
class CitasController {
    @PostMapping
    fun postNewCitas (
        @RequestBody newCita: Citas
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping
    fun getAllCitas () : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping("/{id}")
    fun getCitaById (
        @PathVariable id : String,
        authentication: Authentication
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
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