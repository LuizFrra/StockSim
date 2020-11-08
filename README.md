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

## Endpoints para usuários

__/api/v1/user__  - DEPRECATED ENDPOINT
* POST: Cria um usuário. Deve ser enviado um request no formato JSON, contendo os campos: "email", "password", "firstName" e "lastName", sendo este último opcional.
* DEPRECATED: Os usuários são criados agora através do keycloak.
__/api/v1/user/{id}__  
* GET: Busca pelo o usuário através do ID passado por parâmetro na URL.

__/api/v1/user/__
* GET: Obtém todos os usuários do banco de dados.

__/api/v1/user/{id}/buyquote__
* POST: Realiza a compra da cota, é necessário enviar no body as seguintes informações :
<ol>
  <li>quantity : Quantidade da cota</a></li>
  <li>symbol: Cota a ser adquirida</li>
  <li>userId: usuário no qual a compra será efetuada</li>
</ol>
Em um sistema com autenticação, o userId poderá ser pego através do cookie ou token.

## Endpoints para as ações

__/api/v1/quote__  
* POST: Adiciona uma ação. Deve ser enviado um request no formato JSON, contendo o campo: "symbol", que corresponde ao ticket da ação a ser adicionado, exemplo: MGLU3, ITUB4.

__/api/v1/quote/{symbol}__  
* GET: Busca pela a ação passado por parâmetro na URL.

__/api/v1/quote/__
* GET: Busca por todas as ações no banco de dados.
