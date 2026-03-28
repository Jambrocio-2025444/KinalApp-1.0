# KinalApp
 
Documentación del Proyecto
En el sigueinte proyecto se realizo una API REST construida con tecnologías modernas. Su principla función es gestionar el ciclo de ventas de un negocio, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre entidades importantes como clientes, usuarios, productos, ventas y detalleVentas.

Análisis de los Componentes
* Herramientas Principales: Usamos las versiones más modernas de Java y Spring Boot. Esto hace que el programa sea rápido, seguro y más fácil de programar.
* Base de Datos y Organización: Toda la información se guarda en MySQL (como una gran hoja de Excel). Usamos Maven para que se encargue de descargar y   organizar todas las herramientas extra que el código necesita.
* Estructura de Endpoints: La aplicación sigue una lógica de recursos muy clara:
* Gestina el inventario: Control de productos y monitoreo de stock.
* Gestiona las Ventas: Registro detallado de transacciones (Venta y DetalleVenta), lo cual indica que puede manejar múltiples productos por cada factura.
* Control: Gestión de usuarios y estados de actividad (filtros para ver solo registros "activos").

Flujo de Trabajo (Instalación)
El proceso descrito es el estándar para un desarrollador:

* Preparación del entorno: Asegurar que el motor de base de datos y el kit de desarrollo (JDK) estén listos.
* Sincronización: Clonar el código desde el repositorio de GitHub.
* Configuración: Es vital el paso de revisar el archivo application.properties, ya que ahí se define la conexión a la base de datos y el puerto de red (en este caso, el 8081).
* Pruebas: Se sugiere el uso de Postman o el navegador para interactuar con los datos a través de las URLs (por ejemplo, para ver la lista de clientes).

## Tecnologias utilizadas
* **Java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL** (Sistema gestor de Base de Datos)

## Requisitos Previos
Antes de ejecutar la aplicación debe tener instalado:
* JDK 17 o superior
* Maven instalado
* Una instancia activa en MySQL

Instalaciones opcionales
* Postman

## Instalación y Ejecución 
1. Clonar repositorio https://github.com/Jambrocio-2025444/KinalApp-1.0.git
2. Abrir Intellij IDEA.
3. Abrir la carpeta que clono.
4. Abrir MySQL en su ordenador.
5. Ingresar a la instancia activa en MySQL.
6. Regresar a Intellij IDEA.
7. Dirigirse a la carpeta "\src\main\java\com\joseAmbrocio".
11. Dirirgirse a KinalAppApplication y ejecutar la aplicación.
12. Abrir la carpeta "resources/application.properties".
13. Ingresar el nombre y contraseña de tu usuario en MySQL donde dice "spring.datasource.username, spring.datasource.password".
12. Abrir el navegador y poner el puerto http://localhost:8081/clientes.

## Endpoints 
* Cliente: 
1. "/clientes": Esto lista a todos los clientes que existen. 
2. "/estado": Esto lista todos los clientes que están en estado activos.
3. "/{dpi}": Esto busca el cliente por medio del id.
4. "/{dpi}": Esto elimina el cliente medio del id.
5. "/{dpi}": Esto actualiza el cliente medio del id.

* Usuario:
1. "/usuarios": Esto lista a todos los usuarios que existen.
2. "/activo": Esto lista los usuarios que están en estado activo.
3. "/{codigoUsuario}": Esto busca el usuario por medio del id.
4. "/{codigoUsuario}": Esto elimina el usuario por medio del id.
5. "/{codigoUsuario}": Esto actualiza el usuario por medio del id.

* Productos:
1. "/Productos": Esto lista todos productos que existen.
2. "/activos": Esto lista los productos que están activos.
3. "/{codigoProducto}": Esto busca el producto por medio del id.
4. "/{codigoProducto}": Esto elimina el producto por medio del id.
5. "/{codigoProducto}": Esto actualiza el producto por medio del id.
6. "/listar-stock": Esto lista el nombre del producto y la cantidad que hay en stock.

* Ventas:
1. "/Ventas": Esto lista todas las ventas que existen.
2. "/activos": Esto lista las ventas que están activos.
3. "/{codigoVenta}": Esto busca la venta por medio del id.
4. "/{codigoVenta}": Esto elimina la venta por medio del id.
5. "/{codigoVenta}": Esto actualiza la venta por medio del id.

* DetalleVenta:
1. "/Detalle": Esto nos lista los detalles de venta que existe.
2. "/{codigoDetalleVenta}": Esto busca los detalles de venta por medio él id.
3. "/{codigoDetalleVenta}": Esto elimina los detalles de venta por medio él id.
