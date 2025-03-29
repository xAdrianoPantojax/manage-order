# Manager Order

**Manager Order** é uma aplicação desenvolvida com Spring Boot que permite o gerenciamento de pedidos. Este projeto utiliza o padrão de arquitetura de microserviços e foi projetado para fornecer APIs RESTful para a criação e consulta de pedidos.

A aplicação é integrada a um banco de dados MySQL e utiliza o padrão DTO (Data Transfer Object) para separar a camada de persistência da camada de apresentação.

## Features

- **Criação de Pedidos**: Permite a criação de novos pedidos.
- **Consulta de Pedidos**: Possui endpoints para consultar detalhes de pedidos existentes.
- **Validação de Entrada**: Usa validação automática para garantir que os dados de entrada sejam válidos.
- **Swagger UI**: Documentação da API disponível por meio de uma interface Swagger interativa.

## Tecnologias Utilizadas

- **Java 17+**: A versão do Java utilizada no projeto.
- **Spring Boot 3.x**: Framework principal para criação da API REST.
- **Spring Data JPA**: Utilizado para persistência de dados com MySQL.
- **MySQL**: Banco de dados relacional.
- **MapStruct**: Framework para mapeamento de objetos entre DTOs e entidades.
- **Swagger UI**: Para documentação e testes da API diretamente no navegador.
- **Lombok**: Para evitar boilerplate de código.
- **HikariCP**: Conector de banco de dados de alta performance.

## Pré-Requisitos

Antes de rodar o projeto, é necessário instalar as seguintes ferramentas:

### 1. Java 17+

