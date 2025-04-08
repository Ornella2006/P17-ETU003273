drop database exam;
create database exam;
use exam

create table prevision (
    id_prevision int auto_increment primary key,
    libeller varchar(100),
    montant int
);

INSERT INTO prevision (libeller, montant) VALUES 
("Frais de bus", 120000),
("Repas", 180000);


create table depense (
    id_depense int auto_increment primary key,
    id_prevision int,
    montant int,
    date_crea DATETIME DEFAULT CURRENT_TIMESTAMP,
    foreign key (id_prevision) references prevision(id_prevision)
);

INSERT INTO depense (id_prevision, montant) VALUES 
(1, 50000),
(2, 40000);


/* x2PABuUG */

