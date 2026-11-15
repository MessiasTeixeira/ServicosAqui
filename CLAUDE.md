# ServiçosAqui

Android app (Kotlin + Jetpack Compose) for the course Programação para Dispositivos Móveis (IFPB, 2026.2). Visitors browse home-service providers (Anúncios) by Categoria or search. Backend is Cloud Firestore; the REST API is ViaCEP through Retrofit.

The project is delivered in phases. **Fase 1** (current, spec in `docs/tickets/v1/spec.md`) covers only what the course asked for so far: Compose + Material, search with filter and navigation, Firestore CRUD of Anúncios, Retrofit. Do not build Fase 2 items (login, owner of the Anúncio, WhatsApp/Contato) unless a Fase 2 spec exists.

## Code style

- Follow the professor's examples in `github.com/edembergifpb/PDM` (BuscaCEP ViewModel, AppTask, ListadeTarefasAPP - UIState, Navegacao): ViewModel + `MutableStateFlow` of a `...UIState` data class, Firestore through a `...Dao` class with `await()`, Retrofit interface + repository returning `Result`, `NavHost` with string routes. No Koin.
- No comments in code. Names carry the meaning; identifiers in Portuguese, matching `GLOSSARY.md`.
- Keep it simple and readable for students who must explain each requirement: no extra layers. Screen → ViewModel → Dao/repository.
- Each course requirement lives in an obvious place; `docs/requisitos.md` maps requirements to files. Update it when you move or add one.
- Colors and typography come from `MaterialTheme` (`ui/theme`), never hardcoded per screen.

## Build

- `./gradlew assembleDebug` builds; `./gradlew testDebugUnitTest` runs unit tests.
- `local.properties` (not committed) holds `sdk.dir`.
- `app/google-services.json` is committed on purpose: Firebase client config is not a secret; access is enforced by `firestore.rules`.

## Agent skills

### Issue tracker

Local markdown under `docs/tickets/` (no GitHub Issues for this repo). See `docs/agents/issue-tracker.md`.

### Triage labels

Default five-role vocabulary, recorded as a `Status:` line in each ticket file. See `docs/agents/triage-labels.md`.

### Domain docs

Single-context: `GLOSSARY.md` and `docs/adr/` at the repo root. See `docs/agents/domain.md`.
