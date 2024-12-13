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
- **POST** `/usuarios`
- **Acceso:** Todos (público general). Permitir a cualquier persona registrarse.

#### Obtener todos los usuarios
- **GET** `/usuarios`
- **Acceso:** Administradores. Solo los administradores deberían tener acceso a esta información sensible.

#### Obtener un usuario por ID
- **GET** `/usuarios/{id}`
- **Acceso:** Usuarios autenticados. Los usuarios solo deberían acceder a su propia información.
  *(Ejemplo: Validar que el id del token coincide con el id del recurso solicitado).*

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

