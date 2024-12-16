
-- Insertar un usuario
    INSERT INTO `usuarios`(`id`, `password`, `roles`, `username`) VALUES (1,'$2a$10$DBc2FPq.4XperQMRTGpYnufwdTFxFCJtRZj1zsX.7vFo9YVe9rCyW','ADMIN','diego');

-- Insertar un servicio
INSERT INTO `servicios`(`id`, `nombre_servicio`, `descripcion`, `precio`)
VALUES (1, 'Mantenimiento', 'Mantenimiento Simple', 50.00);

-- Insertar una cita
INSERT INTO `citas`(`id`, `id_usuario`, `id_servicio`, `fecha_cita`, `estado`)
VALUES (1, 1, 1, '2024-12-15 10:00:00', 'Pendiente');
