# Sumarizador de Pessoas

Esta é uma API de sumarização de dados de pessoas, utilizando Spring Boot, Spring Web, Spring Data JPA, H2 Database e Lombok. Ela fornece estatísticas sobre a distribuição de pessoas por sexo e idade nos diferentes estados.

## Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- [JDK 22](https://www.oracle.com/br/java/technologies/downloads/)
- [Maven](https://maven.apache.org/install.html)
- [Git](https://git-scm.com/downloads)

## Clonando o Repositório

Para clonar o repositório, execute o seguinte comando no terminal:

```bash
git clone https://github.com/skafikis/ProcessoSeletivoCTMGEO.git
cd ProcessoSeletivoCTMGEO
```

## Configuração da API

A API utiliza um banco de dados H2 em memória. Não é necessário configurar nenhum banco de dados externo.

## Executando a API
Compile utilizando Maven:

```bash
mvn spring-boot:run
```

Este comando compilará o projeto e iniciará o servidor embutido do Spring Boot. Assim que o servidor estiver em execução, você poderá acessar a aplicação através do seguinte URL: localhost:8080.

## Executando Testes

Execute os testes utilizando Maven:

```bash
mvn test
```
