# 05: Catálogo — Categorias, busca com filtro e Perfil público

**What to build:** o Início mostra as Categorias (chips selecionáveis), um campo de busca e os Anúncios ativos em cards (nome, Categoria, Área atendida). A lista filtra por termo (nome, Categoria, descrição, área; sem diferenciar maiúsculas e acentos) e por Categoria, combinados. Sem resultados ou sem conexão, aparece mensagem (com tentar de novo). Tocar num card abre o Perfil público com a descrição completa; voltar retorna à lista. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 03

**Status:** ready-for-agent

- [ ] Só Anúncios com `ativo == true` (consulta no Firestore)
- [ ] Filtro do catálogo como função pura, com testes unitários
- [ ] Navegação Início → Perfil público (rota recebe o id) → voltar
- [ ] Desativar um Anúncio na aba Anúncios o tira do catálogo ao voltar ao Início
