# Spec: ServiçosAqui — Fase 1

**Status:** ready-for-agent

Fonte: requisitos da [Projeto] Parte 1 da disciplina (Compose + Material responsivo, busca com filtro e navegação, CRUD em banco na nuvem, API REST com Retrofit), o deck "ServiçoJá — Fase I" e as decisões de 2026-11-15 (Q1–Q16, F1–F4). Vocabulário: `GLOSSARY.md`. Decisões: `docs/adr/`. Entrega: 2026-11-16, 14:40.

A Fase 1 entrega **só o que a disciplina pediu até agora**, no estilo dos exemplos do professor (repositório `edembergifpb/PDM`). Login real, dono do Anúncio e Contato protegido ficam para a Fase 2 (ver "Out of Scope").

## Problem Statement

Quem precisa de limpeza, reparo elétrico, hidráulica ou marcenaria depende de indicações soltas em grupos e conversas: não fica claro o que cada pessoa faz nem onde atende. Para a disciplina, o app precisa demonstrar Compose com Material Design, busca com filtro e navegação entre telas, um CRUD no Cloud Firestore e uma API REST consumida com Retrofit.

## Solution

O app ServiçosAqui abre num catálogo público: a pessoa escolhe uma Categoria ou busca por termo, vê os Anúncios ativos em cards e abre o Perfil público de cada um. Uma aba "Anúncios" permite gerenciar os Anúncios no Firestore: criar, listar, editar, ativar/desativar e excluir. No formulário, o CEP preenche a Área atendida pela API ViaCEP.

## User Stories

### Catálogo

1. Como Visitante, quero que o app abra no catálogo sem pedir login, para consultar prestadores na hora (RF01).
2. Como Visitante, quero ver as Categorias no topo do Início, para começar pelo tipo de serviço que preciso (RF01).
3. Como Visitante, quero tocar numa Categoria e ver só os Anúncios dela, e tocar de novo para tirar o filtro (RF02).
4. Como Visitante, quero digitar um termo e ver a lista mudar enquanto digito, procurando em nome, Categoria, descrição e Área atendida (RF02).
5. Como Visitante, quero combinar termo e Categoria, para refinar a busca (RF02).
6. Como Visitante, quero que a busca ignore maiúsculas e acentos, para achar "Elétrica" digitando "eletrica".
7. Como Visitante, quero ver em cada card o nome, a Categoria e a Área atendida (RF03).
8. Como Visitante, quero ver só Anúncios ativos no catálogo (RF03, RN02).
9. Como Visitante, quero uma mensagem quando a busca não encontra nada.
10. Como Visitante, quero uma mensagem clara e um jeito de tentar de novo quando não há conexão, sem o app fechar (RNF07).
11. Como Visitante, quero tocar num card e abrir o Perfil público com a descrição completa e a Área atendida, e voltar para a lista (RF04).
12. Como Visitante, quero nunca ver preços, notas ou certificações (RN05).

### Gerenciar anúncios (CRUD)

13. Como usuário do app, quero uma aba "Anúncios" na barra inferior, com a lista de todos os Anúncios (ativos e inativos), para gerenciá-los.
14. Como usuário, quero um botão de ação flutuante para criar um Anúncio novo.
15. Como usuário, quero informar nome, Categoria (escolhida da lista fixa), descrição e Área atendida, e marcar se o Anúncio está ativo (RN01, sem WhatsApp na Fase 1).
16. Como usuário, quero digitar um CEP e tocar em Buscar para a Área atendida ser preenchida com bairro, cidade e UF (API REST).
17. Como usuário, quero continuar podendo editar a Área atendida depois do preenchimento pelo CEP.
18. Como usuário, quero uma mensagem clara quando o CEP não existe ou a consulta falha, sem o app fechar (RNF07).
19. Como usuário, quero que o app aponte os campos obrigatórios vazios antes de salvar (RN01).
20. Como usuário, quero tocar num Anúncio da lista para editá-lo no mesmo formulário (RF08).
21. Como usuário, quero ativar ou desativar um Anúncio direto na lista e ver o catálogo refletir a mudança (RF09).
22. Como usuário, quero excluir um Anúncio, com uma confirmação antes (CRUD).
23. Como usuário, quero ver os dados salvos depois de fechar e reabrir o app (RNF04).

### Conta (só visual na Fase 1)

24. Como Visitante, quero uma aba "Conta" que mostra as telas de login e cadastro já desenhadas pela equipe, que passam a funcionar na Fase 2.

### Plataforma

25. Como usuário de qualquer celular, quero telas que se adaptem ao tamanho da tela e à fonte ampliada, rolando quando preciso (RNF02).
26. Como usuário, quero botões e campos com rótulos claros e bom contraste (RNF03).
27. Como aluno da equipe, quero cada requisito da disciplina num arquivo óbvio e um mapa requisito→arquivo, para explicar a implementação ao professor.

## Implementation Decisions

