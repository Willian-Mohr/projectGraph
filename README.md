# Projeto Graph
## O projeto em questão tem as seguintes tecnologias
- [Spring Boot]
- [JUnit]
- [Mockito]
- [H2 Data base]
- [Lombok]
- [Rest Assured]
---
### Testes unitários
- Os testes foram aplicados apenas para o GraphController
---
### Consumindo as API
Para consumir as APIs basta informar os seguintes endpoint:
- ##### GRAPH

| METHOD | API | ENDPOINT |
| ------ | ------ | ------ |
| POST | GRAPH INSERT |http://localhost:8080/graph
| GET | GRAPH FIND BY ID | http://localhost:8080/graph/{id}
| GET | GRAPH FIND ALL | http://localhost:8080/graph
| DELETE | GRAPH DELETE | http://localhost:8080/graph/{id}
- ##### DATA
| METHOD | API | ENDPOINT |
| ------ | ------ | ------ |
| GET | DATA FIND BY ID | http://localhost:8080/data/{id}
| GET | DATA FIND ALL | http://localhost:8080/data
| DELETE | DATA DELETE | http://localhost:8080/data/{id}
| PUT | DATA PUT |http://localhost:8080/data/{id}
 Obs.: 
- Afim de facilitar o processo, foi versionado uma [collection.json](https://github.com/Willian-Mohr/projectGraph/blob/develop/documents/Postaman%20collection/Project%20Graph.postman_collection.json), ou seja, o usuário poderá importala para para realizar o consumo das APIs criadas.
---
#### Para subir o projeto basta apenas clonar o repositório e na pasta raiz, rodar a comand line [mvn spring-boot:run]
