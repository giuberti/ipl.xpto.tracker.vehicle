# IPL XPTO Tracking Vehicles

O objectivo deste microserviço é o gerenciamento de veículos. 
Foi desenvolvido usando o Framework Spring, especificamente o Spring Boot e o Spring Data JPA, com um banco de dados PostgreSQL.
Orientação conforme artigo: [Spring Boot, Spring Data JPA – Rest CRUD API example](https://www.bezkoder.com/spring-boot-jpa-crud-rest-api/).

## Uso

Para utilizar este projeto, basta clonar o repositório e importá-lo para o seu IDE preferido. Antes de executar a aplicação, certifique-se de que você tem o PostgreSQL instalado e crie um novo banco de dados para este projeto. Você pode então configurar a conexão com o banco de dados editando o arquivo `application.properties` localizado na pasta `src/main/resources`.

### Configuração

Para alterar a porta da API REST, edite o arquivo `application.properties` e defina a propriedade `server.port` para o número da porta desejado. Por padrão, o número da porta é definido como `8083`.

Para alterar a URL de conexão do PostgreSQL, edite o arquivo `application.properties` e defina a propriedade `spring.datasource.url` para a URL desejada. Por padrão, a URL é definida como `jdbc:postgresql://localhost:5432/xpto_vehicle`.

## BackLog Necessãrio

- Todas validações dos capmos das entidades estão funcionando, mas suas respectivas mensagem de erro não são informados ao usuário da execucao do microserviço.

### Testes

Uma coleção do Postman é fornecida na pasta `\postman` deste projeto, que contém um conjunto de solicitações de amostra que podem ser usadas para testar a API REST. Importe a coleção para o Postman para começar.
Algumas configurações do microserviço podem ser ajustados no `application.properties`.

## Endpoints

Os seguintes endpoints estão disponíveis nesta API REST:

### GET /vehicles

Retorna uma lista de todos os veículos no sistema.

### GET /vehicles/{id}

Retorna o veículo com o ID especificado.

### GET /vehicles?numberPlate={string}

Retorna o veículo com a placa informada

### POST /vehicles

Cria um novo veículo com os detalhes especificados.
``` json
{
    "currentDriver": "f069f7b1-cc2a-42d8-94b4-ea6f9170dfee",
    "telemetryProfile": "1ad86270-8bb5-4753-b664-79ac45e6de33",
    "customerOwner": "8e134961-c283-4fa6-ba1f-e9548c4043db",
    "color": "Black Pianno",
    "vin": "ABCEDABCEDABCEDFG",
    "numberPlate": "BJ20CE"
}
```
Validações
- Todos campos são obrigatórios;
- `currentDriver` e `customerOwner` são validados contra o microserviço People, que precisa estar online;
- `vin` tem que ter 17 caracteres;
- `numberPlate` pode ter entre 6 e 12 caracteres;
- `color` está limitado a 30 caracters;

### PUT /vehicles/{id}

Atualiza os detalhes do veículo com o ID especificado.
``` json
{
    "currentDriver": "f069f7b1-cc2a-42d8-94b4-ea6f9170dfee",
    "telemetryProfile": "1ad86270-8bb5-4753-b664-79ac45e6de33",
    "customerOwner": "8e134961-c283-4fa6-ba1f-e9548c4043db",
    "color": "Black Pianno",
    "vin": "ABCEDABCEDABCEDFG",
    "numberPlate": "BJ20CE"
}
```

### DELETE /vehicles/{id}

Exclui o veículo com o ID especificado.
