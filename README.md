
# Sorvil - Sistema de Gerenciamento de Livraria 📚

Bem-vindo(a) ao repositório do projeto Sorvil! 🎉

Este espaço é dedicado ao sistema de gerenciamento de livraria desenvolvido como parte da disciplina de Programação Orientada a Objetos (POO). O Sorvil é uma aplicação que visa otimizar a gestão de livros, clientes, funcionários e vendas em uma livraria.

Atualmente, este projeto foi desenvolvido por:

## 🎓 Equipe

* Professor: Italo Augusto de Souza Assis

* Alunos:

  * Allan Gabriel Silva de Freitas

  * Leonardo Augusto Silva de Sousa

  * Mauricio Paulino Flores Júnior

---

## 🛠️ Tecnologias Utilizadas

* Linguagem de Programação: Java

* Padrão de Projeto: DAO (Data Access Object)

* Banco de Dados: MySQL

* Ferramenta de Banco de Dados: MySQL Workbench (para gerenciar o banco de dados local)

---

## 🗄️ Estrutura do Banco de Dados

O banco de dados `livraria_db` é utilizado para armazenar todas as informações do sistema. Abaixo, você encontra o script SQL para a criação das tabelas:

```sql
    CREATE DATABASE IF NOT EXISTS livraria_db;
    USE livraria_db;

    CREATE TABLE IF NOT EXISTS cliente(
        id_cliente INT AUTO_INCREMENT PRIMARY KEY,
        nome_cliente VARCHAR(100) NOT NULL,
        cpf_cliente VARCHAR(11) NOT NULL UNIQUE
    );

    CREATE TABLE IF NOT EXISTS funcionario(
        id_funcionario INT PRIMARY KEY AUTO_INCREMENT,
        nome_funcionario VARCHAR(100) NOT NULL,
        email_funcionario VARCHAR(100),
        admin_funcionario BOOLEAN,
        senha_funcionario VARCHAR(50) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS livro(
        isbn_livro INT PRIMARY KEY UNIQUE,
        titulo_livro VARCHAR(150),
        autor_livro VARCHAR(150),
        edicao_livro VARCHAR(150),
        genero_livro VARCHAR(150),
        estoque_livro INT,
        preco_livro FLOAT
    );

    CREATE TABLE IF NOT EXISTS venda(
        id_venda INT PRIMARY KEY AUTO_INCREMENT,
        data_venda DATE,
        id_cliente INT,
        id_funcionario INT,
        FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
        FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
    );

    CREATE TABLE IF NOT EXISTS item_venda(
        id_item INT PRIMARY KEY AUTO_INCREMENT,
        quantidade_item INT,
        id_venda INT,
        isbn_livro INT,
        FOREIGN KEY (id_venda) REFERENCES venda(id_venda),
        FOREIGN KEY (isbn_livro) REFERENCES livro(isbn_livro)
    );
````

-----

## 📂 Estrutura do Projeto

A estrutura de diretórios do projeto é organizada da seguinte forma:

```
.
├── lib
│   └── mysql-connector-j-9.3.0.jar
├── README.md
└── src
    ├── App.java
    ├── conection
    │   └── Conection.java
    ├── DAO
    │   ├── BookDAO.java
    │   ├── ClientDAO.java
    │   ├── EmployeeDAO.java
    │   ├── SaleDAO.java
    │   └── SaleItemDAO.java
    ├── entity
    │   ├── Book.java
    │   ├── Client.java
    │   ├── Employee.java
    │   ├── SaleItem.java
    │   └── Sale.java
    └── services
        ├── BookService.java
        ├── ClientService.java
        ├── EmployeeService.java
        ├── SaleItemService.java
        └── SaleService.java
```

-----

## 🚀 Como Baixar e Usar

Siga estes passos para configurar e executar o projeto Sorvil:

1.  Pré-requisitos:

      * Certifique-se de ter o Java Development Kit (JDK) instalado (versão 8 ou superior).
      * Instale o MySQL Server e o MySQL Workbench em sua máquina local.
      * Tenha um ambiente de desenvolvimento Java (IDE) como IntelliJ IDEA, Eclipse ou NetBeans.

2.  Configuração do Banco de Dados:

      * Abra o MySQL Workbench.
      * Crie um novo esquema (schema) chamado `livraria_db`.
      * Execute o script SQL fornecido acima para criar as tabelas necessárias.

3.  Clone o repositório:
    Abra seu terminal ou prompt de comando e execute o comando:

    ```bash
    git clone [https://github.com/SEU_USUARIO/NOME_DO_SEU_REPOSITORIO.git](https://github.com/Allan-Gabriell/sorvil.git)
    ```

      * ⚠️ Importante: Substitua `SEU_USUARIO` pelo seu nome de usuário do GitHub e `NOME_DO_SEU_REPOSITORIO` pelo nome que você deu a este repositório.

4.  Adicionar o Driver JDBC:

      * Crie uma pasta chamada `lib` na raiz do projeto.
      * Baixe o driver JDBC para MySQL (`mysql-connector-j-9.3.0.jar`) e coloque-o dentro da pasta `lib`. Você pode encontrar o driver no site oficial do MySQL Connector/J.

5.  Importe o projeto na IDE:

      * Abra sua IDE (IntelliJ IDEA, Eclipse, NetBeans).
      * Importe o projeto clonado como um projeto Java existente (Maven ou Gradle, dependendo da estrutura do projeto).
      * Certifique-se de adicionar o JAR do driver JDBC (`mysql-connector-j-9.3.0.jar`) ao `Classpath` do seu projeto na IDE para que a aplicação possa se conectar ao banco de dados.

6.  Configurações de Conexão com o Banco de Dados:

      * Verifique e, se necessário, ajuste as configurações de conexão com o banco de dados no código-fonte (geralmente em um arquivo de configuração ou classe de utilitário de banco de dados) para que correspondam às suas credenciais locais do MySQL.

7.  Execute a Aplicação:

      * Compile o projeto na sua IDE.
      * Execute a classe principal da aplicação para iniciar o sistema Sorvil.

-----

## ✨ Finalidade

O projeto Sorvil demonstra a aplicação de conceitos de Programação Orientada a Objetos e o padrão de projeto DAO para criar um sistema robusto e modular de gerenciamento de dados. Ele serve como um exemplo prático de como integrar uma aplicação Java com um banco de dados relacional (MySQL) para persistência de dados.
