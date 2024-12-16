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

## Campos y Tipos de Datos

### Usuarios
- **ID**
- **Nombre**
- **Email**
- **Contraseña**
- **Fecha_Creación**

### Servicios
- **ID**
- **Nombre_Servicio**
- **Descripción**
- **Precio**

### Citas
- **ID**
- **ID_Usuario**
- **ID_Servicio**
- **Fecha_Cita**
- **Estado**

---

## EndPoints

### **Usuarios**

#### Crear un usuario (Registro)
- **POST** `/usuarios/register`
- **Acceso:** Todos (público general). Permitir a cualquier persona registrarse.

#### Obtener todos los usuarios
- **GET** `/usuarios`
- **Acceso:** Administradores. Solo los administradores deberían tener acceso a esta información sensible.

#### Obtener un usuario por ID
- **GET** `/usuarios/{id}`
- **Acceso:** Usuarios autenticados. Los usuarios solo deberían acceder a su propia información.
  *(Ejemplo: Validar que el id del token coincide con el id del recurso solicitado).*

  #### Obtener todas las citas de los usuarios
- **GET** `/usuarios/{id}/citas`
- **Acceso:** Usuarios autenticados. Los usuarios solo deberían acceder a su propia información.

#### Actualizar un usuario
- **PUT** `/usuarios/{id}`
- **Acceso:** Usuarios autenticados. Cada usuario puede actualizar su propia información.

#### Eliminar un usuario
- **DELETE** `/usuarios/{id}`
- **Acceso:** Administradores. Solo los administradores pueden eliminar cuentas.

---

### **Servicios**

#### Crear un servicio
- **POST** `/servicios`
- **Acceso:** Administradores. Solo el administrador puede crear nuevos servicios.

#### Obtener todos los servicios
- **GET** `/servicios`
- **Acceso:** Todos. La lista de servicios debería estar disponible para cualquier usuario, autenticado o no.

#### Obtener un servicio por ID
- **GET** `/servicios/{id}`
- **Acceso:** Todos. Similar al punto anterior, cualquier usuario puede consultar los detalles de un servicio.

#### Actualizar un servicio
- **PUT** `/servicios/{id}`
- **Acceso:** Administradores. Solo los administradores pueden modificar servicios.

#### Eliminar un servicio
- **DELETE** `/servicios/{id}`
- **Acceso:** Administradores. Solo los administradores pueden eliminar servicios.

---

### **Citas**

#### Crear una cita
- **POST** `/citas`
- **Acceso:** Usuarios autenticados. Solo usuarios autenticados pueden solicitar una cita.

#### Obtener todas las citas
- **GET** `/citas`
- **Acceso:** Administradores. Solo los administradores pueden ver todas las citas.

#### Obtener una cita por ID
- **GET** `/citas/{id}`
- **Acceso:** Usuarios autenticados. Los usuarios solo pueden acceder a sus propias citas, y los administradores pueden acceder a cualquier cita.

#### Actualizar una cita
- **PUT** `/citas/{id}`
- **Acceso:** Usuarios autenticados. Los usuarios pueden modificar sus propias citas (por ejemplo, cambiar la fecha).  
  **Administradores** pueden modificar cualquier cita.

#### Eliminar una cita
- **DELETE** `/citas/{id}`
- **Acceso:** Usuarios autenticados (propias citas) y Administradores (todas las citas).

#### Obtener citas de un usuario específico
- **GET** `/usuarios/{id}/citas`
- **Acceso:** Usuarios autenticados. Los usuarios solo pueden ver sus propias citas. Los administradores pueden consultar cualquier usuario.

---

### **Endpoints adicionales**

#### Autenticación (Login)
- **POST** `/auth/login`
- **Acceso:** Todos (público general). Necesario para que cualquier usuario obtenga un token.

#### Actualizar contraseña
- **PUT** `/usuarios/{id}/contraseña`
- **Acceso:** Usuarios autenticados. Cada usuario puede cambiar su propia contraseña.

### **Lógica de negocio**

#### Gestión de usuarios:

