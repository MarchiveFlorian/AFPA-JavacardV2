CREATE TABLE contact (
	id 			   SERIAL primary KEY,
    first_name     VARCHAR(100) NOT NULL,
    last_name      VARCHAR(200) NOT NULL,
    gender         VARCHAR(10) NOT NULL,
    birth_date     DATE,
	pseudo         VARCHAR(200) NOT NULL,
	"address"      VARCHAR(250) NOT NULL,
	personal_phone_number      VARCHAR(50) NOT NULL,
	professional_phone_number  VARCHAR(50) NOT NULL,
	email          VARCHAR(250) NOT NULL,
	linkedin_ink   VARCHAR(250) NOT NULL,
	git_link        VARCHAR(250) NOT NULL
);

INSERT INTO contact (first_name, last_name, gender, birth_date, pseudo, "address", personal_phone_number, professional_phone_number, email, linkedin_ink, git_link) VALUES ('Chloe', 'Boivin', 'Femme', '1995-07-19', 'bulo', 'Merignac', '06','7', 'chloe.boivin@outlook.com', 'chloe-boivin', 'bu-lo');
INSERT INTO contact (first_name, last_name, gender, birth_date, pseudo, "address", personal_phone_number, professional_phone_number, email, linkedin_ink, git_link) VALUES ('Lucas', 'Clavières', 'Homme', '1995-06-22', 'lumyn', 'Merignac', '06', '07', 'lucas.clavieres@gmail.com', 'lucas-clavieres', 'lumyn');