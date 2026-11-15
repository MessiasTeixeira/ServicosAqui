# 03: Gerenciar anúncios — CRUD no Firestore

**What to build:** na aba Anúncios, a lista de todos os Anúncios (ativos e inativos) vem do Firestore. Um botão de ação flutuante abre o formulário (nome, Categoria da lista fixa, descrição, Área atendida, ativo) que valida os campos obrigatórios e salva. Tocar num Anúncio abre o mesmo formulário para editar. Na lista dá para ativar/desativar e excluir com confirmação. Falhas do Firestore viram mensagem na tela. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 01, 02

**Status:** ready-for-agent

- [ ] Plugin `google-services` aplicado; app compila com o `google-services.json`
- [ ] `AnuncioDao` no estilo do exemplo `AppTask` (criar, listar todos, listar ativos, buscar por id, atualizar, excluir)
- [ ] ViewModel com `MutableStateFlow` de uma data class `UIState`
- [ ] Validação do Anúncio como função pura, com testes unitários JUnit
- [ ] Criar, editar, ativar/desativar e excluir refletem no Firestore e persistem ao reabrir o app
