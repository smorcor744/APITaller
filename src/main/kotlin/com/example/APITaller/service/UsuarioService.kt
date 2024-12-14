package com.example.APITaller.service


import com.example.APITaller.model.Usuario
import com.example.APITaller.repository.UsuarioRepository
import org.springframework.security.core.Authentication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioService: UserDetailsService {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var usuarioRepository: UsuarioRepository


    /*
    TODO
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario: Usuario = usuarioRepository
            .findByUsername(username)
            .orElseThrow()
        return User
            .builder()
            .username(usuario.username)
            .password(usuario.password)
            .roles(usuario.roles)
            .build()
    }

    /*
    MÉTODO PARA INSERTAR UN USUARIO
     */
    fun registerUsuario(usuario: Usuario) : Usuario? {
        // Creamos la instancia de Usuario
        var newUsuario: Usuario? = null

        // Comprobamos que el usuario no existe en la base de datos
        if(usuarioRepository.findByUsername(usuario.username).isEmpty) {

            // Hasheamos la contraseña
            val hashedPassword = passwordEncoder.encode(usuario.password)

            // Creamos un nuevo usuario con la contraseña hasheada
            newUsuario = Usuario(null,usuario.username,hashedPassword,usuario.fecha_creacion,usuario.roles)

            // Lo guardamos en la bd
            usuarioRepository.save(newUsuario)
        }


        // Devolvemos el Usuario insertado en la BDD
        return newUsuario

    }

    fun findById(id: String) : Usuario? {

        // Creamos la instancia de Usuario
        val idLong = id.toLong() ?: return null

        val usuario: Usuario? = usuarioRepository.findById(idLong).orElse(null)

        // Devolvemos el Usuario
        return usuario

    }

    fun findByName(name: String?) : Usuario? {

        if(name.isNullOrBlank()){
            return null
        }
        // Creamos la instancia de Usuario
        val usuario: Usuario? = usuarioRepository.findByUsername(name).orElse(null)

        // Devolvemos el Usuario
        return usuario

    }



    fun getAll() : List<Usuario>? {

        // Creamos la instancia de Usuario

        val usuarios: List<Usuario> = usuarioRepository.findAll()

        // Devolvemos el Usuario
        return usuarios

    }

    fun updateUsuario(usuario: Usuario,authentication: Authentication) : Usuario? {

        // Creamos la instancia de Usuario

        val usuarioEmisor = usuarioRepository.findByUsername(authentication.name).get()

        if(usuarioEmisor.id == usuario.id || usuarioEmisor.roles == "ROLE_ADMIN"){

            // Guardamos el usuario actualizado en la bd
            return usuarioRepository.save(usuario)
        }

        // Devolvemos null
        return null

    }

    fun updateUsuarioPass(usuario: Usuario,pass: String,authentication: Authentication) : Usuario? {

        // Creamos la instancia de Usuario

        val usuarioEmisor = usuarioRepository.findByUsername(authentication.name).get()
        val nuevoUsuario = Usuario(usuario.id,usuario.username,pass,usuario.fecha_creacion,usuario.roles)


        if(usuarioEmisor.id == usuario.id || usuarioEmisor.roles == "ROLE_ADMIN"){

            // Guardamos el usuario actualizado en la bd
            return usuarioRepository.save(nuevoUsuario)
        }

        // Devolvemos null
        return null

    }

    fun deleteUsuario(id: String,authentication: Authentication) : Boolean {

        // Creamos la instancia de Usuario
        val usuarioEmisor = usuarioRepository.findByUsername(authentication.name).get()
        val idLong = id.toLongOrNull() ?: return false

        if(usuarioEmisor.id == idLong || usuarioEmisor.roles == "ROLE_ADMIN"){

            // Guardamos el usuario actualizado en la bd
            usuarioRepository.deleteById(idLong) ?: return false
            return true
        }

        // Devolvemos null
        return false

    }




}