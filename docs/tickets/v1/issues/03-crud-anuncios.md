# 03: Gerenciar anúncios — CRUD no Firestore

**What to build:** na aba Anúncios, a lista de todos os Anúncios (ativos e inativos) vem do Firestore. Um botão de ação flutuante abre o formulário (nome, Categoria da lista fixa, descrição, Área atendida, ativo) que valida os campos obrigatórios e salva. Tocar num Anúncio abre o mesmo formulário para editar. Na lista dá para ativar/desativar e excluir com confirmação. Falhas do Firestore viram mensagem na tela. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 01, 02

**Status:** done

- [x] Plugin `google-services` aplicado; app compila com o `google-services.json`
- [x] `AnuncioDao` no estilo do exemplo `AppTask` (criar, listar todos, listar ativos, buscar por id, atualizar, excluir)
- [x] ViewModel com `MutableStateFlow` de uma data class `UIState`
- [x] Validação do Anúncio como função pura, com testes unitários JUnit
- [x] Criar, editar, ativar/desativar e excluir refletem no Firestore e persistem ao reabrir o app

## Comments

Feito: `AnuncioDao` (criar, listarTodos, listarAtivos, buscarPorId, atualizar, excluir; as exceções sobem e os ViewModels viram mensagem), `AnunciosViewModel` e `AnuncioFormViewModel` com `UIState`, `validarAnuncio` (função pura, 5 testes JUnit), telas `AnunciosScreen` (lista, FAB, Switch ativo, excluir com confirmação) e `AnuncioFormScreen` (criar/editar, categoria em chips da lista fixa), rotas `anuncios` e `formulario?id={id}`. Os `Example*Test` foram apagados.

Falta: o `app/google-services.json` ainda não existe. O plugin `com.google.gms.google-services` está declarado no build raiz e o `app/build.gradle.kts` só o aplica quando o arquivo existir. Sem o arquivo, o app compila, mas o Firestore não inicializa e as telas mostram a mensagem de erro de conexão. Falta também verificar no emulador (criar, editar, ativar/desativar, excluir, reabrir).

2026-11-15: `google-services.json` versionado e o plugin aplicado com `alias(libs.plugins.google.services)` (sem condicional). CRUD verificado no emulador contra o Firestore real.
