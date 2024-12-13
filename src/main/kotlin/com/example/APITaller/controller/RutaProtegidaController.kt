package com.example.APITaller.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rutas_protegidas")
class RutaProtegidaController {


    @GetMapping("/recurso1")
    fun getRecursoProtegidoUno () : String {
        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"
    }

    @GetMapping("/recurso2")
    fun postRecursoProtegidoDos() : String{
        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"

    }

    @GetMapping("/recurso/{id}")
    fun getRecursoProtegidoId(@PathVariable id : String) : String{
        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"

    }

    @DeleteMapping("/recurso/{id}")
    fun deleteRecursoProtegidoId(@PathVariable id : String) : String{
        return "Este recurso sólo puede ser accedido por usuarios registrados en la BDD \uD83E\uDD75"

    }

    @GetMapping("/usuario_autenticado")
    fun saludarUsuarioAutenticdo(authentication: Authentication) : String{

        // OBJETIVO SALUDAR AL USUARIO AUTENTICADO
        return "Hola ${authentication.name}."
    }

    @DeleteMapping("/eliminar/{nombre}")
    fun deleteUsuarioProtegidoId(@PathVariable nombre : String ,authentication: Authentication) : String{
        if (authentication.name == nombre) {
            return "${authentication.name} a eliminado $nombre \uD83E\uDD75"
        } else if (authentication.authorities.any { it.authority == "ROLE_ADMIN" } )
        {
            return "${authentication.name} a eliminado $nombre \uD83E\uDD75"
        }
        return "Usted no esta autorizado para realizar esto."

    }

}