- Los usuarios deben registrarse con un correo electrónico único.
- Las contraseñas se almacenan cifradas usando un algoritmo de hash seguro (como BCrypt).
- Los usuarios pueden actualizar su información, pero no pueden cambiar su rol.
  
#### Gestión de servicios:

- Solo los administradores pueden agregar, actualizar o eliminar servicios.
- Los servicios están disponibles públicamente para que los usuarios puedan consultarlos.
  
#### Gestión de citas:

- Los usuarios autenticados pueden crear citas seleccionando un servicio y una fecha/hora disponible.
- Las citas solo pueden ser actualizadas o canceladas por el usuario que las creó o por un administrador.
- El estado de una cita puede ser actualizado por un administrador para reflejar su progreso (e.g., "Pendiente", "En Proceso", "Completada").
  
#### Restricciones de acceso:

- Los usuarios solo pueden acceder a sus propios datos y citas.
- Los administradores tienen acceso completo a todas las entidades.

### **Excepciones y códigos de estado**

#### Usuarios:

- 400 Bad Request: Datos inválidos en el registro o actualización.
- 401 Unauthorized: Usuario no autenticado.
- 404 Not Found: Usuario no encontrado.

#### Servicios:

- 400 Bad Request: Datos inválidos en el registro o actualización.
- 401 Unauthorized: Usuario no autenticado.
- 404 Not Found: Usuario no encontrado.
  
#### Citas:

- 400 Bad Request: Datos inválidos en el registro o actualización.
- 401 Unauthorized: Usuario no autenticado.
- 404 Not Found: Usuario no encontrado.

#### Autenticación:

- 401 Unauthorized: Credenciales inválidas al iniciar sesión.
- 400 Bad Request: Datos inválidos en el registro o actualización.

### **Restricciones de seguridad**

## Cifrado:

- Contraseñas almacenadas con hash seguro (BCrypt).
- Uso de cifrado asimétrico (RSA) para las claves pública y privada en el manejo de tokens JWT.

## Autenticación:

- Uso de JWT para autenticar y autorizar a los usuarios.
- Los tokens incluyen información como el rol del usuario para validar permisos.

## Autorización:

# Restricciones de acceso basadas en roles:

- Los administradores tienen permisos completos.
- Los usuarios regulares solo pueden acceder/modificar sus propios datos y citas.
- Validación en cada endpoint para asegurar que el usuario autenticado tiene permisos para realizar la acción solicitada.

## Protección contra ataques comunes:

- Validación de entrada para prevenir ataques de inyección SQL y XSS.
- Uso de HTTPS para garantizar la seguridad de los datos en tránsito.

Pruebas
Pruebas funcionales:

Verificar el registro, inicio de sesión y obtención de token JWT.
Crear, actualizar y eliminar usuarios, servicios y citas.
Validar restricciones de acceso para usuarios y administradores.
Pruebas de seguridad:

Intentar acceder a datos de otros usuarios sin permisos.
Verificar la expiración y revocación de tokens JWT.
Validar que las contraseñas se almacenan de manera segura.
Pruebas de errores:

Intentar registrar usuarios con correos duplicados.
Probar endpoints con datos inválidos o incompletos.
Documentación adicional
Tecnologías utilizadas:

Spring Boot: Framework para el desarrollo de la API REST.
Spring Security: Gestión de autenticación y autorización.
JWT: Manejo de tokens para autenticación.
MySQL: Base de datos relacional para almacenar datos.
Postman: Pruebas de los endpoints de la API.
BCrypt: Hashing de contraseñas.
Principios de REST aplicados:

Uniform Interface: Uso de métodos HTTP estándar (GET, POST, PUT, DELETE).
Statelessness: Cada solicitud incluye toda la información necesaria para procesarla.
Layered System: Separación entre cliente y servidor.
Resource Representation: Los datos se exponen como recursos identificados por URI.

Ventajas de la separación cliente-servidor
Escalabilidad: Permite escalar el cliente y el servidor de manera independiente.
Reutilización: La API puede ser utilizada por múltiples clientes (web, móvil, etc.).
Mantenimiento: Facilita la actualización y el mantenimiento de cada componente.
