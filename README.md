
# Registro de Usuários API

Esta é uma API REST para cadastro de usuários, desenvolvida com o framework Spring Boot. A API permite o cadastro, consulta, atualização e remoção de usuários com os campos id, name, email e cep. O banco de dados utilizado é o MongoDB, executado em um contêiner Docker.

# Tecnologias Utilizadas

- Java 21
- Spring Boot
- MongoDB
- Docker
- Gradle

# Pré-requisitos

- JDK 17 ou superior
- Docker
- Gradle

# Configuração do MongoDB com Docker

Para rodar o MongoDB em um contêiner Docker, execute o seguinte comando:
```bash
  docker run -d -p 27017:27017 --name mongodb mongo
```
Esse comando fará o download da imagem do MongoDB (se necessário) e iniciará um contêiner com a porta 27017 exposta, permitindo que a aplicação Spring se conecte ao banco de dados.

# Configuração do Projeto

Clone o repositório do projeto:
```bash
  git clone https://github.com/thspanhol/crud-mongo-spring.git
  cd crud-mongo-spring

```
# Executando a Aplicação

Rode a aplicação na IDE de sua preferência, ou utilize o Gradle:
```bash
 ./gradlew bootRun
```
A aplicação será iniciada e estará disponível no endereço http://localhost:8080.

# Endpoints da API

A seguir estão os endpoints disponíveis na API:

- POST /localhost/v1/users: Cadastra um novo usuário.

    - Exemplo de body:
```json
{
  "name": "Steve Rogers",
  "email": "steve.rogers@exemplo.com",
  "cep": "12345678"
}
```
- GET /localhost/v1/users: Retorna a lista de todos os usuários cadastrados.

- GET /localhost/v1/users/{id}: Retorna os dados de um usuário específico pelo ID.

- GET /localhost/v1/users?name={name}: Retorna os dados de usuários que contêm o nome informado.

- PUT /localhost/v1/users/{id}: Atualiza os dados de um usuário existente.

- DELETE /localhost/v1/users/{id}: Remove um usuário pelo ID.

- DELETE /localhost/v1/users/ids?list={list}: Remove usuários pelos IDs informados.

# Estrutura do Projeto

- /src/main/java: Contém o código-fonte da aplicação.

- /src/main/resources: Arquivos de configuração, como application.properties.

- build.gradle: Arquivo de configuração do Gradle.