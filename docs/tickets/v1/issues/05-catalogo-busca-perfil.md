# 05: Catálogo — Categorias, busca com filtro e Perfil público

**What to build:** o Início mostra as Categorias (chips selecionáveis), um campo de busca e os Anúncios ativos em cards (nome, Categoria, Área atendida). A lista filtra por termo (nome, Categoria, descrição, área; sem diferenciar maiúsculas e acentos) e por Categoria, combinados. Sem resultados ou sem conexão, aparece mensagem (com tentar de novo). Tocar num card abre o Perfil público com a descrição completa; voltar retorna à lista. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 03

**Status:** done (falta google-services.json para rodar)

- [x] Só Anúncios com `ativo == true` (consulta no Firestore)
- [x] Filtro do catálogo como função pura, com testes unitários
- [x] Navegação Início → Perfil público (rota recebe o id) → voltar
- [x] Desativar um Anúncio na aba Anúncios o tira do catálogo ao voltar ao Início

## Comments

Feito: `filtrarAnuncios` (função pura, 9 testes JUnit: termo sem maiúsculas e acentos em nome, Categoria, descrição e área; Categoria opcional; combinados), `CatalogoViewModel` (consulta `ativo == true` via `listarAtivos`), `InicioScreen` (busca, chips de Categoria que desmarcam ao tocar de novo, cards, mensagem sem resultado, erro de conexão com "Tentar de novo"), `PerfilViewModel` e `PerfilScreen` (rota `perfil/{id}`, voltar). O catálogo recarrega ao voltar ao Início, então desativar na aba Anúncios o tira da lista. Tela provisória removida.

Falta: o `app/google-services.json` não existe, então o Firestore não inicializa e o Início mostra a mensagem de falta de conexão. Também falta verificar no emulador o fluxo do Visitante e a ligação com o ticket 03.
