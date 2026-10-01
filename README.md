# Controle de Estoque

> **Link da Apresentação em Vídeo:** [Insira o link aqui]

---

Descrição

Este projeto é um sistema simples de Controle de Estoque em Java. Permite cadastrar, listar, buscar, atualizar e remover produtos usando um menu no console.

Organização do projeto

Projeto (estrutura de pastas):

README.md
src
└── br
    └── edu
        └── aesa
            ├── app
            │   └── Main.java
            ├── model
            │   └── Produto.java
            └── service
                └── EstoqueService.java

Principais classes

- Produto (br.edu.aesa.model.Produto)
  - Campos: id (int), nome (String), categoria (String), preco (double), quantidade (int).

- EstoqueService (br.edu.aesa.service.EstoqueService)
  - Faz o CRUD e guarda os produtos em um Map (na implementação usamos ConcurrentHashMap<Integer, Produto>)
  - Gera IDs com AtomicInteger quando o produto não informa um ID.

- Main (br.edu.aesa.app.Main)
  - Menu em loop com Scanner para interação com o usuário.

Estrutura de dados

- Usei um Map onde a chave é o ID (Integer) e o valor é o Produto.
- Na prática, o código usa ConcurrentHashMap para armazenar os produtos e AtomicInteger para gerar IDs.
- Motivo: precisamos procurar, atualizar e remover produtos pelo ID; o mapa torna essas operações rápidas (tempo médio constante).

Complexidade (média)

- Cadastro: O(1)
- Busca por ID: O(1)
- Atualização: O(1)
- Remoção: O(1)
- Listagem de todos: O(n)

Funcionalidades (menu)

1. Cadastrar produto
2. Listar produtos
3. Buscar produto por ID
4. Atualizar produto
5. Remover produto
0. Sair

Fluxos principais

1) Cadastrar produto:
- O usuário informa nome, categoria, preço e quantidade (se informar um ID <= 0, o sistema gera um ID automático).
- O sistema valida nome obrigatório e evita duplicar ID quando informado.

2) Listar produtos:
- Mostra todos os produtos cadastrados (ou mensagem se estiver vazio).

3) Buscar por ID:
- Retorna o produto com o ID informado ou mensagem de não encontrado.

4) Atualizar produto:
- Localiza o produto pelo ID.
- Permite atualizar apenas os campos informados (deixar em branco mantém o valor atual).

5) Remover produto:
- Remove o produto pelo ID e informa sucesso/fracasso.

Validações

- ID deve ser número (o sistema trata NumberFormatException no input).
- Nome e categoria não podem ficar vazios ao cadastrar.
- Preço e quantidade devem ser números; não podem ser negativos.
- Ao atualizar, campos numéricos vazios são tratados para não alterar o valor atual.

Como compilar e executar (Windows)

Pré-requisito: JDK 11+

Abra o terminal na raiz do projeto e rode:

javac -d out src\\br\\edu\\aesa\\**\\*.java
java -cp out br.edu.aesa.app.Main

Ou rode pela sua IDE (IntelliJ, Eclipse) abrindo o projeto e executando a classe Main.

Exemplo de uso rápido

1) Escolha 1 para cadastrar:
   Nome: Arroz
   Categoria: Alimentos
   Preço: 25.90
   Quantidade: 10

2) Escolha 2 para listar — verá o produto cadastrado com ID gerado.

3) Escolha 3 e informe o ID para buscar.

4) Escolha 4 para atualizar (deixe campos em branco para manter valores).

5) Escolha 5 para remover pelo ID.


---

Feito por: Pedro Vitor — 3º período ADS
