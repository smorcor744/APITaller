package com.example.APITaller.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rutas_publicas")
class RutaPublicaController {


    @GetMapping("/recurso1")
    fun getRecursoPublicoUno () : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }

    @PostMapping("/recurso3")
    fun postRecursoPublicoUno () : String {
        return "Este recurso puede ser accedido por cualquiera, es público \uD83D\uDE0E"
    }



}