# CREDIT CARD INTEGRATOR

---
<!-- 
# OVERVIEW

---
--> 
# GRUPO

- RM346315: Lais Kagawa ([lakagawa](https://github.com/lakagawa))
- RM346511: Jônatha Lacerda Gonzaga ([jhowlacerda](https://github.com/jhowlacerda))
- RM346958: Thiago de Souza Zanella ([zanella86](https://github.com/zanella86))

---

# FERRAMENTAS / TECNOLOGIAS

<!-- - [Draw.io](https://app.diagrams.net/) -->
- Git / Github
- Gradle 7.6
- IntelliJ IDEA Community Edition (2022.2.1)
- MySQL Workbench 8.0 CE
- Spring Framework 3.0.4 (Java 17+)
- Swagger

---

# CONSTRUÇÃO/JUSTIFICATIVA

<u>Utilizamos o Spring Framework:</u>

- **Spring Batch:** Para carga dos arquivos/integração
- **Spring Web:** Para disponibilização dos _endpoints_ (Tomcat embutido)
- **Spring Data JPA:** Persistência via Hibernate

<u>Outros:</u>

- MySQL Driver: Para utilização do MySQL
- Lombok: Para redução da verbosidade

![Spring Initializr](docs/spring-initializr-setup.PNG)

---

# PARA TESTAR

## Bancos de dados

### MySQL

- Crie um *database schema* no MySQL chamado `credit-card-integrator`

![MySQL-Create-Schema](docs/mysql-schema-create.PNG)

## Aplicação

### Swagger

Documentação disponível em: http://localhost:8080/creditcard-integrator/swagger-ui/index.html#/

![Swagger - Home](docs/swagger.PNG)

---

# REFERÊNCIAS

- [Prof.º Fabio Tadashi - Travel](https://github.com/fabiotadashi/1SCJR-travel)
- [Spring Initializr](https://start.spring.io/;)