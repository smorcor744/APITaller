package com.example.APITaller.controller

import com.example.APITaller.model.Usuario
import com.example.APITaller.service.TokenService
import com.example.APITaller.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var tokenService: TokenService

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    @PostMapping("/register")
    fun register(
        @RequestBody newUsuario: Usuario
    ) : ResponseEntity<Usuario?>? {

        // Comprobación mínima
        // -> La obviamos por ahora

        // Llamar al UsuarioService para insertar un usuario
        usuarioService.registerUsuario(newUsuario)

        // Devolver el usuario insertado
        return ResponseEntity(newUsuario, HttpStatus.CREATED) // Cambiar null por el usuario insertado

    }


    /*
    METODO (ENDPOINT) PARA HACER UN LOGIN
     */

    @PostMapping("/login")
    fun login(@RequestBody usuario: Usuario) : ResponseEntity<Any>? {
        val authentication : Authentication
        try {
            authentication  = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(usuario.username,usuario.password))

        } catch (e: AuthenticationException){
            return ResponseEntity(mapOf("mensaje" to "Credenciales incorrectas dude"), HttpStatus.UNAUTHORIZED)
        }

        // SI PASAMOS LA AUTENTIFICACION, SIGNIFICA QUE ESTAMOS BIEN AUTENTICADOS
        // PASAMOS A GENERAR EL TOKEN
        var token = ""
        token = tokenService.generarToken(authentication)

        println(authentication)

        return ResponseEntity(mapOf("token" to token),HttpStatus.CREATED)
    }

}