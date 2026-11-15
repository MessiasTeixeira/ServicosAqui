# 06: Mapa de requisitos e verificação no emulador

**What to build:** `docs/requisitos.md` mapeia cada requisito da disciplina (Compose/Material, busca com filtro, navegação, CRUD Firestore, Retrofit) e cada RF/RN/RNF coberto na Fase 1 para os arquivos e funções que o implementam, como roteiro para a apresentação. Os dois percursos da spec são verificados no emulador.

**Blocked by:** 04, 05

**Status:** done

- [x] `docs/requisitos.md` com uma linha por requisito → arquivo(s)
- [x] Percurso Visitante verificado no emulador
- [x] Percurso Gestão verificado no emulador (criar com CEP, editar, desativar, reativar, excluir)
- [x] `./gradlew testDebugUnitTest assembleDebug` passa

## Comments

2026-11-15, emulador API 36 com o Firestore real:
- Visitante: catálogo lista só ativos; busca "hidraulica" acha "Hidráulica"; termo sem resultado mostra mensagem; chip Elétrica filtra e um segundo toque limpa; card abre o Perfil público (sem contato) e Voltar retorna.
- Gestão: criar com CEP 01001-000 (Área "Sé — São Paulo/SP"), editar o nome, desativar (some do Início com "Ainda não há anúncios ativos."), reativar (volta), dados mantidos após fechar e reabrir o app, excluir com confirmação.
- Erros: sem Firebase e com CEP inexistente aparecem mensagens, sem fechar o app.
- `./gradlew testDebugUnitTest assembleDebug` verde (18 testes).
