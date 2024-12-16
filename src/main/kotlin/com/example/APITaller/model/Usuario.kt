package com.example.APITaller.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var username: String? = null,

    @Column(nullable = false)
    var password: String? = null,//'$2a$10$DBc2FPq.4XperQMRTGpYnufwdTFxFCJtRZj1zsX.7vFo9YVe9rCyW'


    var roles: String? = null // e.g., "ROLE_USER,ROLE_ADMIN"

)
