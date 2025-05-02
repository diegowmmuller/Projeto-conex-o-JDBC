# Sistema de Gerenciamento de Vendedores e Departamentos

Este é um sistema de gerenciamento de vendedores e departamentos implementado utilizando Java e JDBC. O sistema permite realizar operações CRUD (Create, Read, Update, Delete) para as entidades `Seller` (Vendedor) e `Department` (Departamento), com a interação direta com o banco de dados via JDBC.

## Funcionalidades

- **Vendedores (Sellers)**:
  - Adicionar, atualizar, excluir e consultar vendedores.
  - Consultar vendedores por ID ou departamento.
  
- **Departamentos (Departments)**:
  - Adicionar, atualizar, excluir e consultar departamentos.
  
## Tecnologias Utilizadas

- **Java**: Linguagem principal para o desenvolvimento.
- **JDBC**: Para interação com o banco de dados MySQL.
- **MySQL**: Sistema de gerenciamento de banco de dados utilizado.

## Estrutura do Projeto

O projeto é dividido em várias classes e pacotes:

### Pacote `model.dao`

Contém as interfaces e implementações dos DAOs (Data Access Objects) que realizam a persistência de dados.

- **`DepartmentDao`**: Interface para as operações de persistência relacionadas ao departamento.
- **`SellerDao`**: Interface para as operações de persistência relacionadas ao vendedor.
- **`DepartmentDaoJDBC`**: Implementação JDBC da interface `DepartmentDao`.
- **`SellerDaoJDBC`**: Implementação JDBC da interface `SellerDao`.

### Pacote `model.entities`

Contém as classes de modelo para as entidades do sistema.

- **`Department`**: Classe que representa um departamento.
- **`Seller`**: Classe que representa um vendedor.

### Pacote `application`

Contém a classe principal que executa o programa e testa as funcionalidades do sistema.

- **`Program`**: Classe principal, onde são executados os testes de CRUD.

## Como Rodar o Projeto

1. **Pré-requisitos**:
   - Java 11 ou superior.
   - MySQL ou outro banco de dados relacional configurado.
   
2. **Configuração do Banco de Dados**:
   - Crie um banco de dados no MySQL chamado `seller_system` (ou o nome que preferir).
   - Configure as tabelas para `Department` e `Seller` conforme os seguintes esquemas:

```sql
CREATE TABLE department (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL
);

CREATE TABLE seller (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Email VARCHAR(255) NOT NULL,
  BirthDate DATE NOT NULL,
  BaseSalary DOUBLE NOT NULL,
  DepartmentId INT,
  FOREIGN KEY (DepartmentId) REFERENCES department(Id)
);
