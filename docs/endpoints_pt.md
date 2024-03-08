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
