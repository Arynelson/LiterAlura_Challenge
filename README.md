# LiterAlura - Catálogo de Livros Interativo

Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação. Os livros serão buscados através de uma API específica. As informações sobre a API e as opções de interação com o usuário serão detalhadas na coluna 



![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java )
![Spring](https://img.shields.io/badge/Spring_Boot-3.3-green?style=for-the-badge&logo=spring )
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=for-the-badge&logo=postgresql )
![Maven](https://img.shields.io/badge/Maven-4.0-red?style=for-the-badge&logo=apache-maven )

## 📖 Descrição do Projeto

**LiterAlura** é um catálogo de livros interativo que funciona via console (terminal). O projeto foi desenvolvido como parte do Challenge Alura e permite aos usuários buscar livros na internet através da API [Gutendex](https://gutendex.com/ ), uma API pública baseada no acervo do Projeto Gutenberg.

As principais funcionalidades incluem:
-   Buscar livros por título diretamente da API.
-   Salvar os livros e seus autores em um banco de dados local.
-   Listar todos os livros e autores já registrados.
-   Consultar autores que estavam vivos em um determinado ano.
-   Listar livros por idioma.

O projeto foi construído utilizando Java com o framework Spring Boot, fazendo uso do Spring Data JPA para a persistência de dados em um banco PostgreSQL.

## ⚙️ Funcionalidades

O menu principal da aplicação oferece as seguintes opções de interação:

1.  **Buscar livro pelo título:** Realiza uma busca na API Gutendex, salva o livro e o autor no banco de dados (se ainda não estiverem cadastrados).
2.  **Listar livros registrados:** Exibe todos os livros salvos no banco de dados.
3.  **Listar autores registrados:** Exibe todos os autores salvos no banco de dados.
4.  **Listar autores vivos em um determinado ano:** Solicita um ano e exibe os autores que estavam vivos naquele período.
5.  **Listar livros em um determinado idioma:** Solicita um código de idioma (ex: `en`, `pt`, `es`) e lista os livros correspondentes.
0.  **Sair:** Encerra a aplicação.

## 🛠️ Tecnologias Utilizadas

-   **Java 17:** Linguagem principal do projeto.
-   **Spring Boot:** Framework para criação da aplicação, gerenciamento de dependências e configuração.
-   **Spring Data JPA:** Para persistência de dados e comunicação com o banco de dados de forma simplificada.
-   **PostgreSQL:** Sistema de gerenciamento de banco de dados relacional utilizado para armazenar os dados.
-   **Maven:** Gerenciador de dependências e build do projeto.
-   **API Gutendex:** Fonte externa para a busca dos dados dos livros.
-   **Jackson:** Biblioteca para conversão de dados JSON (da API) para objetos Java.

## 🚀 Como Executar o Projeto

Para executar o projeto localmente, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/Arynelson/LiterAlura_Challenge.git
    ```

2.  **Configure o Banco de Dados:**
    -   Tenha o PostgreSQL instalado e rodando na sua máquina.
    -   Crie um banco de dados com o nome de sua preferência (ex: `literalura_db` ).
    -   Abra o arquivo `src/main/resources/application.properties`.
    -   Altere as seguintes propriedades com suas credenciais:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/seu-banco-de-dados
        spring.datasource.username=seu-usuario-postgres
        spring.datasource.password=sua-senha-postgres
        ```

3.  **Execute a Aplicação:**
    -   Abra o projeto em sua IDE de preferência (IntelliJ, Eclipse, VS Code).
    -   Aguarde o Maven baixar todas as dependências.
    -   Execute a classe principal `com.alura.literalura.LiteraluraApplication`.
    -   A aplicação irá iniciar e o menu interativo aparecerá no console.

## 👨‍💻 Autor

-   **Arynelson** - [Arynelson](https://github.com/Arynelson )
