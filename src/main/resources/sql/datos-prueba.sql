INSERT INTO `usuarios`(`id`, `nombre`, `contraseña`, `fecha_creación`)
VALUES (1, 'Diego', '$2a$10$DBc2FPq.4XperQMRTGpYnufwdTFxFCJtRZj1zsX.7vFo9YVe9rCyW', CURRENT_TIMESTAMP);

INSERT INTO `servicios`(`id`, `nombre_servicio`, `descripcion`, `precio`)
VALUES (1, 'Mantenimiento', 'Mantenimiento Simple', 50.00);

INSERT INTO `citas`(`id`, `id_usuario`, `id_servicio`, `fecha_cita`, `estado`)
VALUES (1, 1, 1, '2024-12-15 10:00:00', 'Pendiente');
