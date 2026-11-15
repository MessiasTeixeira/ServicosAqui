# Requisitos: onde cada um está no código

Guia para a apresentação. Para cada requisito: onde está o código e como mostrar no app.

Os caminhos abaixo começam em `app/src/main/java/com/example/servicosaqui/`. Os testes ficam em `app/src/test/java/com/example/servicosaqui/model/`.

Atualize este arquivo sempre que mover ou criar um arquivo de requisito.

## 1. Requisitos da disciplina

| Requisito | Arquivo e função/classe | Como mostrar no app |
|---|---|---|
| Interface com Compose e Material, adaptada a qualquer tela | `ui/theme/Theme.kt` (`ServicosAquiTheme`), `ui/theme/Color.kt`; componentes Material em `ui/screens/InicioScreen.kt` (`FilterChip`, `Card`), `ui/screens/AnunciosScreen.kt` (`FloatingActionButton`, `Switch`, `AlertDialog`) e `ui/navigation/AppNavigation.kt` (`Scaffold`, `NavigationBar`); formulários com `verticalScroll` em `ui/screens/AnuncioFormScreen.kt` | Abra o app, mostre as cores e a barra inferior, aumente a fonte do sistema e veja as telas rolarem. |
| Busca com filtro | `model/FiltroCatalogo.kt` (`filtrarAnuncios`); `viewmodels/CatalogoViewModel.kt` (`alterarTermo`, `alternarCategoria`); tela em `ui/screens/InicioScreen.kt` (`InicioScreen`); testes em `FiltroCatalogoTest.kt` | No Início, toque numa Categoria e digite "eletrica": a lista muda enquanto você digita. |
| Navegação entre telas | `ui/navigation/AppNavigation.kt` (`AppNavigation`, com `NavHost` e rotas `inicio`, `perfil/{id}`, `anuncios`, `formulario?id={id}`, `login`, `cadastro`) | Toque num card para abrir o Perfil, volte, e troque de aba na barra inferior. |
| CRUD no Cloud Firestore | `model/AnuncioDao.kt` (`criar`, `listarTodos`, `listarAtivos`, `buscarPorId`, `atualizar`, `excluir`); `viewmodels/AnunciosViewModel.kt` (`carregar`, `alternarAtivo`, `excluir`); `viewmodels/AnuncioFormViewModel.kt` (`salvar`); telas `ui/screens/AnunciosScreen.kt` e `ui/screens/AnuncioFormScreen.kt`; regras em `firestore.rules` | Na aba Anúncios, crie, edite, desative e exclua um anúncio; feche e abra o app e veja que os dados continuam. |
| API REST com Retrofit | `clientservice/EnderecoAPI.kt` (`getEnderecoByCEP`); `clientservice/CepRepository.kt` (`getEnderecoByCEP`, devolve `Result`); `viewmodels/AnuncioFormViewModel.kt` (`buscarCep`); `model/AreaAtendida.kt` (`montarAreaAtendida`); testes em `AreaAtendidaTest.kt` | No formulário, digite um CEP, toque em Buscar e veja a Área atendida preenchida. |

## 2. Requisitos do deck cobertos na Fase 1

Os códigos são os do deck da Fase I (slides 8 a 13).

| Código | O que a Fase 1 faz | Arquivo(s) |
|---|---|---|
| RF01 | O app abre no catálogo, sem login, com as Categorias no topo | `ui/navigation/AppNavigation.kt` (`startDestination = "inicio"`), `ui/screens/InicioScreen.kt` |
| RF02 | Busca por termo e filtro por Categoria, combinados | `model/FiltroCatalogo.kt` (`filtrarAnuncios`) |
| RF03 | Cada card mostra nome, Categoria e Área atendida | `ui/screens/InicioScreen.kt` (`CartaoCatalogo`) |
| RF04 | Perfil público com a descrição completa e a Área atendida | `ui/screens/PerfilScreen.kt`, `viewmodels/PerfilViewModel.kt` |
| RF08 | Criar e editar um Anúncio no mesmo formulário, com validação | `ui/screens/AnuncioFormScreen.kt`, `viewmodels/AnuncioFormViewModel.kt` (`carregar`, `salvar`) |
| RF09 | Ativar ou desativar um Anúncio e ver o catálogo mudar | `viewmodels/AnunciosViewModel.kt` (`alternarAtivo`), `model/AnuncioDao.kt` (`atualizar`) |
| RN01 | Nome, Categoria, descrição e Área atendida são obrigatórios (o WhatsApp entra na Fase 2) | `model/ValidacaoAnuncio.kt` (`validarAnuncio`) |
| RN02 | O catálogo mostra só Anúncios ativos | `model/AnuncioDao.kt` (`listarAtivos`), `viewmodels/CatalogoViewModel.kt` |
| RN05 | Sem preços, notas ou certificações | `model/Anuncio.kt` (o Anúncio não tem esses campos) |
| RN06 | Sem pedidos, agenda, pagamento ou comissão | Os modelos `Solicitacao`, `ServicoPrestador` e afins foram removidos |
| RNF02 | Telas se adaptam ao tamanho e à fonte ampliada | Telas com rolagem: `ui/screens/AnuncioFormScreen.kt`, `ui/screens/PerfilScreen.kt`, `ui/screens/LoginScreen.kt` |
| RNF03 | Rótulos claros e bom contraste | `ui/theme/Theme.kt`, `ui/theme/Color.kt` |
| RNF04 | Dados salvos na nuvem e só HTTPS | `model/AnuncioDao.kt` (Firestore), `clientservice/CepRepository.kt` (`https://viacep.com.br`) |
| RNF07 | Falhas viram mensagem na tela, sem fechar o app | Blocos `try/catch` em `viewmodels/` e `CepRepository.getEnderecoByCEP` |

## 3. Fica para a Fase 2

- Login e cadastro reais (Firebase Auth), sessão e aba Conta funcional (RF05, RF07).
- Dono do Anúncio (um por Prestador) e regras do Firestore por dono (RF07).
- Campo WhatsApp, botão Contatar, volta ao perfil depois do login e Contato protegido na origem (RF06, RN03, RN04, RNF05, ADR 0001).
- Aviso de exibição do contato (RNF06) e "Esqueceu a senha?".
- Pedidos, agenda, pagamento, comissão (RN06); preços, avaliações e certificações (RN05); categorias editáveis.
- Testes instrumentados e testes automatizados das regras do Firestore.
