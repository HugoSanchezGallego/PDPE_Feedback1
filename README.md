# PDPE Feedback1

# Enlace del repositorio en Github ----> https://github.com/HugoSanchezGallego/PDPE_Feedback1.git

## Descripción del Proyecto

PDPE Feedback1 es una aplicación de gestión de novelas desarrollada en Kotlin utilizando Jetpack Compose para la interfaz de usuario. La aplicación permite a los usuarios registrarse, iniciar sesión, agregar, ver detalles y eliminar novelas. Además, los usuarios pueden dejar reseñas y marcar novelas como favoritas.

## Estructura del Proyecto

El proyecto está organizado en varios archivos y paquetes para mantener el código modular y fácil de mantener. A continuación se describen los archivos y su funcionalidad:

### `MainActivity.kt`

Este archivo contiene la actividad principal que se ejecuta al iniciar la aplicación. Configura el tema de la aplicación y navega a la pantalla de inicio de sesión.

### `NavegacionApp.kt`

Define la navegación de la aplicación utilizando `NavHost` y `NavController`. Configura las rutas para las diferentes pantallas de la aplicación.

### `PantallaLogin.kt`

Contiene la pantalla de inicio de sesión donde los usuarios pueden ingresar sus credenciales para autenticarse. También permite la navegación a la pantalla de registro.

### `PantallaRegistro.kt`

Contiene la pantalla de registro donde los nuevos usuarios pueden crear una cuenta proporcionando un nombre de usuario y una contraseña.

### `PantallaPrincipal.kt`

Esta es la pantalla principal que se muestra después de que un usuario inicia sesión. Muestra una lista de novelas y permite agregar nuevas novelas, ordenar la lista y navegar a los detalles de una novela.

### `PantallaAgregarNovela.kt`

Contiene la pantalla para agregar una nueva novela. Los usuarios pueden ingresar el título, autor, año y sinopsis de la novela.

### `PantallaDetallesNovela.kt`

Muestra los detalles de una novela seleccionada, incluidas las reseñas dejadas por otros usuarios. También permite a los usuarios agregar nuevas reseñas y marcar la novela como favorita.

### `ItemNovela.kt`

Define el componente de la interfaz de usuario para mostrar una novela en la lista de la pantalla principal. Incluye botones para ver detalles y eliminar la novela.

### `ConfirmacionBorrarNovela.kt`

Contiene un diálogo de confirmación que se muestra cuando un usuario intenta eliminar una novela.

### `AuthManager.kt`

Gestiona la autenticación de usuarios, incluyendo el registro y la verificación de credenciales.

### `DatosIncompletosException.kt`

Define una excepción personalizada que se lanza cuando los datos proporcionados para una novela son incompletos.

### `Novela.kt`

Define la clase `Novela` que representa una novela con atributos como título, autor, año, sinopsis y si es favorita.

### `NovelaRepository.kt`

Gestiona el almacenamiento y la manipulación de las novelas. Permite agregar, eliminar y actualizar novelas.

### `Resena.kt`

Define la clase `Resena` que representa una reseña con atributos como calificación, comentario, usuario, timestamp y título de la novela.

### `ResenaRepository.kt`

Gestiona el almacenamiento y la manipulación de las reseñas. Permite agregar y obtener reseñas por título de novela.

### `Orden.kt`

Define un enumerado `Orden` que especifica los diferentes criterios de ordenación para las novelas (por título o autor, ascendente o descendente).

### `ui/theme/Color.kt`

Define los colores utilizados en el tema de la aplicación.

### `ui/theme/Shapes.kt`

Define las formas utilizadas en el tema de la aplicación.

### `ui/theme/Theme.kt`

Configura el tema de la aplicación, incluyendo colores, tipografía y formas.

### `ui/theme/Type.kt`

Define la tipografía utilizada en el tema de la aplicación.

## Dependencias

El proyecto utiliza varias dependencias de Android y Jetpack Compose, que se gestionan en los archivos `build.gradle.kts` y `gradle/libs.versions.toml`.

## Ejecución del Proyecto

Para ejecutar el proyecto, abre el proyecto en Android Studio y selecciona "Run" en el menú. Asegúrate de tener un emulador de Android configurado o un dispositivo físico conectado.
