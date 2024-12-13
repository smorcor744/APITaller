package com.example.APITaller.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "citas")
class Citas(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    var usuario: Usuario? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    var servicio: Servicios? = null,

    @Column(nullable = false)
    var fecha_cita: Date? = null,

    @Column(nullable = false)
    var estado: String? = "Pendiente"
)
