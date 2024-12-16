package com.example.APITaller.controller

import com.example.APITaller.error.exception.BadRequestException
import com.example.APITaller.model.Citas
import com.example.APITaller.model.Usuario
import com.example.APITaller.service.CitasService
import com.example.APITaller.service.TokenService
import com.example.APITaller.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    private lateinit var usuarioService: UsuarioService
    @Autowired
    private lateinit var citasService: CitasService
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
        if (newUsuario.username.isNullOrBlank() && newUsuario.password.isNullOrBlank()) {
            throw BadRequestException("El username y la contraseña son obligatorios")
        }


        // Llamar al UsuarioService para insertar un usuario
        usuarioService.registerUsuario(newUsuario)

        // Devolver el usuario insertado
        return ResponseEntity(newUsuario, HttpStatus.CREATED)

    }

    @GetMapping("/{id}/citas")
    fun getCitasUsuario(
        @PathVariable id: String,
        authentication: Authentication
    ): ResponseEntity<List<Citas>> {
        // Validar que el ID es numérico
        val idUsuario = id.toLongOrNull()
            ?: throw BadRequestException("El ID del usuario debe ser un número válido.")
        val usuario = usuarioService.findById(idUsuario.toString())

        // Verificar que el usuario autenticado tiene acceso
        if (usuario != null) {
            if (usuario.username != authentication.name && usuario.roles != "ROLE_ADMIN") {
                throw IllegalArgumentException("No tienes permiso para acceder a las citas de este usuario.")
            }
            // Obtener las citas del usuario
            val citas = citasService.findCitasByUsuarioId(id)
            if (citas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(emptyList())
            }

            // Devolver las citas
            return ResponseEntity.ok(citas)
        }

        throw BadRequestException("Usuario no encontrado.")
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
        var token = tokenService.generarToken(authentication)

        println(authentication)

        return ResponseEntity(mapOf("token" to token),HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllUsuarios () : ResponseEntity<List<Usuario>?>? {
        val usuarios = usuarioService.getAll()

        return ResponseEntity(usuarios, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUsuarioById (
        @PathVariable id : String,
        authentication: Authentication
    ) : ResponseEntity<Usuario?>? {
        
        val usuario = usuarioService.findById(id)
        if (usuario != null) {
            if (usuario.username != authentication.name && usuario.roles != "ROLE_ADMIN") {
                throw IllegalArgumentException("No puedes acceder a otro usuario")
            }
        }

        
        return ResponseEntity(usuario, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateUsuario (
        @PathVariable id : String,
        @RequestBody usuario: Usuario,
        authentication: Authentication
    ) : ResponseEntity<Usuario?>? {
        val usuarioActualizar: Usuario? = usuarioService.findById(id)
        
        if (usuarioActualizar == null) {
            return ResponseEntity(null, HttpStatus.NOT_FOUND)
        }else if(authentication.name == usuario.username || usuario.roles == "ROLE_ADMIN"){
            usuarioService.updateUsuario(usuario,authentication)
        }

        return ResponseEntity(usuario, HttpStatus.OK)
    }

    @PutMapping("/{id}/{password}")
    fun updateUsuarioPassword(
        @PathVariable id: String,
        @PathVariable password: String,
        authentication: Authentication
    ): ResponseEntity<String> {
        // Buscar al usuario por ID
        val usuario = usuarioService.findById(id)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")

        if (usuario.username == authentication.name) {
            // Actualizar la contraseña
            usuarioService.updateUsuarioPass(usuario,password,authentication)
        }

        return ResponseEntity.ok("Contraseña actualizada exitosamente")
    }

    @DeleteMapping("/{id}")
    fun deleteUsuarioById (
        @PathVariable id : String,
        authentication: Authentication
    ) : ResponseEntity<String> {

        if (usuarioService.deleteUsuario(id,authentication)){

            return ResponseEntity.ok("Usuario borrado exitosamente")
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado")
    }

}