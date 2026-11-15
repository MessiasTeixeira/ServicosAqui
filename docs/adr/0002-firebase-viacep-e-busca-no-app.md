# Firebase, ViaCEP e busca feita no app

A disciplina exige banco na nuvem com CRUD e uma API REST consumida com Retrofit. Usamos Firebase Auth + Cloud Firestore (plano Spark, gratuito) como banco e login, e a API ViaCEP para preencher a Área atendida a partir do CEP no formulário do Anúncio: é HTTPS, sem chave e tem uso natural no app. Como o Firestore não faz busca por texto, o app carrega os Anúncios ativos e aplica a busca por termo e o filtro por Categoria em memória; na escala do projeto isso é suficiente e deixa a regra de busca num só lugar, fácil de testar.
