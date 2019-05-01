# Trabalho de Fiap Microservices


# Documentacao endpoints

##swagger
```sh
http://localhost:8080/transaction-service/swagger-ui.html


 POST 
  http://localhost:8080/transaction-service/transactions
  
  http://localhost:8090/transaction-service/transactions
 json
 { 
	"timestamp": 2447952675125,
	"amount": 2700.10 
  }

GET
  http://localhost:8080/transaction-service/statistics
  http://localhost:8090/transaction-service/statistics

```
# Build do docker
```sh
docker build -t ddaniesouza/transaction-service
```
# Executar instancias 

```sh
docker run -p 8080:8080 -t daniesouza/transaction-service
docker run -p 8090:8080 -t daniesouza/transaction-service
```
