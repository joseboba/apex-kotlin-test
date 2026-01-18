
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
