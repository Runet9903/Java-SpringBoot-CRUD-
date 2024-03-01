INSERT INTO ARTICLE(TITLE, CONTENT) VALUES('AAAA', '1111');
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES('BBBB', '2222');
INSERT INTO ARTICLE(TITLE, CONTENT) VALUES('CCCC', '3333');


INSERT INTO article(title, content) VALUES('Movie', 'Comment1');
INSERT INTO article(title, content) VALUES('Sport', 'Comment2');
INSERT INTO article(title, content) VALUES('Food', 'Comment3');

INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Name1', 'Movie1');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Name2', 'Movie2');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Name3', 'Movie3');

INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Name1', 'Soccer');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Name2', 'Tennis');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Name3', 'Golf');

INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Name1', 'Sushi');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Name2', 'Pizza');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Name3', 'Udon');
