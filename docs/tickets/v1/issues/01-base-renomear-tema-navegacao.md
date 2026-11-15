# 01: Base — renomear, tema Material e barra de navegação

**What to build:** o app abre com o nome ServiçosAqui, a identidade verde/dourado aplicada pelo `MaterialTheme` e um `Scaffold` com `NavigationBar` de três abas (Início, Anúncios, Conta). Início e Anúncios mostram telas provisórias; Conta mostra a tela de login existente, que navega para o cadastro e volta. Os modelos fora da Fase 1 saem do código. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** None (can start immediately)

**Status:** ready-for-agent

- [ ] Nome exibido "ServiçosAqui"; `rootProject.name`, tema XML e tema Compose renomeados para ServicosAqui; `applicationId` = `com.example.servicosaqui`
- [ ] `MainActivity` aplica o tema; as cores da identidade vivem só em `ui/theme` (sem cores dinâmicas); login e cadastro usam as cores do tema
- [ ] `Scaffold` com `NavigationBar` (Início, Anúncios, Conta) navegando por `NavHost` com rotas em string
- [ ] Removidos `Avaliacao`, `Solicitacao`, `ServicoPrestador`, `Cliente`, `Prestador`, `Usuario`; criados `Anuncio` (com `@DocumentId`) e a lista fixa de `Categoria`
- [ ] Dependências de Retrofit + Gson, lifecycle-viewmodel-compose e Firestore declaradas (plugin `google-services` só entra quando o `google-services.json` existir, no ticket 03)
- [ ] `./gradlew assembleDebug` passa; sem comentários no código