- **Estilo dos exemplos do professor.** Seguir o repositório `edembergifpb/PDM`:
  - Retrofit: uma interface com `suspend fun` e `@GET`/`@Path` e um repositório que cria o Retrofit com `GsonConverterFactory` e devolve `Result` (exemplo `BuscaCEP ViewModel`).
  - ViewModels com `MutableStateFlow` de uma data class `...UIState`, alterada com `copy`, e as telas com `collectAsState()` e `viewModel()` (exemplos `ListadeTarefasAPP - UIState` e `BuscaCEP ViewModel`).
  - Acesso ao Firestore numa classe `AnuncioDao` com `FirebaseFirestore.getInstance()`, `await()`, `toObjects` e `@DocumentId` (exemplo `AppTask`).
  - Navegação com `NavHost` e rotas em string (exemplo `Navegacao`), e Material com `Scaffold`, `TopAppBar`, `NavigationBar`, `FloatingActionButton` e `Card`.
  - Sem Koin, sem comentários no código, nomes em português conforme o glossário.
- **Nome e identificação.** ServiçosAqui em todo lugar (nome exibido, projeto Gradle, tema). `applicationId` igual ao namespace, `com.example.servicosaqui`, antes do registro no Firebase.
- **Modelos.** Só `Anuncio` (id via `@DocumentId`, nome, categoria, descricao, areaAtendida, ativo) e `Categoria` (lista fixa: Limpeza, Elétrica, Hidráulica, Marcenaria, Pintura, Jardinagem), mais o modelo de resposta do ViaCEP. `Avaliacao`, `Solicitacao`, `ServicoPrestador`, `Cliente`, `Prestador` e `Usuario` saem (RN05, RN06; contas voltam na Fase 2).
- **Regras puras, testáveis sem Android:**
  - validação do Anúncio (nome, Categoria, descrição e Área atendida preenchidos);
  - filtro do catálogo (termo sem diferenciar maiúsculas e acentos, procurado em nome, Categoria, descrição e área; Categoria opcional);
  - montagem da Área atendida a partir da resposta do ViaCEP (`bairro — cidade/UF`, sem o bairro quando ele vier vazio).
- **Firestore.**
  - Coleção `anuncios`, sem login na Fase 1.
  - O catálogo consulta `ativo == true`; a aba Anúncios lista todos.
  - `firestore.rules`, versionado no repositório, libera leitura e escrita de `anuncios` para qualquer pessoa, mas valida os campos na escrita. Fica marcado como temporário até a Fase 2 (ADR 0001).
- **ViaCEP (ADR 0002).** `GET https://viacep.com.br/ws/{cep}/json/`. Um CEP inexistente volta com `"erro": true` e deve virar mensagem. Só HTTPS (RNF04).
- **Navegação.**
  - `Scaffold` com `NavigationBar` de três abas: Início, Anúncios e Conta.
  - Rotas: início, perfil do Anúncio (recebe o id), anúncios, formulário (novo ou editar, recebe o id opcional), login e cadastro.
  - A aba Conta mostra a tela de login existente, que leva ao cadastro. Os botões de entrar e criar conta continuam sem ação até a Fase 2.
- **Tema.** A identidade atual (verde `#173F35`, dourado `#D5A85B`, fundo `#F5F2EA`, grafite `#343B36`) vai para o `MaterialTheme` (esquema claro, sem cores dinâmicas), aplicado no `MainActivity`. As telas de login e cadastro passam a usar as cores do tema.
- **Erros (RNF07).** Falhas de rede, do Firestore e do ViaCEP aparecem como mensagem na tela; nenhuma exceção derruba o app.
- **Documentação de apoio.** `docs/requisitos.md` mapeia cada requisito da disciplina e cada RF/RN/RNF coberto para os arquivos que o implementam.

## Testing Decisions

- Um bom teste verifica comportamento externo (entrada → saída esperada), nunca detalhes internos, Compose ou Firebase.
- Testes unitários JVM (JUnit 4, já no projeto) para as três regras puras: validação do Anúncio, filtro do catálogo e montagem da Área atendida.
- Firestore, Retrofit e telas são verificados rodando o app no emulador:
  - **Visitante:** filtra por Categoria e termo, abre um Perfil público e volta.
  - **Gestão:** cria um Anúncio com CEP, edita, desativa (some do catálogo), reativa (volta) e exclui.
- Arte anterior: só os `Example*Test` gerados pelo Android Studio, que serão substituídos.

## Out of Scope (Fase 2)

- Login e cadastro reais (Firebase Auth), sessão e aba Conta funcional (RF05, RF07).
- Dono do Anúncio (um por Prestador) e regras do Firestore por dono (RF07).
- Campo WhatsApp, botão Contatar, volta ao perfil depois do login e Contato protegido na origem (RF06, RN03, RN04, RNF05, ADR 0001).
- Aviso de exibição do contato (RNF06) e "Esqueceu a senha?".
- Pedidos, agenda, pagamento, comissão (RN06); preços, avaliações e certificações (RN05); categorias editáveis.
- Testes instrumentados e testes automatizados das regras do Firestore.

## Further Notes

- O projeto Firebase é criado por um humano (conta do Caio, plano Spark gratuito) com um passo a passo gerado para isso. O `google-services.json` é versionado.
- O deck HTML continua com o nome antigo; só o código muda de nome.
