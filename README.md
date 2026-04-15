KinalApp
Descripción del Proyecto

KinalApp es una aplicación web desarrollada con tecnologías modernas del ecosistema Java utilizando Spring Boot. Su propósito principal es gestionar el ciclo completo de ventas de un negocio, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre entidades clave como:

Clientes
Productos
Usuarios
Ventas
Detalle de ventas
La aplicación implementa una arquitectura basada en el patrón MVC (Modelo - Vista - Controlador) e integra tanto backend como frontend por medio de  Thymeleaf.

Análisis de los Componentes
Stack Tecnológico: Utiliza las versiones más recientes y estables del mercado. Java 21 ofrece las últimas mejoras de rendimiento, mientras que Spring Boot 4 facilita la configuración automática y el despliegue rápido.
Arquitectura de Datos: Se apoya en MySQL para el almacenamiento de datos y Maven para manejar todas las librerías necesarias.
Estructura de Endpoints: La aplicación sigue una lógica de recursos muy clara:
Gestión de Inventario: Control de productos y monitoreo de stock.
Gestión de Ventas: Registro detallado de transacciones (Venta y DetalleVenta), lo cual indica que puede manejar múltiples productos por cada factura.
Seguridad/Control: Gestión de usuarios y estados de actividad (filtros para ver solo registros "activos").
Arquitectura de Datos
El sistema utiliza MySQL como base de datos relacional y está estructurado en capas:

Entity → Representación de tablas
Repository → Acceso a datos 
Service → Lógica de negocio
Controller → Manejo de peticiones HTTP
Flujo de Trabajo (Instalación)
El proceso descrito es el estándar para un desarrollador:

Preparación del entorno: Asegurar que el motor de base de datos y el kit de desarrollo (JDK) estén listos.
Sincronización: Clonar el código desde el repositorio de GitHub.
Configuración: Es vital el paso de revisar el archivo application.properties, ya que ahí se define la conexión a la base de datos y el puerto de red (en este caso, el 8081).
Pruebas: Usar el navegador para interactuar con los datos a través de las URLs.
Tecnologias utilizadas
Java 21
Spring Boot 4.0.2
Maven (Gestor de dependencias)
MySQL (Base de Datos)
Requisitos Previos
Antes de ejecutar la aplicación debe tener instalado: * JDK 17 o superior * Maven instalado * Una instancia activa en MySQL

Instalaciones opcionales
Postman
Instalación y Ejecución
Clonar repositorio https://github.com/Jambrocio-2025444/KinalApp-1.0.git
Abrir Intellij IDEA.
Abrir la carpeta que clono.
Abrir MySQL en su ordenador.
Regresar a Intellij IDEA.
Dirigirse a la carpeta "src\main\java\com\joseambrocio".
Dirirgirse a KinalAppApplication y ejecutar la aplicación.
Abrir la carpeta "resources/application.properties".
Verificar que puerto está utilizando la aplicación.
Abrir el navegador y poner el puerto http://localhost:8081/
Aparecera un Login el cual es para registrar un usuario(en caso de no tener uno se puede crear)
Endpoints
**************************************************
clientes
/clientes → lista todos los clientes existentes
/clientes/NuevoCliente → Crea un nuevo cliente
/clientes/BuscarCliente → Busca el dpi del cliente
/clientes/buscar → El resultado si el cliente existe
/clientes/editar/  → Editar al cliente existente
/clientes/activos → Los clientes que estan activos
*****************************************************
usuarios
/usuarios → lista todos los usuarios existentes
/usuarios/NuevoUsuario → Crea un nuevo usuario
/usuarios/BuscarUsuario → Busca el id (código) del usuario
/usuarios/buscar  → El resultado si el cliente existe
/usuarios/editar/ → Edita un usuario existente
*********************************************************
productos
/productos → lista todos los prodcutos existentes
/productos/NuevoProducto → Crea un nuevo producto
/productos/BuscarProducto → Busca el id (código) del producto
/productos/buscar → El resultado si el producto existe
/productos/editar/  → Editar al producto existente
/productos/activos → Los productos que estan activos
************************************************************
ventas
/ventas → lista todos los ventas existentes
/ventas/NuevaVenta → Crea una nueva venta
/clientes/BuscarVenta → Busca el id (código) de la venta
/ventas/buscar → El resultado si la venta si existe
/ventas/activos → Las ventas que estan activas
***************************************************************
detalles
/detalles → lista todos los clientes existentes
/detalles/NuevoDetalle → Crea un nuevo detalle de venta
/detalles/BuscarDetalle → Busca el id (código) del detalle de la venta
/detalles/buscar → El resultado el detalle de la venta es existente
***********************************************************************


<img width="1918" height="936" alt="image" src="https://github.com/user-attachments/assets/1efc6fc7-f41a-4253-bed2-be99d78674b7" />
<img width="1916" height="888" alt="image" src="https://github.com/user-attachments/assets/a67c313f-b109-4e7a-8646-7b93ee1a8769" />
<img width="1918" height="938" alt="image" src="https://github.com/user-attachments/assets/5e75d0c6-902a-4417-bc2b-e4a4e4fb82c0" />
<img width="1918" height="966" alt="image" src="https://github.com/user-attachments/assets/6b3795ea-96b1-4b10-990b-c792e03a7457" />
<img width="1918" height="972" alt="image" src="https://github.com/user-attachments/assets/96f74e91-0649-4598-879e-5dcc653a9e78" />




