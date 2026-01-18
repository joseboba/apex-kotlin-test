# ApexTest (Kotlin + Jetpack Compose)

Proyecto Android en Kotlin utilizando **Jetpack Compose**, **Retrofit + OkHttp + Moshi** para consumo de APIs, **Paging 3** para paginación, y **Coil** para carga de imágenes.

---

## Estructura del proyecto (File System)

El proyecto está organizado por capas y responsabilidades, siguiendo una separación clara entre **domain**, **infrastructure** y **presentation**.

```
.
├── com.example.apextest/
│   ├── config/
│   │   ├── interceptors
│   │   └── theme
│   ├── domain/
│   │   ├── api
│   │   ├── datasources
│   │   ├── entities
│   │   └── repositories
│   ├── infrastructure/
│   │   ├── datasources
│   │   ├── mappers
│   │   ├── model
│   │   ├── paging
│   │   └── repositories
│   ├── presentation/
│   │   ├── components
│   │   ├── provider
│   │   ├── screens
│   │   └── state
│   └── shared/
│       └── infrastructure
├── App
└── MainActivity
```


### `config/`
- **interceptors/**: Configuración relacionada a red (por ejemplo, `OkHttpClient`, interceptores de logging, headers, etc.) y módulos de inyección asociados.
- **theme/**: Tema de Jetpack Compose (colores, tipografía, shapes).

### `domain/` (Capa de negocio)
Esta capa define **contratos** y modelos del dominio sin depender de frameworks.
- **api/**: Definiciones de API (interfaces) o contratos de acceso remoto.
- **datasources/**: Interfaces (contratos) de fuentes de datos. Ej: `GetCharacterDatasource`.
- **entities/**: Entidades del dominio (modelos “puros” que representan reglas/objetos del negocio), mapeo del JSON response del API a un `data class` de Kotlin.
- **repositories/**: Interfaces (contratos) de repositorios. Ej: `GetCharacterRepository`.

### `infrastructure/` (Capa de implementación)
Aquí viven las implementaciones concretas y adaptadores a frameworks.
- **datasources/**: Implementaciones de los datasources definidos en domain. Ej: `GetCharacterDatasourceImpl(api)`.
- **mappers/**: Convertidores entre modelos de red y entidades de dominio.
- **model/**: Modelos que representan toda la capa de datos para el uso en el dominio de `presentation`.
- **paging/**: Configuración de Paging 3 (PagingSource).
- **repositories/**: Implementaciones de los repositorios del dominio. Ej: `GetCharacterRepositoryImpl(datasource)`.

### `presentation/` (UI)
Todo lo relacionado a UI con Compose y estado de pantalla.
- **components/**: Componentes reutilizables (cards, lists, items, botones, etc.).
- **provider/**: Providers de dependencias o wiring de presentation (por ejemplo, módulos Hilt para ViewModels, o helpers de UI).
- **screens/**: Pantallas (composables) principales.
- **state/**: Estado de UI (uiState, sealed classes, reducers, etc.).

### `shared.infrastructure/`
Espacio para componentes compartidos de infraestructura (helpers o utilidades comunes entre features/capas).

### `App` y `MainActivity`
- **App**: Clase `Application` (`@HiltAndroidApp` para inicializar Hilt).
- **MainActivity**: Activity principal que setea el contenido Compose (`setContent { ... }`).

---

## Dependencias utilizadas

A continuación se describen las librerías principales del proyecto y su propósito:

### UI + Ciclo de vida (Compose)
```kotlin
implementation(libs.androidx.lifecycle.runtime.compose)
```

#### Propósito
- Integra el ciclo de vida de Android con Jetpack Compose.
- Permite recolectar Flow y StateFlow de forma segura usando collectAsStateWithLifecycle.
- Evita fugas de memoria y recomposiciones innecesarias cuando la UI no está activa.

### Networking – Retrofit, OkHttp y Moshi
```kotlin
implementation(libs.retrofit)
implementation(libs.retrofit.converter.moshi)
implementation(libs.okhttp.logging)
implementation(libs.moshi.kotlin)
```

### Retrofit

- Cliente HTTP declarativo para consumir APIs REST.
- Permite definir endpoints como interfaces usando anotaciones (@GET, - - @POST, etc.).
- Genera implementaciones en runtime.
- OkHttp Logging Interceptor
- Interceptor de red para registrar requests y responses HTTP.
- Útil para debugging (headers, body y status codes).
- Se integra directamente en el OkHttpClient.

### Moshi

- Librería de serialización y deserialización JSON optimizada para Kotlin.
- Convierte automáticamente JSON en data classes.
- Maneja nullability, valores por defecto y estructuras anidadas.
- Moshi Converter para Retrofit
- Permite que Retrofit utilice Moshi para mapear las respuestas HTTP a modelos Kotlin.

### Carga de Imágenes – Coil
```kotlin
implementation(libs.coil.compose)
```

#### Propósito

- Carga de imágenes eficiente y optimizada para Android.
- Integración nativa con Jetpack Compose.
- Manejo automático de caché, placeholders y errores.

### Paginación – Paging 3
```kotlin
implementation(libs.paging.runtime)
implementation(libs.paging.compose)
```

### Paging Runtime
- Provee la lógica central de paginación mediante PagingSource y Pager.
- Maneja la carga incremental de datos desde red o base de datos.

### Paging Compose
- Integra Paging 3 con Jetpack Compose.
- Introduce LazyPagingItems para listas paginadas.
- Facilita el manejo de estados de carga (refresh, append, error).

# Arquitectura

El proyecto está organizado por capas, separando responsabilidades entre **Presentation**, **Domain** e **Infrastructure**.  
El objetivo es mantener la UI desacoplada de los detalles de implementación (networking, paging, mappers, etc.) y facilitar testing y mantenimiento.

---

## Flujo principal (alto nivel)

```text
ViewModel  →  UseCase / Repository  →  Datasource  →  API (Retrofit)  →  Network

┌──────────────────────────────┐
│          Presentation        │
│  - screens / components      │
│  - state                     │
│  - ViewModel                 │
└───────────────┬──────────────┘
                │ (llama)
                ▼
┌──────────────────────────────┐
│             Domain           │
│  - entities                  │
│  - repositories (interfaces) │
│  - datasources (interfaces)  │
│  (+ usecases si aplican)     │
└───────────────┬──────────────┘
                │ (implementa)
                ▼
┌──────────────────────────────┐
│         Infrastructure       │
│  - repositories (impl)       │
│  - datasources (impl)        │
│  - model (DTOs)              │
│  - mappers                   │
│  - paging                    │
│  - network (Retrofit API)    │
└──────────────────────────────┘

CharactersViewModel
   ↓ necesita
GetCharacterRepository  (domain: interface)
   ↓ implementado por
GetCharacterRepositoryImpl (infrastructure)
   ↓ necesita
GetCharacterDatasource (domain: interface)
   ↓ implementado por
GetCharacterDatasourceImpl (infrastructure)
   ↓ necesita
CharactersApi (Retrofit)
   ↓ necesita
Retrofit → OkHttpClient → Moshi
```

# Aplicación en ejecución
![](/home/jebo/Pictures/Apex/loading_inicial.png)