# Entidades 

## Obter todas as entidades
Endpoint: `GET products/entities`

### Parâmetros de Consulta Opcionais:
- `orderby`: Ordena o resultado da requisição. Padrões válidos: ["id", "name"]
- `order`: Escolher se o resultado da requisição tera ordem crescente ou decrescente. Padrões válidos: ["asc" ,"desc"]

### Exemplo de Requisição:
````
GET /products/entities?orderby=id&order=desc
``````

## Obter entitdade por id
Endpoint: `GET products/entities/{id}`

 - Body: json
 - Status: 200 || 404

## Criar nova entidade
Endpoint: `POST products/entities/{id}`
````json
{
    "name": "Macarrão"
}

``````
- Body: json
- Status: 201
## Editar entidade
Endpoint: `PUT products/entities/{id}`
````json
{
    "name": "Leite"
}

``````
 - Body: json
 - Status: 200 || 404

## Deletar entidade
Endpoint: `DELETE products/entities/{id}`
 - Body: json
 - Status: 200 || 404

# Produtos
## Obter Todos os Produtos

Endpoint: `GET /products`
### Parâmetros de Consulta Opcionais:

- `orderby`: Ordena o resultado da requisição. Padrões válidos: ["id", "entityId"]
- `order`: Escolher se o resultado da requisição tera ordem crescente ou decrescente. Padrões válidos: ["asc" ,"desc"]
- `sold`: Filtrar entre produtos vendidos e não vendidos. Padrões válidos: ["true", "false"]

### Exemplo de Requisição:
````
GET /products?orderby=id&order=asc&sold=true
``````
- Body: json
- Status: 200

## Obter produtos por id
Endpoint: `GET /products/{id}`

- Body: json
- Status: 200 || 404

## Registrar novo produto
Endpoint: `POST /products`

### Exemplo de requisição
`````json
{
 "entityId": 1
}

`````````
- Body: json
- Status: 200 || 404


## Editar status do produto
Endpoint: `PUT /products/{id}`

### Exemplo de requisição
`````json
{
 "sold": true
}

`````````
- Body: json
- Status: 200 || 404


## Deletar produto
Endpoint: `DELETE /products/{id}`

- Body: json
- Status: 200 || 404

# Transações
## Todas as transações
Endpoint: `GET /transaction`

### Parâmetros de Consulta Opcionais:
- `order`: Escolher se o resultado da requisição tera ordem crescente ou decrescente. Padrões válidos: ["asc" ,"desc"]

- Body: json
- Status: 200

## Transação por id
Endpoint: `GET /transaction/{id}`
- Body: json
- Status: 200 || 404
## transação por id do produto
Endpoint: `GET /transaction/product/{id}`
- Body: json
- Status: 200 || 404
## Nova transação
Endpoint: `GET /transaction/product/{id}`

`````json
{
    "cpf": "111.111.111-11",
    "productID": 1
}

`````````

- Body: json
- Status: 201 || 404

## Deletar transação por id
Endpoint: `DELETE /transaction/{id}`
- Body: json
- Status: 200 || 404

# Users 
roles = ["admin", "employee"]
## Login
Endpoint: `POST /users/login`

```json
{
    "email": "dummy@email.com",
	"password": "1234"
}
```
- Body: json
- Status: 200 || 403 || 404

## Todos os usuarios
Endpoint: `GET /users`

- `orderby`: Ordena o resultado da requisição. Padrões válidos: ["id", "username", "email"]
- `order`: Escolher se o resultado da requisição tera ordem crescente ou decrescente. Padrões válidos: ["asc" ,"desc"]

### Exemplo de Requisição:
````
GET /users?orderby=id&order=desc
``````
- Body: json
- Status: 200

## Usuario por id
Endpoint: `GET /users/{id}`

- Body: json
- Status: 200 || 404

## Registrar novo usuario
Endpoint: `POST /users`

```json
{
    "username": "dummy",
    "email": "dummy@email.com",
	"password": "1234",
    "role": "employee"
}
```
- Body: json
- Status: 200 || 403
## Alterar usuario

Endpoint: `POST /users/{id}`

```json
{
    "username": "dummy",
    "email": "dummy@email.com",
    "role": "employee"
}
```
- Body: json
- Status: 200 || 403 || 404
