CREATE TABLE pessoa
(
	codigo bigint(20) primary key auto_increment,
	nome varchar(50) not null,
	ativo boolean not null,
	logradouro varchar(50),
	numero varchar(5),
	complemento varchar(50),
	bairro varchar(50),
	cep varchar(10),
	cidade varchar(50),
	estado varchar(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
values ('Jonatan',True,'Thomas Pereira','391','Casa','Bairro','95880000','Estrela','RS');

insert into pessoa(nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) 
values ('Felipe',True,'Benjamin Constant','001','ap301','Centro','95900000','Lajeado','RS');



