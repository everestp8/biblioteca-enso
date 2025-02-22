# Sistema de Biblioteca

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Mysql](https://img.shields.io/badge/mysql-%23316192.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Sobre o Projeto
Este projeto é um **Sistema de Biblioteca** desenvolvido em **Java** com interface gráfica feita em **Swing**, utilizando um banco de dados **MySQL** e seguindo a arquitetura **MVC** (Model-View-Controller). 

O sistema foi desenvolvido como projeto final das disciplinas de **Programação Orientada a Objetos, Banco de Dados e Engenharia de Software**, no **Instituto Federal de Alagoas (IFAL)**, sob orientação dos professores **Cledja Rolim, Douglas Menezes e Italo Carlo**.

## Equipe de Desenvolvimento
- **Bárbara Gleyce**
- **Charles Evaristo**
- **Erick Davi**

## Funcionalidades
O sistema possui três tipos de usuários, cada um com diferentes níveis em suas permissões:

### 📌 Cliente
- Visualização de livros disponíveis
- Consulta e renovação de empréstimos
- Alteração de dados da conta

### 📌 Bibliotecário
- Adição e remoção de livros
- Gerenciamento de exemplares
- Controle de empréstimos (criação e devolução)

### 📌 Administrador
- Gerenciamento de usuários (clientes, bibliotecários e outros administradores)

## Tecnologias Utilizadas
- **Java** (Linguagem de Programação)
- **Swing** (Interface Gráfica)
- **MySQL** (Banco de Dados)
- **JDBC** (Conexão com Banco de Dados)

## Requisitos para Execução
Antes de executar o sistema, certifique-se de ter os seguintes requisitos instalados:
- **JDK 17**
- **MySQL Server**

## Configuração do Banco de Dados
1. Execute os scripts SQL para criar o banco e as tabelas necessárias (fornecidos no diretório `database/` do projeto).

`database/ddl.sql` e `database/dml.sql`

## Como Executar o Projeto
1. Clone este repositório:
   ```sh
   git clone https://github.com/everestp8/biblioteca-enso
   ```
2. Configure a conexão com o banco de dados ao criar o arquivo `.env`. Ex:
    ``` env
    JDBC_URL="jdbc:mysql://localhost:3306/BibliotecaDatabase"
    DB_USER="SEU USUÁRIO MYSQL"
    DB_PASSWORD="SUA SENHA"
    ```
3. Importe o projeto em sua IDE de preferência (**IntelliJ IDEA, NetBeans, Eclipse**).
4. Compile e execute a classe principal do sistema.

## Licença
Este projeto foi desenvolvido exclusivamente para fins acadêmicos e não possui fins comerciais.

---
📌 **Instituto Federal de Alagoas - IFAL**  
🖥️ **Projeto Final - Engenharia de Software, Banco de Dados e POO**

