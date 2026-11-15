---
status: proposed (Fase 2)
---

# Contato do Anúncio fica num documento separado, protegido pelas regras do Firestore

A RNF05 pede que o WhatsApp seja protegido na origem dos dados, não só escondido na tela. Na Fase 2, quando houver login, o Contato não fica no documento público do Anúncio: fica num subdocumento privado do Anúncio (`anuncios/{uid}/privado/contato`), e as regras do Firestore só liberam esse subdocumento para usuários logados. O documento público é legível por qualquer pessoa apenas quando o Anúncio está ativo, e só o dono (o Prestador cujo uid é o id do Anúncio) escreve nos dois.

Na Fase 1 não há login nem campo WhatsApp, e as regras liberam a coleção `anuncios` para qualquer pessoa, só validando os campos. Isso é temporário: a Fase 2 troca essas regras pelas descritas aqui.

## Considered Options

- Guardar o número no Anúncio e esconder na tela: rejeitado, qualquer leitura direta do banco entrega o número (viola RNF05).
- Uma Cloud Function que devolve o número: rejeitada, exige o plano pago (Blaze) e acrescenta código de servidor sem ganho para a disciplina.
