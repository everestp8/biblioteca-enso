create database if not exists BibliotecaDatabase;
use BibliotecaDatabase;

create table Usuario(
	ID_Usuario int primary key auto_increment,
	Nome varchar(100) not null,
	Email varchar(100) unique not null,
	Senha varchar(50) not null,
	CPF varchar(14) unique not null,
	Papel int not null
);

create table Livro(
	ID_Livro int primary key auto_increment,
	Titulo varchar(100) unique not null,
	Autor varchar(100) not null,
	Genero varchar(50) not null,
	Descricao varchar(200) not null,
	Ano int not null
);

create table Exemplar(
	ID_Exemplar int primary key auto_increment,
	ID_LivroFK int not null,
	ID_Fisico varchar(7) unique not null,
	Disponivel boolean not null,
	foreign key(ID_LivroFK) references Livro(ID_Livro)
);

create table Emprestimo(
	ID_Emprestimo int primary key auto_increment,
	ID_ExemplarFK int unique not null,
	ID_UsuarioFK int not null,
	DataEmprestimo date not null,
	DataDevolucao date,
	DataPrazo date not null,
	foreign key(ID_ExemplarFk) references Exemplar(ID_Exemplar),
	foreign key(ID_UsuarioFK) references Usuario(ID_Usuario)
);

create table Renovacao(
	ID_Renovacao int primary key auto_increment,
	ID_EmprestimoFK int not null,
	ID_UsuarioFK int not null,
	DataRenovacao date not null,
	foreign key(ID_EmprestimoFK) references Emprestimo(ID_Emprestimo),
	foreign key(ID_UsuarioFK) references Usuario(ID_Usuario)
);

