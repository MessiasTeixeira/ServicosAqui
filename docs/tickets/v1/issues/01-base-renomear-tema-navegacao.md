# 01: Base — renomear, tema Material e barra de navegação

**What to build:** o app abre com o nome ServiçosAqui, a identidade verde/dourado aplicada pelo `MaterialTheme` e um `Scaffold` com `NavigationBar` de três abas (Início, Anúncios, Conta). Início e Anúncios mostram telas provisórias; Conta mostra a tela de login existente, que navega para o cadastro e volta. Os modelos fora da Fase 1 saem do código. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** None (can start immediately)

**Status:** done

- [x] Nome exibido "ServiçosAqui"; `rootProject.name`, tema XML e tema Compose renomeados para ServicosAqui; `applicationId` = `com.example.servicosaqui`
- [x] `MainActivity` aplica o tema; as cores da identidade vivem só em `ui/theme` (sem cores dinâmicas); login e cadastro usam as cores do tema
- [x] `Scaffold` com `NavigationBar` (Início, Anúncios, Conta) navegando por `NavHost` com rotas em string
- [x] Removidos `Avaliacao`, `Solicitacao`, `ServicoPrestador`, `Cliente`, `Prestador`, `Usuario`; criados `Anuncio` (com `@DocumentId`) e a lista fixa de `Categoria`
- [x] Dependências de Retrofit + Gson, lifecycle-viewmodel-compose e Firestore declaradas (plugin `google-services` só entra quando o `google-services.json` existir, no ticket 03)
- [x] `./gradlew assembleDebug` passa; sem comentários no código

## Comments

Feito: renomeado para ServiçosAqui (nome exibido, `rootProject.name`, tema XML, `ServicosAquiTheme`, `applicationId`); identidade verde/dourado em `ui/theme` (esquema claro, sem cores dinâmicas), login e cadastro usam `MaterialTheme.colorScheme` e perderam os comentários; `Scaffold` + `NavigationBar` (Início, Anúncios, Conta) com `NavHost` de rotas em string (`inicio`, `anuncios`, `login`, `cadastro`); o botão "Entrar" do cadastro agora volta ao login; removidos os 6 modelos fora da Fase 1 e o `colors.xml` do template; criados `Anuncio` (`@DocumentId`) e `Categoria.todas`; dependências de Retrofit, Gson, lifecycle-viewmodel-compose, Firestore (BOM 34.6.0) e coroutines-play-services (para `await()`), mais material-icons-core. `assembleDebug` passou. Não verificado no emulador. Code-review pulado por decisão do dono (revisão única no fim da Fase 1). Sem testes (ticket não pede). Falta: plugin `google-services` (ticket 03).
