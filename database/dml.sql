use BibliotecaDatabase;

insert into Usuario(nome, email, senha, cpf, papel) values
	('Bárbara', 'bgss@', '123321', '132.132.132-13', 0),
	('Erick', 'edas@', '123321', '321.321.321-32', 0),
	('Evaristo', 'ceps@', '123321', '123.123.123-12', 0);

insert into Livro(titulo, autor, genero, descricao, ano) values
	('1984', 'George Orwell', 'Ficção Distópica', 'Coisas acontecem', 1949),
	('A Metamorfose', 'Franz Kafka', 'Ficção', 'Inseto', 1915),
	('O Hobbit', 'J. R. R. Tolkien', 'Aventura', 'Condado', 1937);

insert into Exemplar(livroFk, idFIsico, disponivel) values
	(1, 'ShREk1', true),
	(1, 'ShErlk', true),
	(2, 'CafBab', true),
	(3, 'DeADm3', true);

