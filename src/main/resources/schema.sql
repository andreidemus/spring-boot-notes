DROP SEQUENCE IF EXISTS seq_notes;
DROP TABLE IF EXISTS notes;

CREATE SEQUENCE seq_notes START WITH 1 INCREMENT BY 1;

CREATE TABLE notes (
    id              BIGINT      DEFAULT NEXTVAL('seq_notes') NOT NULL,
    creation_date   TIMESTAMP   NOT NULL DEFAULT NOW(),
    author          VARCHAR     NOT NULL,
    title           VARCHAR     NOT NULL,
    text            VARCHAR     NOT NULL,
    CONSTRAINT notes_pk PRIMARY KEY (id)
);

INSERT INTO notes (author, title, text) VALUES ('Jamie', 'Chillwave', 'Flannel pok pok bicycle rights ethical, single-origin coffee swag chartreuse retro edison bulb. Iceland PBR&B pitchfork umami ethical. Vice Flannel poke jianbing, air plant health goth crucifix photo booth chillwave asymmetrical raclette bitters hot chicken iPhone. Enamel pin godard meggings, portland cardigan semiotics food truck ugh mlkshk. Drinking vinegar woke mixtape, copper mug hashtag church-key vexillologist iPhone pour-over tote bag forage schlitz blog selfies biodiesel. Meggings occupy listicle portland man braid PBR&B. Photo booth squid VHS quinoa, hammock wolf mumblecore hoodie ugh food truck bitters microdosing cornhole seitan fap.');
INSERT INTO notes (author, title, text) VALUES ('Hans Ulrich Obrist', 'Aesthetic kinfolk kitsch', 'Literally VHS ugh craft beer, affogato skateboard four dollar toast chartreuse. Aesthetic kinfolk kitsch try-hard, iceland enamel pin fap celiac normcore dreamcatcher locavore keffiyeh marfa chicharrones blog. Woke locavore biodiesel, selfies offal af vinyl occupy tbh post-ironic blog pok pok polaroid salvia. Flannel selfies humblebrag, tofu pug typewriter brunch fingerstache yuccie. Biodiesel authentic mustache selvage, ennui wolf vaporware retro glossier kogi cray. Marfa man bun succulents cray. Skateboard gluten-free cred, church-key hammock heirloom flannel readymade.');
INSERT INTO notes (author, title, text) VALUES ('Monique', 'Street art vape twee', 'Listicle live-edge celiac, cornhole tofu jianbing swag sustainable kale chips brooklyn pickled blue bottle keytar neutra +1.');
