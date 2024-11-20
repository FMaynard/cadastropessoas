# CadastroSimplesAPI

## Descrição do Projeto
CadastroSimplesAPI é uma aplicação Java desenvolvida para realizar operações CRUD (Create, Read, Update, Delete) em um cadastro de pessoas. A aplicação utiliza Java 17, JPA, Lombok e segue os princípios de clean code e clean architecture. O banco de dados utilizado é o MySQL, que é levantado utilizando Docker.

## Funcionalidades
- Cadastro de pessoas
- Consulta de pessoas
- Atualização de informações de pessoas
- Remoção de pessoas

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **JPA (Java Persistence API)**
- **Lombok**
- **MySQL**
- **Docker**
- **Maven**

## Pré-requisitos
- Docker e Docker Compose instalados
- Java 17 instalado
- Maven instalado

## Configuração do Ambiente

### Passo 1: Configurar o MySQL utilizando Docker
Crie um arquivo `docker-compose.yml` com o seguinte conteúdo:

```yaml
version: '3.1'

services:
  db:
    image: mysql:latest
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: cadastro
      MYSQL_USER: user
      MYSQL_PASSWORD: password
    ports:
      - "3306:3306"
