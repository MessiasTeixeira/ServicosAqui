# 04: CEP preenche a Área atendida (Retrofit + ViaCEP)

**What to build:** no formulário do Anúncio, um campo CEP com botão Buscar consulta a ViaCEP via Retrofit e preenche a Área atendida com `bairro — cidade/UF` (sem o bairro se vier vazio). A área continua editável. CEP inexistente (`"erro": true`) ou falha de rede viram mensagem clara. Ver `docs/tickets/v1/spec.md`.

**Blocked by:** 03

**Status:** done

- [x] Interface Retrofit com `suspend fun` e repositório que devolve `Result`, no estilo do exemplo `BuscaCEP ViewModel`
- [x] Montagem da Área atendida como função pura, com testes unitários
- [x] Estado de carregamento visível durante a consulta; erros não derrubam o app

## Comments

Feito: `EnderecoAPI` (Retrofit, `suspend fun`, `@GET`/`@Path`), `CepRepository` (Retrofit + Gson, devolve `Result`, `"erro": true` vira `CepNaoEncontradoException`), `Endereco`, `montarAreaAtendida` (função pura, 4 testes JUnit) e, no formulário, campo CEP (só números, até 8) com botão Buscar, indicador de carregamento e mensagens para CEP curto, inexistente, sem conexão e falha geral. A área continua editável. Falta verificar no emulador com um CEP real.
