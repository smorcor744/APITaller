package com.example.APITaller.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rutas_extra_confidenciales")
class SecretosExtraConfidenciales {

    @GetMapping("/ficha1")
    fun getConfidenciales() : String{

        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"
    }

    @GetMapping("/ficha2")
    fun getConfidencialesPublicos() : String{

        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"
    }
}