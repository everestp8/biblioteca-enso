create database if not exists BibliotecaDatabase;
use BibliotecaDatabase;

create table Usuario(
	Id int primary key auto_increment,
	Nome varchar(100) not null,
	Email varchar(100) unique not null,
	Senha varchar(50) not null,
	CPF varchar(14) unique not null,
	Papel int not null
);

create table Livro(
	Id int primary key auto_increment,
	Titulo varchar(100) unique not null,
	Autor varchar(100) not null,
	Genero varchar(50) not null,
	Descricao varchar(200) not null,
	Ano int not null
);

create table Exemplar(
	Id int primary key auto_increment,
	LivroFK int not null,
	IdFisico varchar(7) unique not null,
	Disponivel boolean not null,
	foreign key(LivroFK) references Livro(Id)
);

create table Emprestimo(
	Id int primary key auto_increment,
	ExemplarFK int not null,
	UsuarioFK int not null,
	DtEmprestimo date not null,
	DtDevolucao date,
	DtPrazo date not null,
	foreign key(ExemplarFk) references Exemplar(Id),
	foreign key(UsuarioFK) references Usuario(Id)
);

create table Renovacao(
	Id int primary key auto_increment,
	EmprestimoFK int not null,
	UsuarioFK int not null,
	DtRenovacao date not null,
	foreign key(EmprestimoFK) references Emprestimo(Id),
	foreign key(UsuarioFK) references Usuario(Id)
);

