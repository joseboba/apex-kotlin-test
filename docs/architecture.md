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