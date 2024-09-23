CREATE TABLE categoria
(
	codigo numeric(18) IDENTITY(1,1) primary key,
	nome varchar(50) not null
);


insert into categoria (nome) values ('Lazer');
insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Supermercado');
insert into categoria (nome) values ('Farmacia');
insert into categoria (nome) values ('Outros');