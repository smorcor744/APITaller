package com.example.APITaller.model

import jakarta.persistence.*

@Entity
@Table(name = "servicios")
class Servicios (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var nombre: String? = null,

    @Column
    var descripcion: String? = null,

    @Column(nullable = false)
    var precio: Long? = null,

)