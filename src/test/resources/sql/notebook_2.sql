INSERT INTO notebooks (name, description)
VALUES ('Essential English Words 2', 'Essential English Words Part 2');

INSERT INTO words (word, pronunciation, definition, notebook_id)
VALUES ('taste', 'teɪst', 'A taste is the flavor something makes in your mouth.', 2);
INSERT INTO examples (example_text, word_id)
VALUES ('The taste of the fruit was sweet.', 6);
INSERT INTO examples (example_text, word_id)
VALUES ('Birds do not have a highly developed sense of taste', 6);

INSERT INTO words (word, pronunciation, definition, notebook_id)
VALUES ('symbol', 'ˈsɪmbl', 'A symbol is a thing that stands for something else.', 2);
INSERT INTO examples (example_text, word_id)
VALUES ('This symbol tells us that we cannot smoke in this area.', 7);

INSERT INTO words (word, pronunciation, definition, notebook_id)
VALUES ('scrape', 'skreɪp', 'To scrape something is to rub it very hard with something sharp.', 2);
INSERT INTO examples (example_text, word_id)
VALUES ('I accidentally scraped the paint of the side of the car.', 8);