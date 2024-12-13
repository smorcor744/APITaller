package com.example.APITaller.controller

import com.example.APITaller.model.Servicios
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/servicios")
class ServiciosController {

    @PostMapping
    fun postNewServicio (
        @RequestBody newServicios: Servicios
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping
    fun getAllServicios () : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping("/{id}")
    fun getServicioById (
        @PathVariable id : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping("?nombre={nombre}")
    fun getServicioByName (
        @PathVariable nombre : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @PutMapping("/{id}")
    fun updateServicio (
        @PathVariable id: Servicios
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @DeleteMapping("/{id}")
    fun deleteServicioById (
        @PathVariable id : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }
}