Você pode baixar e instalar o Java 17 através do site oficial [OpenJDK](https://openjdk.java.net/).

### 2. MySQL

É necessário ter o MySQL instalado. Caso não tenha, você pode instalar o MySQL a partir do [site oficial](https://dev.mysql.com/downloads/).

### 3. Maven

O Maven é utilizado para a construção e gerenciamento de dependências. Você pode instalar o Maven a partir do [site oficial](https://maven.apache.org/).


### 1. Clonar o repositório
```bash
   git clone https://github.com/reinaldojun/manage-order.git
```
```bash
   cd manage-order
```   
   
### 2. Compilar e rodar a aplicação

```bash
   mvn clean install
   mvn spring-boot:run
```   
   A aplicação estará rodando em http://localhost:8080.

## Documentação da API
A documentação interativa da API está disponível no Swagger. Para acessar a documentação:

Inicie a aplicação.
Acesse http://localhost:8080/swagger-ui/index.html no seu navegador.

## Estrutura da aplicação

```bash
manager-order/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   └── ambev/
│   │   │   │       └── manager_order/
│   │   │   │           ├── config/
│   │   │   │           │   └── SwaggerConfig.java
│   │   │   │           ├── controller/
│   │   │   │           │   └── OrderController.java
│   │   │   │           ├── dto/
│   │   │   │           │   ├── OrderRequestDTO.java
│   │   │   │           │   ├── OrderResponseDTO.java
│   │   │   │           │   ├── OrderItemDTO.java
│   │   │   │           │   ├── OrderItemRequestDTO.java
│   │   │   │           │   └── OrderItemResponseDTO.java
│   │   │   │           ├── exception/
│   │   │   │           │   ├── ValidationExceptionHandler.java
│   │   │   │           │   ├── GlobalExceptionHandler.java
│   │   │   │           │   └── OrderAlreadyExistsException.java
│   │   │   │           ├── mapper/
│   │   │   │           │   ├── OrderMapper.java
│   │   │   │           │   ├── BaseConverter.java
│   │   │   │           │   └── ModelMapperConfig.java
│   │   │   │           ├── model/
│   │   │   │           │   ├── Order.java
│   │   │   │           │   └── OrderItem.java
│   │   │   │           ├── repository/
│   │   │   │           │   └── OrderRepository.java
│   │   │   │           └── service/
│   │   │   │               ├── OrderService.java
│   │   │   │               └── converter/
│   │   │   │                   └── OrderResponseConverter.java
│   ├── resources/
│   │   └── application.properties
│   ├── test/
│       └── java/
│           └── com/
│               └── ambev/
│                   └── manager_order/
│                       ├── controller/
│                       │   └── OrderControllerTest.java
│                       └── service/
│                           └── OrderServiceTest.java
└── pom.xml

```

## Endpoints da API:

### Criar Pedido:

```bash
POST "http://localhost:8080/orders"
```

### RequestBody:
```bash
{
    "customer": "João Pereira",
    "totalAmount": 4850.75,
    "status": "CONFIRMADO",
    "items": [
        {
            "product": "Smartphone Samsung Galaxy S23",
            "price": 4200.00,
            "quantity": 1
        },
        {
            "product": "Carregador Turbo Samsung",
            "price": 250.75,
            "quantity": 1
        },
        {
            "product": "Fone de Ouvido Bluetooth JBL",
            "quantity": 1,
            "price": 400.0
        }
    ]
}
```

### Response:
```bash
{
    "orderId": 8,
    "customer": "João Pereira",
    "totalAmount": 4850.75,
    "status": "Processed",
    "items": [
        {
            "product": "Smartphone Samsung Galaxy S23",
            "quantity": 1,
            "price": 4200.0
        },
        {
            "product": "Carregador Turbo Samsung",
            "quantity": 1,
            "price": 250.75
        },
        {
            "product": "Fone de Ouvido Bluetooth JBL",
            "quantity": 1,
            "price": 400.0
        }
    ]
}
```

### Consultar Pedido por ID:

```bash
GET "http://localhost:8080/orders/{id}"
```

#### Substitua {id} pelo ID do vendedor desejado.


## Padrões de Design Utilizados no Projeto

### MVC (Model-View-Controller)

O padrão MVC é utilizado para organizar a estrutura da aplicação em três componentes principais:

- **Model**: Representado pelas classes de modelo como `Order`, `OrderRequestDTO` e `OrderResponseDTO`. O Model encapsula os dados e regras de negócio da aplicação.

- **View**: No contexto da aplicação RESTful, a "View" é representada indiretamente pela estrutura das respostas JSON retornadas pelos endpoints.

- **Controller**: Representado pela classe `OrderController`, responsável por receber requisições HTTP, delegar o processamento para o serviço (`Service`) adequado e retornar as respostas apropriadas.

### DTO (Data Transfer Object)

Os DTOs são utilizados para transferência de dados entre camadas da aplicação e para representação dos dados recebidos ou enviados pela API. Exemplos incluem `OrderRequestDTO` e `OrderResponseDTO`, que ajudam a separar a representação interna dos dados (`Model`) da interface externa/API (`Controller`).

### Repository Pattern

O padrão Repository é implementado na camada de acesso a dados (`Repository`), como `VendedorRepository`. Ele abstrai e encapsula o acesso aos dados, proporcionando métodos para operações de persistência sem expor detalhes de implementação específicos.

### Dependency Injection (DI)

O Spring Framework utiliza extensivamente o princípio de Injeção de Dependência para gerenciar as dependências entre os componentes da aplicação. Isso é evidente nas anotações `@Autowired` nos serviços (`Service`) e conversores (`Converter`), como `OrderService`.

### Strategy Pattern (ModelMapper)

Embora não seja explicitamente implementado como tal, o uso do ModelMapper dentro da interface `BaseConverter` pode ser considerado uma implementação simplificada do padrão Strategy. O ModelMapper é configurado globalmente e utilizado para converter entre entidades e DTOs de forma padronizada.

### Builder Pattern (SwaggerConfig)

A configuração do Swagger (`SwaggerConfig`) pode ser vista como uma implementação do padrão Builder. As classes `OpenAPI` e `GroupedOpenApi` são construídas de forma fluente e configuradas com várias opções usando métodos encadeados (`builder pattern`).

---

Esses padrões de design ajudam a estruturar o projeto de forma organizada e modular, promovendo a reutilização de código, manutenção facilitada e uma separação clara de responsabilidades entre os componentes da aplicação.
