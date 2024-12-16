package com.example.APITaller.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
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
    @JsonIgnoreProperties(value=["hibernateLazyInitializer", "handler", "fieldHandler"])
    var usuario: Usuario? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio", nullable = false)
    @JsonIgnoreProperties(value=["hibernateLazyInitializer", "handler", "fieldHandler"])
    var servicio: Servicios? = null,

    @Column(/*nullable = false,*/ name = "fecha_cita")
    var fechaCita: Date? = null,

    @Column(nullable = false)
    var estado: String? = "Pendiente"
)
{
}