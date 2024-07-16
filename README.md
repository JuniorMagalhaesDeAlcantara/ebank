# E-Bank

E-Bank é uma API de banco digital desenvolvida em Java usando Spring Boot. Esta aplicação permite realizar operações bancárias básicas, como saque, depósito e transferência entre contas, além de funcionalidades de criação e gerenciamento de contas.

## Funcionalidades

- **Criação de Conta**: Permite criar uma nova conta de usuário.
- **Saque**: Realiza a retirada de um valor da conta do usuário.
- **Depósito**: Permite adicionar um valor à conta do usuário.
- **Transferência**: Transfere valores entre contas de diferentes usuários.
- **Consulta de Saldo**: Exibe o saldo atual da conta do usuário.
- **Deleção de Conta**: Permite deletar uma conta de usuário.
- **Atualização de Conta**: Permite atualizar as informações de uma conta de usuário.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Insomnia** (para testar as APIs)

## Pré-requisitos

- **Java 17** instalado
- **PostgreSQL** instalado e configurado

## Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/JuniorMagalhaesDeAlcantara/ebank.git

2. Navegue até o diretório do projeto:
    ```bash
    cd 
    
3. Configure o banco de dados PostgreSQL:
- **Crie um banco de dados chamado ebank.**
- **Atualize o arquivo application.properties com suas credenciais do PostgreSQL:**    
      ```bash
      spring.application.name=ebank
        spring.datasource.url=jdbc:postgresql://localhost:5432/ebank
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        spring.datasource.driver-class-name=org.postgresql.Driver
        spring.jpa.show-sql=true
        spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
        spring.jpa.hibernate.ddl-auto=update

4. Execute a aplicação
    ```bash
      ./mvnw spring-boot:run


## Testando a API

  Use uma ferramenta como o Insomnia ou Postman para testar os endpoints da API. Abaixo estão alguns exemplos de requisições:

## Criar Conta 

- **Endpoint: POST /api/customers**
- **Body**
  ```bash
        {
         "name": "John Doe",
         "email": "john.doe@example.com",
         "password": "123"
        }

## Saque

- **Endpoint: POST /api/accounts/withdraw**
- **Body**
  ```bash
    {
     "accountId": 1,
     "amount": 100.0
    }

## Deposito

- **Endpoint: POST /api/accounts/deposit**
- **Body:**
  ```bash
    {
     "accountId": 1,
     "amount": 200.0
    }

## Transferência

- **Endpoint: POST /api/accounts/transfer**
- **Body:**
    ```bash
    {
    "sourceAccountId": 1,
    "targetAccountId": 2,
    "amount": 50.0
    }

## Consultar Saldo

- **Endpoint: GET /api/accounts/balance/{accountId}**

## Deletar Cliente

- **Endpoint: DELETE /api/customers/{id}**

## Atualizar Cliente

- **Endpoint: PUT /api/customers/{id}**
- **Body:**    
  ```bash
     {
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "password": "456"
    }

## Contribuição

Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar bugs, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença
Este projeto está licenciado sob a MIT License.




