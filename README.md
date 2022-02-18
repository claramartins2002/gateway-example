# Microservice - Gateway

Serviço de roteamento desenvolvido para gerenciar micro serviços independentes em uma só porta do localhost. 


## Linguagens e tecnologias utilizadas

- Spring Boot
- Spring Cloud Gateway


## Dependências

No [inicializador spring](https://start.spring.io/), as dependências adicionadas foram:
**App Servidor**
- Spring Cloud Gateway

**App Cliente**
- Spring Web
- H2 banco de dados
- JPA
- Lombok

## Instruções de uso
Primeiramente, o serviço de roteamento, presente na pasta route, deve ser iniciado, para depois iniciar os dois micro serviços, a fim e evitar erros de compilação. Uma vez startados, os serviços podem ter os métodos GET, POST, PUT e DELETE nas rotas http://localhost:8080/books e http://localhost:8080/movies. 




