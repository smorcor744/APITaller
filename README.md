# Aplicación de Gestión de Taller de Coches

## Idea del Proyecto

**Aplicación de Gestión de Taller de Coches**

Esta aplicación está diseñada para facilitar la gestión de un taller de coches, permitiendo a los usuarios:
- Solicitar servicios de reparación y mantenimiento.
- Gestionar sus citas de manera eficiente.
- Visualizar servicios disponibles.
- Recibir actualizaciones sobre el estado de sus citas.

Por otro lado, el taller podrá:
- Gestionar las citas.
- Mantener un registro de los servicios prestados.

### Justificación del Proyecto
Facilitar la organización y gestión de servicios y citas en un taller de coches, permitiendo a los usuarios solicitar servicios y gestionar sus citas de manera sencilla y efectiva.

---

## Tablas a Gestionar

### Usuarios
- **ID**: Identificador único del usuario *(int, PK, auto-increment)*  
- **Nombre**: Nombre del usuario *(varchar, not null)*  
- **Email**: Correo electrónico del usuario *(varchar, unique, not null)*  
- **Contraseña**: Contraseña del usuario *(varchar, not null)*  
- **Fecha_Creación**: Fecha de creación del usuario *(datetime, default current_timestamp)*  

### Servicios
- **ID**: Identificador único del servicio *(int, PK, auto-increment)*  
- **Nombre_Servicio**: Nombre del servicio *(varchar, not null)*  
- **Descripción**: Descripción del servicio *(text)*  
- **Precio**: Precio del servicio *(decimal, not null)*  

### Citas
- **ID**: Identificador único de la cita *(int, PK, auto-increment)*  
- **ID_Usuario**: Identificador del usuario que solicita la cita *(int, FK, references Usuarios(ID))*  
- **ID_Servicio**: Identificador del servicio solicitado *(int, FK, references Servicios(ID))*  
- **Fecha_Cita**: Fecha y hora de la cita *(datetime, not null)*  
- **Estado**: Estado de la cita *(varchar, not null, default 'Pendiente')*  

---

## EndPoints

### **Usuarios**

#### Crear un usuario (Registro)
- **POST** `/usuarios/register`  
- **Acceso:** Público general. Permite a cualquier persona registrarse.

#### Obtener todos los usuarios
- **GET** `/usuarios`  
- **Acceso:** Solo administradores.

#### Obtener un usuario por ID
- **GET** `/usuarios/{id}`  
- **Acceso:** Usuarios autenticados (solo pueden acceder a su propia información).  
  *(Validación: El ID del token debe coincidir con el ID solicitado).*

#### Obtener todas las citas de un usuario
- **GET** `/usuarios/{id}/citas`  
- **Acceso:** Usuarios autenticados.

#### Actualizar un usuario
- **PUT** `/usuarios/{id}`  
- **Acceso:** Usuarios autenticados (solo pueden modificar su propia información).

#### Eliminar un usuario
- **DELETE** `/usuarios/{id}`  
- **Acceso:** Solo administradores.

---

### **Servicios**

#### Crear un servicio
- **POST** `/servicios`  
- **Acceso:** Solo administradores.

#### Obtener todos los servicios
- **GET** `/servicios`  
- **Acceso:** Público general.

#### Obtener un servicio por ID
- **GET** `/servicios/{id}`  
- **Acceso:** Público general.

#### Actualizar un servicio
- **PUT** `/servicios/{id}`  
- **Acceso:** Solo administradores.

#### Eliminar un servicio
- **DELETE** `/servicios/{id}`  
- **Acceso:** Solo administradores.

---

### **Citas**

#### Crear una cita
- **POST** `/citas`  
- **Acceso:** Usuarios autenticados.

#### Obtener todas las citas
- **GET** `/citas`  
- **Acceso:** Solo administradores.

#### Obtener una cita por ID
- **GET** `/citas/{id}`  
- **Acceso:** Usuarios autenticados (solo pueden acceder a sus propias citas).

