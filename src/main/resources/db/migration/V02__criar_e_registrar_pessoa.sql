CREATE TABLE pessoa
(
	codigo numeric(18) IDENTITY(1,1) primary key,
	nome varchar(50) not null,
	ativo bit not null,
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(50),
	cep varchar(10),
	cidade varchar(50),
	estado varchar(2)
);


insert into pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
values ('Jonatan',1,'Thomas Pereira','391','Casa','Bairro','95880000','Estrela','RS');

insert into pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
values ('Felipe',1,'Benjamin Constant','001','ap301','Centro','95900000','Lajeado','RS');



