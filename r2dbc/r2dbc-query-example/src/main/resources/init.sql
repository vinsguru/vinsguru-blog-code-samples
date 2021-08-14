create table customer (
	id INT auto_increment,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(50),
	country VARCHAR(50),
	is_active Boolean
);

insert into customer (first_name, last_name, email, country, is_active) values
     ('Tara','Wheeliker','twheeliker0@storify.com','Brazil','false'),
     ('Daisy','Klulisek','dklulisek1@constantcontact.com','Peru','true'),
     ('Fredericka','Marusik','fmarusik2@google.ca','China','true'),
     (NULL,'Pinckstone','mpinckstone3@prlog.org','Philippines','true'),
     ('Dela','Dacks','ddacks4@hao123.com','China','false'),
     ('Brita','Doughton','bdoughton5@tmall.com','China','false'),
     ('Jamima','Sorsby','jsorsby6@goo.ne.jp','Poland','true'),
     ('Vincenty','Ianinotti','vianinotti7@freewebs.com','Israel','false'),
     ('Karin','Sollas','ksollas8@cdc.gov','Sweden','false'),
     ('Foss','Bifield','fbifield9@statcounter.com','Philippines','false');