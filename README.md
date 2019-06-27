foi realizado as seguintes atividade:
* Adicionar e Manter contato;
* Adicionar/Alterar/Remover: dados do contato;
* Consultar Contatos, por filtros de dados;

Utilização:
Mysql, jsf, maven, jdk;



crie uma base de dados chamada 'db_cadastro';
e a tabela.

create table contatos (
id int auto_increment,
nome varchar(30) NOT NULL,
telefone varchar(12),
primary KEY(id)
);