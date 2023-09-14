# DSW1-AA3
Atividade avaliativa 3 referente ao projeto da disciplina Desenvolvimento de Software para Web 1, ministrada pelo professor Alan Demetrius.

## Descrição
O objetivo desta atividade é implementar uma API REST para o sistema para locação de bicicletas que foi desenvolvido na atividade AA2. 
O sistema deve incorporar os seguintes requisitos:

## REST API - CRUD de Clientes
Cria um novo cliente [Create - CRUD]

Rota: POST http://localhost:8080/clientes
Body: raw/JSON (application/json)
Retorna a lista de clientes [Read - CRUD]

Rota: GET http://localhost:8080/clientes
Retorna o cliente de id = {id} [Read - CRUD]

Rota: GET http://localhost:8080/clientes/{id}
Atualiza o cliente de id = {id} [Update - CRUD]

Rota: PUT http://localhost:8080/clientes/{id}
Body: raw/JSON (application/json)
Remove o cliente de id = {id} [Delete - CRUD]

Rota: DELETE http://localhost:8080/clientes/{id}
## REST API - CRUD de Locadoras
Cria uma nova locadora [Create - CRUD]

Rota: POST http://localhost:8080/locadoras
Body: raw/JSON (application/json)
Retorna a lista de locadoras [Read - CRUD]

Rota: GET http://localhost:8080/locadoras
Retorna a locadora de id = {id} [Read - CRUD]

Rota: GET http://localhost:8080/locadoras/{id}
Retorna a lista de todas as locadoras da cidade de nome = {nome}

Rota: GET http://localhost:8080/locadoras/cidades/{nome}
Atualiza a locadora de id = {id} [Update - CRUD]

Rota: PUT http://localhost:8080/locadoras/{id}
Body: raw/JSON (application/json)
Remove a locadora de id = {id} [Delete - CRUD]

Rota: DELETE http://localhost:8080/locadoras/{id}
## REST API - Locações
Retorna a lista de locações [Read - CRUD]

Rota: GET http://localhost:8080/locacoes
Retorna a locação de id = {id} [Read - CRUD]

Rota: GET http://localhost:8080/locacoes/{id}
Retorna a lista das locações do cliente de id = {id} [Read - CRUD]

Rota: GET http://localhost:8080/locacoes/clientes/{id}
Retorna a lista de locações da locadora de id = {id} [Read - CRUD]

Rota: GET http://localhost:8080/locacoes/locadoras/{id}
## Arquitetura
O projeto segue a arquitetura Modelo-Visão-Controlador (MVC).

## Tecnologias Utilizadas
Spring MVC (Controladores REST)
Spring Data JPA
Spring Security
Thymeleaf (Lado Servidor)
Ambiente de Desenvolvimento
A compilação e o deployment devem ser obrigatoriamente realizados via Maven.

## Colaboradores
- Leonardo da Silva Lopes, aluno BCC UFSCar, github: [github.com/leonardo-lopes-br](https://github.com/leonardo-lopes-br)
- Vitor Kasai Tanoue, aluno BCC UFSCar, github: [github.com/vitorkasai](https://github.com/vitorkasai)
- Maria Luiza Edwards, aluna BCC UFSCar, github: [github.com/maluedwards](https://github.com/maluedwards)
- Karen Ketlyn Barcelos, aluna BCC UFSCar, github: [github.com/42kkkkkaren](https://github.com/42kkkkkaren)
