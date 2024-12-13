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
