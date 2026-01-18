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