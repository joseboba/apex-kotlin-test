## Instrucciones de compilación y ejecución

Las siguientes instrucciones describen cómo compilar y ejecutar el proyecto localmente en un entorno de desarrollo.

---

### Requisitos previos

Antes de comenzar, asegúrate de contar con lo siguiente:

- **Android Studio** (recomendado: última versión estable)
- **JDK 17** (o la versión configurada en el proyecto)
- **Android SDK** instalado desde Android Studio
- Un **emulador Android** o un **dispositivo físico** con modo desarrollador habilitado
- Conexión a internet (la aplicación consume una API remota)

---

### Clonar el repositorio

Clona el proyecto desde el repositorio remoto:

```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_PROYECTO>
```


#### **Abrir el proyecto**
    1. Abre Android Studio.
    2. Selecciona Open.
    3. Navega hasta la carpeta del proyecto y ábrela.
    4. Espera a que Gradle sincronice las dependencias automáticamente.

#### **Configuración del proyecto**

    1. No se requiere configuración adicional para ejecutar el proyecto.
    2. Las dependencias se gestionan mediante Gradle Version Catalog (libs.versions.toml).
    3. La aplicación utiliza una API pública, por lo que no es necesario configurar variables de entorno ni claves privadas.