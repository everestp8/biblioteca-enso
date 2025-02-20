use BibliotecaDatabase;

INSERT INTO Usuario(nome, email, senha, cpf, papel) VALUES
	('Bárbara', 'bgss@', '123321', '111.111.111-11', 0),
	('Erick', 'edas@', '123321', '222.222.222-22', 0),
	('Evaristo', 'ceps@', '123321', '333.333.333-33', 0);

INSERT INTO Livro(titulo, autor, genero, descricao, ano) VALUES
    ('Dom Quixote', 'Miguel de Cervantes', 'Romance', 'Aventuras de um cavaleiro sonhador', 1605),
    ('Crime e Castigo', 'Fiódor Dostoiévski', 'Ficção Psicológica', 'O dilema moral de Raskólnikov', 1866),
    ('Orgulho e Preconceito', 'Jane Austen', 'Romance', 'Elizabeth Bennet e Sr. Darcy', 1813),
    ('A Revolução dos Bichos', 'George Orwell', 'Sátira', 'Animais se rebelam contra os humanos', 1945),
    ('O Senhor dos Anéis', 'J. R. R. Tolkien', 'Fantasia', 'A jornada para destruir o Um Anel', 1954),
    ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Infantil', 'Um príncipe viaja por planetas', 1943),
    ('Moby Dick', 'Herman Melville', 'Aventura', 'Capitão Ahab persegue uma baleia branca', 1851);

INSERT INTO Exemplar(livroFk, idFisico, disponivel) VALUES
   (4, 'DQX001', true),
   (4, 'DQX002', true),
   (5, 'CRC001', true),
   (5, 'CRC002', true),
   (6, 'OEP001', true),
   (6, 'OEP002', true),
   (7, 'REB001', true),
   (7, 'REB002', true),
   (8, 'SDA001', true),
   (8, 'SDA002', true),
   (8, 'SDA003', true),
   (9, 'PPE001', true),
   (9, 'PPE002', true),
   (10, 'MBD001', true),
   (10, 'MBD002', true);
