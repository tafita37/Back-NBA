CREATE DATABASE nba;
\c nba;
CREATE TABLE equipe(
    idequipe serial PRIMARY KEY,
    equipe varchar(125)
);

CREATE TABLE joueur(
    idjoueur serial PRIMARY KEY,
    joueur varchar(125),
    idequipe int,
    FOREIGN KEY (idequipe) references equipe(idequipe)
);


CREATE TABLE match(
    idmatch serial PRIMARY KEY,
    match varchar(125),
    idequipe1 int,
    idequipe2 int,
    foreign key (idEquipe1) references equipe(idEquipe),
    foreign key (idEquipe2) references equipe(idEquipe)
);

CREATE TABLE point(
    idpoint serial PRIMARY KEY,
    idjoueur int,
    points int,
    idmatch int,
    foreign key (idjoueur) references joueur(idjoueur),
    foreign key (idmatch) references match(idmatch)
);

CREATE TABLE type_passe(
    idtypepasse serial PRIMARY KEY,
    type varchar(125)
);

INSERT INTO type_passe VALUES (default,'normal');
INSERT INTO type_passe VALUES (default,'decisive');

CREATE TABLE passe(
    idpasse serial PRIMARY KEY,
    idjoueur int,
    idmatch int,
    idtypepasse int,
    foreign key (idjoueur) references joueur(idjoueur),
    foreign key (idmatch) references match(idmatch),
    foreign key (idtypepasse) references type_passe(idtypepasse)
);

CREATE TABLE rebond(
    idrebond serial PRIMARY KEY,
    idjoueur int,
    idmatch int,
    foreign key (idjoueur) references joueur(idjoueur),
    foreign key (idmatch) references match(idmatch)
);

CREATE TABLE  temps(
    idtemps serial PRIMARY KEY,
    idjoueur int,
    idmatch int,
    debut time,
    fin time,
    foreign key (idjoueur) references joueur(idjoueur),
    foreign key (idmatch) references match(idmatch)
);


-- view point --
CREATE OR REPLACE VIEW v_point AS
SELECT j.idjoueur,j.joueur,m.idmatch,p.points FROM point as p
JOIN joueur AS j ON j.idjoueur = p.idjoueur
JOIN match AS m ON m.idmatch = p.idmatch
WHERE p.idmatch = ? AND p.idjoueur = ?; 
-- view rebond --
CREATE OR REPLACE VIEW v_rebond AS
SELECT j.idjoueur,j.joueur,m.idmatch,COUNT(*) AS nbr_rebond FROM rebond as r
JOIN joueur AS j ON j.idjoueur = r.idjoueur
JOIN match AS m ON m.idmatch = r.idmatch
GROUP BY j.idjoueur,j.joueur,m.idmatch
WHERE r.idmatch = ? AND r.idjoueur = ?;
-- view passe --
CREATE OR REPLACE VIEW v_passe AS
SELECT j.idjoueur,j.joueur,m.idmatch,p.idtypepasse FROM passe as p
JOIN joueur AS j ON j.idjoueur = p.idjoueur
JOIN match AS m ON m.idmatch = p.idmatch
WHERE p.idmatch = ? AND p.idjoueur = ?;
-- view all --
CREATE OR REPLACE VIEW v_statistique AS
SELECT points.idjoueur,rebond.joueur,passe.idmatch,passe.idtypepasse,rebond.nbr_rebond,SUM(points.points) AS nbr_point FROM v_point as points
JOIN v_passe AS passe ON points.idjoueur = passe.idjoueur
JOIN v_rebond AS rebond ON points.idjoueur = passe.idjoueur
GROUP BY points.idjoueur,rebond.joueur,passe.idmatch,passe.idtypepasse,rebond.nbr_rebond,points.points
ORDER BY rebond.nbr_rebond,points.points ASC;

