![](https://img.shields.io/badge/status-WORK%20IN%20PROGRESS-red)

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
</ol>

## Utilizando API
__/api/v1/user__  
* POST: Cria um usuário. Deve ser enviado um request no formato JSON, contendo os campos: "email", "password", "firstName" e "lastName", sendo este último opcional.

__/api/v1/user/{id}__  
* GET: Busca pelo o usuário através do ID passado por parâmetro na URL.

__/api/v1/quote__  
* POST: Adiciona uma ação. Deve ser enviado um request no formato JSON, contendo o campo: "symbol", que corresponde ao ticket da ação a ser adicionado, exemplo: MGLU3, ITUB4 ...

__/api/v1/quote/{symbol}__  
* GET: Busca pela a ação passado por parâmetro na URL.
