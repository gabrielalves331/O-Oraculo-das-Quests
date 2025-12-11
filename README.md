Oráculo das Quests

Sistema de gerenciamento de missões utilizando Java, Swing, JDBC e MySQL.

O Oráculo das Quests é um sistema desktop para gerenciar missões (quests).
Ele permite cadastrar, visualizar, editar e remover missões utilizando uma interface gráfica construída em Java Swing, 
com persistência de dados em MySQL e acesso via JDBC.

O projeto foi desenvolvido seguindo o padrão MVC e boas práticas de separação de camadas.

--> Funcionalidades <--

* Cadastrar nova missão

* Listar missões na JTable

* Editar missões existentes

* Excluir missões

* Visualização completa da descrição com duplo clique

* Atualização dinâmica da tabela

--> Atributos disponíveis: <--

Título

Descrição

Dificuldade

Recompensa

Status (concluída ou não)

--> Arquitetura do Projeto (MVC)  <--
Modelo (Model)

Representa as entidades e regras de negócio.
Ex: Missao.java

Visão (View)

Interface gráfica feita em Swing (JFrames e Forms).
Ex: QuadroQuests.java, InsercaoMissao.java, etc.

Controle & DAO

Acesso ao banco via JDBC através de classes DAO.
Ex: MissaoDAOJDBC.java

TableModel

Realiza a ponte entre a GUI e os objetos Java.
Ex: MissaoTableModel.java

🛠️ Tecnologias Utilizadas

Java (JDK 17+)

Swing (interface gráfica)

MySQL

JDBC

Maven

NetBeans (IDE usada no desenvolvimento)

--> Banco de Dados <--
Script SQL utilizado:
CREATE DATABASE `oraculo_das_quests`;

CREATE TABLE `missoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `descricao` text,
  `dificuldade` varchar(50) DEFAULT NULL,
  `recompensa` varchar(255) DEFAULT NULL,
  `concluida` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--> DER (Diagrama Entidade-Relacionamento) <--

O banco possui apenas uma entidade:

+===============================+
|MISSOES                        |
|-------------------------------|
|PK id : int                    |
|titulo : varchar(255)          |
|descricao : text               |
|dificuldade : varchar(50)      |
|recompensa : varchar(255)      |
|concluida : tinyint(1)         |
+===============================+

--> Como Executar <--
1. Clone o repositório
git clone https://github.com/gabrielalves331/O-Oraculo-das-Quests.git

2. Configure o Banco de Dados

Importe o script SQL acima no MySQL

Configure o usuário/senha e URL no DAO (ex.: jdbc:mysql://localhost/oraculo_das_quests)

3. Execute o projeto

Abra no NetBeans (ou outra IDE)

Execute o arquivo principal (ex.: PaginaInicial.java)

A interface gráfica será aberta 💻

--> Estrutura de Pastas <--
src/
 ├── dao/          # Acesso ao banco de dados (DAO + JDBC)
 ├── modelo/       # Classes de modelo
 └── paginas/      # Interface gráfica (Swing)

pom.xml            # Configuração Maven
README.md          # Este arquivo

--> Melhorias Futuras <--

Filtros por dificuldade / título / status

Adicionar usuários com login

Logs de histórico

Versão Web (Spring Boot)

Categorias de missões

Autor

Gabriel Alves
gabrielalves33147@gmail.com

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos.

--> Licença <--

Este projeto é livre para fins acadêmicos e pessoais.