#### Actualizar una cita
- **PUT** `/citas/{id}`  
- **Acceso:** Usuarios autenticados (propias citas) y administradores.

#### Eliminar una cita
- **DELETE** `/citas/{id}`  
- **Acceso:** Usuarios autenticados (propias citas) y administradores.

---

### **Autenticación**

#### Iniciar sesión
- **POST** `/auth/login`  
- **Acceso:** Público general. Devuelve un token JWT.

#### Actualizar contraseña
- **PUT** `/usuarios/{id}/contraseña`  
- **Acceso:** Usuarios autenticados.

---

## Lógica de Negocio

### Gestión de Usuarios
- Registro con correos únicos.  
- Contraseñas cifradas con **BCrypt**.  
- Los usuarios no pueden cambiar su rol.  

### Gestión de Servicios
- Solo los administradores pueden crear, actualizar o eliminar servicios.  
- Los servicios están disponibles públicamente.  

### Gestión de Citas
- Los usuarios autenticados pueden crear citas seleccionando un servicio y una fecha/hora disponible.  
- Los administradores pueden modificar el estado de una cita (e.g., "Pendiente", "En Proceso", "Completada").  

### Restricciones de Acceso
- Validación en cada endpoint para asegurar permisos adecuados.  
- Usuarios solo pueden acceder a sus propios datos.  
- Los administradores tienen acceso total.  

---

## Excepciones y Códigos de Estado

### Usuarios
- **400 Bad Request:** Datos inválidos.  
- **401 Unauthorized:** Usuario no autenticado.  
- **404 Not Found:** Usuario no encontrado.  

### Servicios
- **400 Bad Request:** Datos inválidos.  
- **401 Unauthorized:** Usuario no autenticado.  
- **404 Not Found:** Servicio no encontrado.  

### Citas
- **400 Bad Request:** Datos inválidos.  
- **401 Unauthorized:** Usuario no autenticado.  
- **404 Not Found:** Cita no encontrada.  

### Autenticación
- **401 Unauthorized:** Credenciales inválidas.  

---

## Restricciones de Seguridad

### Cifrado
- Contraseñas cifradas con **BCrypt**.  
- Uso de **JWT** con claves asimétricas (RSA).  

### Autenticación
- Los tokens incluyen roles y expiran automáticamente.  

### Autorización
- Validación de roles en cada endpoint.  

### Protección contra ataques
- Validación de entrada para prevenir inyección SQL y XSS.  
- Uso de **HTTPS** para proteger los datos en tránsito.  

---

## Pruebas

### Funcionales
- Registro, login y obtención de token JWT.  
- CRUD de usuarios, servicios y citas.  
- Validación de permisos según roles.  

### Seguridad
- Acceso no autorizado a recursos.  
- Verificación de contraseñas cifradas.  

### Errores
- Registro con correos duplicados.  
- Solicitudes con datos inválidos.  

---

## Documentación Adicional

### Tecnologías Utilizadas
- **Spring Boot:** Desarrollo de la API REST.  
- **Spring Security:** Gestión de autenticación y autorización.  
- **JWT:** Autenticación basada en tokens.  
- **MySQL:** Base de datos relacional.  
- **Postman:** Pruebas de los endpoints.  
- **BCrypt:** Cifrado de contraseñas.  

### Principios REST Aplicados
- **Uniform Interface:** Métodos estándar HTTP (GET, POST, PUT, DELETE).  
- **Statelessness:** Cada solicitud incluye toda la información necesaria.  
- **Layered System:** Separación entre cliente y servidor.  

---

## Ventajas de la Separación Cliente-Servidor
- **Escalabilidad:** Cliente y servidor pueden escalarse independientemente.  
- **Reutilización:** La API puede ser utilizada por múltiples plataformas.  
- **Mantenimiento:** Facilita la actualización de cada componente.  
