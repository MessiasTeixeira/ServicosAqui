# 06: Mapa de requisitos e verificação no emulador

**What to build:** `docs/requisitos.md` mapeia cada requisito da disciplina (Compose/Material, busca com filtro, navegação, CRUD Firestore, Retrofit) e cada RF/RN/RNF coberto na Fase 1 para os arquivos e funções que o implementam, como roteiro para a apresentação. Os dois percursos da spec são verificados no emulador.

**Blocked by:** 04, 05

**Status:** ready-for-agent

- [ ] `docs/requisitos.md` com uma linha por requisito → arquivo(s)
- [ ] Percurso Visitante verificado no emulador
- [ ] Percurso Gestão verificado no emulador (criar com CEP, editar, desativar, reativar, excluir)
- [ ] `./gradlew testDebugUnitTest assembleDebug` passa
