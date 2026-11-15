# 04: CEP preenche a Área atendida (Retrofit + ViaCEP)

**What to build:** no formulário do Anúncio, um campo CEP com botão Buscar consulta a ViaCEP via Retrofit e preenche a Área atendida com `bairro — cidade/UF` (sem o bairro se vier vazio). A área continua editável. CEP inexistente (`"erro": true`) ou falha de rede viram mensagem clara. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 03

**Status:** ready-for-agent

- [ ] Interface Retrofit com `suspend fun` e repositório que devolve `Result`, no estilo do exemplo `BuscaCEP ViewModel`
- [ ] Montagem da Área atendida como função pura, com testes unitários
- [ ] Estado de carregamento visível durante a consulta; erros não derrubam o app
