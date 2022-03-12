# Score API

## Documentação da API

 - A página da interface do usuário do Swagger estará disponível em http://localhost:8080/api/swagger-ui.html

## Endpoints

context-path: http://localhost:8080/api/score

### 1. Cadastrar Pessoa
- METHOD: POST
- PATH: /pessoa
- BODY: 
```
{
	“nome”: “Fulano de Tal”,
	“telefone”: “99 99999-9999”,
	“idade”: 99,
	“cidade”: “Cidade de Fulano”,
	“estado”: “XX”,
	“score”: 1000	// Entre 0 e 1000
}
```
- RESPONSE:
```
Sucesso: 201 - CREATED 
```
```
Falha: 204 - NO_CONTENT
```

### 2. Listar Pessoas
- METHOD: GET
- PATH: /pessoa
- RESPONSE:
```
Sucesso: 200 - OK 
[
  {
    “nome”: “Fulano de Tal”,
    “cidade”: “Cidade de Fulano”,
    “estado”: “XX”,
    “scoreDescricao”: “Recomendável”
  },
  {
    “nome”: “Sicrano de Tal”,
    “cidade”: “Cidade de Sicrano”,
    “estado”: “YY”,
    “scoreDescricao”: “Insuficiente”
  }
]
```  
```
Falha: 204 - NO_CONTENT
```

### 3. Buscar Pessoa pelo ID
- METHOD: GET
- PATH: /pessoa/{id}
- PARAMS:
```
{id}
- Type: Integer
- Description: Identificador único do usuário
```
- RESPONSE:
```
Sucesso: 200 - OK
{
  “nome”: “Fulano de Tal”,
  “telefone”: “99 99999-9999”,
  “idade”: 99,
  “scoreDescricao”: “Recomendável”
}
```
```
Falha: 204 - NO_CONTENT
```

## Stack

- Java 11
- Maven
- Spring Boot
- Spring Cloud
- Spring Doc Open API
- JUnit
- Mockito
- Lombok
- JPA
- Validation
- Sleuth
- H2 Database
