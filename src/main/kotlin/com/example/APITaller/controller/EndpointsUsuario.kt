package com.example.APITaller.controller

import com.example.APITaller.model.Usuario
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class EndpointsUsuario {

    @PostMapping
    fun postNewUsuario (
        @RequestBody newUsuario: Usuario
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping
    fun getAllUsuarios () : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @GetMapping("/{id}")
    fun getUsuarioById (
        @PathVariable id : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @PutMapping("/{id}")
    fun updateUsuario (
        @RequestBody usuario: Usuario
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @PutMapping("/{id}/password")
    fun updateUsuarioPassword (
        @RequestBody usuario: Usuario
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @DeleteMapping("/{id}")
    fun deleteUsuarioById (
        @PathVariable id : String
    ) : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }
}