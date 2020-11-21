![](https://img.shields.io/badge/status-WORK%20IN%20PROGRESS-red)

[![CircleCI](https://circleci.com/gh/LuizFrra/StockSim.svg?style=shield&circle-token=ada2fcfa87f14d394f638a07a21e24477e3d31aa)](https://circleci.com/gh/LuizFrra/StockSim)

# StockSim

Uma API que estou construíndo para propósitos de estudos, juntando duas coisas que eu gosto muito, o mercado de renda variável e programação.

## Objetivo

Essa api tem como por objetivo realizar a simulação de compras e vendas de ativos na bolsa de valores, para alcançar o objetivo desejado, irei utilizar uma API externa para acompanhar a cotação da  bolsa, por enquanto a api será a <a href="https://hgbrasil.com/">HG BRASIL<a/>, essa API permite realizar 400 request por dia, portanto o acompanhamento dos ativos precisam ser bem distribuídos, impedindo que seja realizado em tempo real.

## Tecnologias Utilizadas
<ol>
  <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
  <li><a href="https://spring.io/projects/spring-data-jpa">Spring JPA</a></li>
  <li><a href="http://modelmapper.org/">ModelMapper</a></li>
  <li><a href="https://projectlombok.org/">Lombok</a></li>
  <li><a href="https://www.postgresql.org/">PostgreSQL</a></li>
  <li><a href="https://www.docker.com/">Docker</a></li>
  <li><a href="https://swagger.io/">Swagger</a></li>
</ol>

# Utilizando API

# 📁 Coleção: User 


## End-point: Get User

### Descrição: Nesse Endpoint é possível obter informações a respeito de um usuário específico, deve ser realizado uma requisição do tipo GET, passando como parâmetro o UUID do usuário desejado.

Método: GET

>```
>http://{{connectAddress}}/api/v1/user/UUID
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Get All Users

### Descrição: Nesse EndPoint é possível listar e obter as informações de todos os usuários cadastrados na aplicação.

Método: GET

>```
>http://{{connectAddress}}/api/v1/user/
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | Bearer {{token}} |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Buy Quote

### Descrição: Nesse EndPoint é possível realizar a compra/venda de um ativo previamente cadastrado no banco de dados da aplicação, para a compra deve ser passado para o atributo "operationType" um dos seguintes valores: 1 ou "BUY". Após a compra ser realizada com sucesso, será disparado um e-mail para o usuário que realizou a compra, nesse e-mail é informado o ativo comprado e a quantidade.

Método: POST

>```
>http://{{connectAddress}}/api/v1/user/quote
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |


### Body (**raw**)

```json
{
    "symbol": "MGLU3",
    "quantity": 50,
    "operationType": "BUY"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Sell Quote

### Descrição: Nesse EndPoint é possível realizar a compra/venda de um ativo previamente cadastrado no banco de dados da aplicação, para a compra deve ser passado para o atributo "operationType" um dos seguintes valores: 0 ou "SELL". Após a venda ser realizada com sucesso, será disparado um e-mail para o usuário que realizou a venda, nesse e-mail é informado o ativo vendido e a quantidade.

Método: POST

>```
>http://{{connectAddress}}/api/v1/user/quote
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |


### Body (**raw**)

```json
{
    "symbol": "MXRF11",
    "quantity": 200,
    "operationType": 0
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

# 📁 Coleção: Quote 


## End-point: Create Quote

### Descrição: Nesse EndPoint é possível incluir um ativo da bolsa de valores no banco de dados da aplicação, para isso basta enviar uma requsição do tipo POST contendo no atributo symbol o código do ativo desejado na base de dados.

Método: POST

>```
>http://{{connectAddress}}/api/v1/quote
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |


### Body (**raw**)

```json
{
    "symbol": "MXRF11"
}
```


⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Get Quote

### Descrição: Nesse EndPoint é possível obter informações a respeito de um determinado ativo previamente incluído no banco de dados, bastando apenas passar o código do mesmo como parâmetro na URL.

Método: GET

>```
>http://{{connectAddress}}/api/v1/quote/PETR4
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃


## End-point: Get All Quotes

### Descrição: Nesse EndPoint é possível listar e obter informações a respeito de todos ativos previamente cadastrados no banco de dados.

Método: GET

>```
>http://{{connectAddress}}/api/v1/quote
>```

### Headers

| Content-Type  | Value            |
| ------------- | ---------------- |
| Authorization | BEARER {{token}} |



⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃ ⁃

# 📁 Coleção: Security 

## End-point:  🔑  Basic Authentication EndPoint 

### Descrição: Nesse EndPoint é realizado a autenticação utilizando O Basic Auth, portanto aqui é enviado no Header Authorization contendo o login e senha no seguinte formato: 'BASIC login:senha', esses dados devem ser codificado para base 64 conforme o Padrão MIME.

Método: GET

>```
>http://{{connectAddress}}/api/basicauth
>```

### Headers

| Content-Type  | Value                      |
| ------------- | -------------------------- |
| Authorization | Basic dXNlcjE6bHVpejEyMw== |


### 

_________________________________________________



## Estrutura do Projeto

- 📂 __StockSim__
   - 📄 [README.md](README.md)
   - 📄 [build.gradle](build.gradle)
   - 📄 [docker\-compose.yml](docker-compose.yml)
   - 📄 [dockerfile](dockerfile)
   - 📄 [list.md](list.md)
   - 📄 [realm\-export.json](realm-export.json)
   - 📄 [settings.gradle](settings.gradle)
   - 📂 __src__
     - 📂 __main__
       - 📂 __java__
         - 📂 __com__
           - 📂 __luizfrra__
             - 📂 __stockSim__
               - 📂 __Configurations__
                 - 📄 [SwaggerConfig.java](src/main/java/com/luizfrra/stockSim/Configurations/SwaggerConfig.java)
               - 📂 __Controllers__
                 - 📂 __Commons__
                   - 📄 [BaseController.java](src/main/java/com/luizfrra/stockSim/Controllers/Commons/BaseController.java)
                 - 📂 __Exception__
                   - 📄 [ExceptionHandlerController.java](src/main/java/com/luizfrra/stockSim/Controllers/Exception/ExceptionHandlerController.java)
                 - 📂 __Quote__
                   - 📄 [QuoteController.java](src/main/java/com/luizfrra/stockSim/Controllers/Quote/QuoteController.java)
                 - 📂 __User__
                   - 📄 [UserController.java](src/main/java/com/luizfrra/stockSim/Controllers/User/UserController.java)
               - 📂 __DTOs__
                 - 📂 __Commons__
                   - 📄 [CommonDTO.java](src/main/java/com/luizfrra/stockSim/DTOs/Commons/CommonDTO.java)
                 - 📂 __Quote__
                   - 📄 [QuoteDTO.java](src/main/java/com/luizfrra/stockSim/DTOs/Quote/QuoteDTO.java)
                 - 📂 __User__
                   - 📄 [UserDTO.java](src/main/java/com/luizfrra/stockSim/DTOs/User/UserDTO.java)
                 - 📂 __UserQuotes__
                   - 📄 [OperationType.java](src/main/java/com/luizfrra/stockSim/DTOs/UserQuotes/OperationType.java)
                   - 📄 [UserQuotesDTO.java](src/main/java/com/luizfrra/stockSim/DTOs/UserQuotes/UserQuotesDTO.java)
               - 📂 __EntitiesDomain__
                 - 📂 __Exception__
                   - 📄 [ErrorInfo.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/Exception/ErrorInfo.java)
                 - 📂 __Message__
                   - 📄 [Message.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/Message/Message.java)
                 - 📂 __Quote__
                   - 📄 [Quote.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/Quote/Quote.java)
                 - 📂 __User__
                   - 📄 [User.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/User/User.java)
                 - 📂 __UserQuotes__
                   - 📄 [UserQuotes.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/UserQuotes/UserQuotes.java)
                   - 📄 [UserQuotesKey.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/UserQuotes/UserQuotesKey.java)
                 - 📂 __UserRegister__
                   - 📄 [UserRegisterDetails.java](src/main/java/com/luizfrra/stockSim/EntitiesDomain/UserRegister/UserRegisterDetails.java)
               - 📂 __Exceptions__
                 - 📄 [AbstractGeralException.java](src/main/java/com/luizfrra/stockSim/Exceptions/AbstractGeralException.java)
                 - 📄 [DataAlreadyExistException.java](src/main/java/com/luizfrra/stockSim/Exceptions/DataAlreadyExistException.java)
                 - 📄 [NotMeetRequisitesException.java](src/main/java/com/luizfrra/stockSim/Exceptions/NotMeetRequisitesException.java)
                 - 📄 [QuoteNotExistException.java](src/main/java/com/luizfrra/stockSim/Exceptions/QuoteNotExistException.java)
               - 📂 __HGBrasil__
                 - 📄 [HGAPIConsumer.java](src/main/java/com/luizfrra/stockSim/HGBrasil/HGAPIConsumer.java)
               - 📂 __RabbitMQ__
                 - 📄 [RabbitStartUp.java](src/main/java/com/luizfrra/stockSim/RabbitMQ/RabbitStartUp.java)
                 - 📄 [RegisterConsumer.java](src/main/java/com/luizfrra/stockSim/RabbitMQ/RegisterConsumer.java)
               - 📂 __Repositories__
                 - 📂 __Quote__
                   - 📄 [QuoteRepository.java](src/main/java/com/luizfrra/stockSim/Repositories/Quote/QuoteRepository.java)
                 - 📂 __User__
                   - 📄 [UserRepository.java](src/main/java/com/luizfrra/stockSim/Repositories/User/UserRepository.java)
                 - 📂 __UserQuotes__
                   - 📄 [UserQuotesReposittory.java](src/main/java/com/luizfrra/stockSim/Repositories/UserQuotes/UserQuotesReposittory.java)
               - 📂 __Responses__
                 - 📄 [InvalidFieldsResponse.java](src/main/java/com/luizfrra/stockSim/Responses/InvalidFieldsResponse.java)
                 - 📄 [ObjectResponse.java](src/main/java/com/luizfrra/stockSim/Responses/ObjectResponse.java)
               - 📂 __Security__
                 - 📄 [BasicAuthFilter.java](src/main/java/com/luizfrra/stockSim/Security/BasicAuthFilter.java)
                 - 📄 [KeycloakSecurityConfig.java](src/main/java/com/luizfrra/stockSim/Security/KeycloakSecurityConfig.java)
                 - 📄 [TokenCreationResponse.java](src/main/java/com/luizfrra/stockSim/Security/TokenCreationResponse.java)
               - 📂 __Services__
                 - 📂 __Commons__
                   - 📄 [IBaseService.java](src/main/java/com/luizfrra/stockSim/Services/Commons/IBaseService.java)
                 - 📂 __Email__
                   - 📄 [EmailServiceConfiguration.java](src/main/java/com/luizfrra/stockSim/Services/Email/EmailServiceConfiguration.java)
                   - 📄 [FallBackEmailService.java](src/main/java/com/luizfrra/stockSim/Services/Email/FallBackEmailService.java)
                   - 📄 [IEmailService.java](src/main/java/com/luizfrra/stockSim/Services/Email/IEmailService.java)
                 - 📂 __Quote__
                   - 📄 [QuoteService.java](src/main/java/com/luizfrra/stockSim/Services/Quote/QuoteService.java)
                 - 📂 __User__
                   - 📄 [UserService.java](src/main/java/com/luizfrra/stockSim/Services/User/UserService.java)
                 - 📂 __UserQuotes__
                   - 📄 [UserQuotesService.java](src/main/java/com/luizfrra/stockSim/Services/UserQuotes/UserQuotesService.java)
               - 📄 [StockSimApplication.java](src/main/java/com/luizfrra/stockSim/StockSimApplication.java)
               - 📂 __Utils__
                 - 📄 [DateStockUtils.java](src/main/java/com/luizfrra/stockSim/Utils/DateStockUtils.java)
                 - 📄 [StringStockUtils.java](src/main/java/com/luizfrra/stockSim/Utils/StringStockUtils.java)
       - 📂 __resources__
         - 📄 [application.yml](src/main/resources/application.yml)
     - 📂 __test__
       - 📂 __java__
         - 📂 __com__
           - 📂 __luizfrra__
             - 📂 __stockSim__
               - 📄 [StockSimApplicationTests.java](src/test/java/com/luizfrra/stockSim/StockSimApplicationTests.java)
               - 📄 [UserControllerTest.java](src/test/java/com/luizfrra/stockSim/UserControllerTest.java)
       - 📂 __resources__
         - 📄 [application\-test.properties](src/test/resources/application-test.properties)
