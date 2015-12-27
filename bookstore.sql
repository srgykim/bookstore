--DROP TABLE BOOKAUTHORS;
--DROP TABLE BOOKCATEGORIES;
--DROP TABLE ORDERITEMS;
--DROP TABLE ORDERS;
--DROP TABLE CUSTOMERS;
--DROP TABLE AUTHORS;
--DROP TABLE CATEGORIES;
--DROP TABLE BOOKS;
--DROP SEQUENCE order_numbers;

CREATE TABLE AUTHORS (
	author_id VARCHAR PRIMARY KEY,
	first_name VARCHAR,
	last_name VARCHAR
);

INSERT INTO AUTHORS VALUES ('1', 'Jamie', 'Oliver');
INSERT INTO AUTHORS VALUES ('2', 'Nigella', 'Lawson');
INSERT INTO AUTHORS VALUES ('3', 'Anthony', 'Doerr');
INSERT INTO AUTHORS VALUES ('4', 'Paula', 'Hawkins');
INSERT INTO AUTHORS VALUES ('5', 'Nicholas', 'Sparks');
INSERT INTO AUTHORS VALUES ('6', 'Victoria', 'Finlay');
INSERT INTO AUTHORS VALUES ('7', 'June Jamrich', 'Parsons');
INSERT INTO AUTHORS VALUES ('8', 'Dan', 'Oja');


CREATE TABLE BOOKS (
	isbn VARCHAR PRIMARY KEY,
	title VARCHAR,
	description VARCHAR,
	publisher VARCHAR,
	publication_date DATE,
	book_type VARCHAR,
	price FLOAT
);

INSERT INTO BOOKS VALUES ('9780062305640', 'Everyday Super Food', 'This is the most personal book I''ve ever written, 
and in order to write it I''ve been on a complete journey through the world of health and nutrition. 
Now, using the thing I know best—incredible food—my wish is that this book will inspire and empower 
you to live the healthiest, happiest, most productive life you can. Food is there to be enjoyed, 
shared, and celebrated, and healthy, nourishing food should be colorful, delicious, and fun. 
This book is full of well-rounded, balanced recipes that will fill you up and tickle your taste buds, 
and because I''ve done all the hard work on the nutrition front, you can be sure that every choice is a good choice. 
If you pick up just a handful of ideas from this book, it will change the way you think about food, 
arming you with the knowledge to get it right on the food front, most of the time.<br><br>
Love, Jamie xxx', 'Ecco', '20-Oct-2015', 'Hardcover', 24.55);

INSERT INTO BOOKS VALUES ('9781250073754', 'Simply Nigella: Feel Good Food', 
'<b>"Part of the balance of life lies in understanding that different days require different ways of eating . . ."</b><br><br>
Whatever the occasion, food-in the making and the eating-should always be pleasurable. 
Simply Nigella taps into the rhythms of our cooking lives with recipes that are uncomplicated and relaxed yet always satisfying.<br><br>
From quick and calm workday dinners (Miso Salmon; Cauliflower & Cashew Nut Curry) to stress-free ideas when feeding a crowd 
(Chicken Traybake with Bitter Orange & Fennel) to the instant joy of bowlfood for cozy nights on the sofa 
(Thai Noodles with Cinnamon and Shrimp), here is food guaranteed to make everyone feel good.<br><br>
Whether you need to create some breathing space at the end of a long week (Asian-Flavored Short Ribs), 
indulge in a sweet treat (Lemon Pavlova; Chocolate Chip Cookie Dough Pots), or wake up to a strength-giving 
breakfast (Toasty Olive Oil Granola), Nigella''s new cookbook is filled with recipes destined to become firm favorites.<br><br>
<i>Simply Nigella</i> is the perfect antidote to our busy lives: a calm and glad celebration of food to soothe and uplift.', 
'Flatiron Books', '3-Nov-2015', 'Hardcover', 19.64);

INSERT INTO BOOKS VALUES ('9781476746586', 'All the Light We Cannot See', '<b>WINNER OF THE PULITZER PRIZE</b><br>
From the highly acclaimed, multiple award-winning Anthony Doerr, the beautiful, stunningly ambitious instant 
New York Times bestseller about a blind French girl and a German boy whose paths collide in occupied France as both try to survive the 
devastation of World War II.<br><br>Marie-Laure lives with her father in Paris near the Museum of Natural History, 
where he works as the master of its thousands of locks. When she is six, Marie-Laure goes blind and her father builds 
a perfect miniature of their neighborhood so she can memorize it by touch and navigate her way home. When she is twelve, 
the Nazis occupy Paris and father and daughter flee to the walled citadel of Saint-Malo, where Marie-Laure''s reclusive 
great-uncle lives in a tall house by the sea. With them they carry what might be the museum''s most valuable and dangerous jewel.<br><br>
In a mining town in Germany, the orphan Werner grows up with his younger sister, enchanted by a crude radio they find. 
Werner becomes an expert at building and fixing these crucial new instruments, a talent that wins him a place at a 
brutal academy for Hitler Youth, then a special assignment to track the resistance. More and more aware of the 
human cost of his intelligence, Werner travels through the heart of the war and, finally, into Saint-Malo, where 
his story and Marie-Laure''s converge.<br><br>
Doerr''s “stunning sense of physical detail and gorgeous metaphors” <i>(San Francisco Chronicle)</i> are dazzling. 
Deftly interweaving the lives of Marie-Laure and Werner, he illuminates the ways, against all odds, people try 
to be good to one another. Ten years in the writing, a National Book Award finalist, 
<i>All the Light We Cannot See</i> is a magnificent, deeply moving novel from a writer “whose sentences never fail to thrill” 
<i>(Los Angeles Times)</i>.', 'Scribner', '6-May-2014', 'Hardcover', 15.29); 

INSERT INTO BOOKS VALUES ('9781594633669', 'The Girl on the Train', '<b>Instant #1 New York Times Bestseller<br><br>
“Nothing is more addicting than The Girl on the Train.”—Vanity Fair<br><br>
“The Girl on the Train has more fun with unreliable narration than any chiller since Gone Girl. . . . 
[It] is liable to draw a large, bedazzled readership.”—The New York Times<br><br>
“Like its train, the story blasts through the stagnation of these lives in suburban London and the 
reader cannot help but turn pages.”—The Boston Globe<br><br>
“Gone Girl fans will devour this psychological thriller.”—People<br><br>
A debut psychological thriller that will forever change the way you look at other people''s lives.</b><br><br>
Rachel takes the same commuter train every morning. Every day she rattles down the track, flashes 
past a stretch of cozy suburban homes, and stops at the signal that allows her to daily watch the same couple 
breakfasting on their deck. She''s even started to feel like she knows them. “Jess and Jason,” she calls them. 
Their life—as she sees it—is perfect. Not unlike the life she recently lost.<br><br>
And then she sees something shocking. It''s only a minute until the train moves on, but it''s enough. 
Now everything''s changed. Unable to keep it to herself, Rachel offers what she knows to the police, 
and becomes inextricably entwined in what happens next, as well as in the lives of everyone involved. 
Has she done more harm than good?<br><br>
Compulsively readable, <i>The Girl on the Train</i> is an emotionally immersive, 
Hitchcockian thriller and an electrifying debut.', 'Riverhead Books', '13-Jan-2015', 'Hardcover', 13.47);

INSERT INTO BOOKS VALUES ('9781455520619', 'See Me', '<i>See me just as I see you . . .</i><br><br>
Colin Hancock is giving his second chance his best shot. With a history of violence and bad decisions 
behind him and the threat of prison dogging his every step, he''s determined to walk a straight line. 
To Colin, that means applying himself single-mindedly toward his teaching degree and avoiding everything 
that proved destructive in his earlier life. Reminding himself daily of his hard-earned lessons, 
the last thing he is looking for is a serious relationship.<br><br>
Maria Sanchez, the hardworking daughter of Mexican immigrants, is the picture of conventional success. 
With a degree from Duke Law School and a job at a prestigious firm in Wilmington, 
she is a dark-haired beauty with a seemingly flawless professional track record. 
And yet Maria has a traumatic history of her own, one that compelled her to return to 
her hometown and left her questioning so much of what she once believed.<br><br>
A chance encounter on a rain-swept road will alter the course of both Colin and Maria''s 
lives, challenging deeply held assumptions about each other and ultimately, themselves. 
As love unexpectedly takes hold between them, they dare to envision what a future together could possibly look like . . . 
until menacing reminders of events in Maria''s past begin to surface.<br><br>
As a series of threatening incidents wreaks chaos in Maria''s life, 
Maria and Colin will be tested in increasingly terrifying ways. Will demons from their past destroy the tenuous 
relationship they''ve begun to build, or will their love protect them, even in the darkest hour?<br><br>
Rich in emotion and fueled with suspense, SEE ME reminds us that love is sometimes forged in the 
crises that threaten to shatter us . . . and that those who see us for who we truly are may not always be the ones easiest to recognize.', 
'Grand Central Publishing', '13-Oct-2015', 'Paperback', 8.68); 

INSERT INTO BOOKS VALUES ('9781606064290', 'The Brilliant History of Color in Art', 'The history of art is inseparable from the history of color. 
And what a fascinating story they tell together: one that brims with an all-star cast of characters, eye-opening details, and unexpected 
detours through the annals of human civilization and scientific discovery.<br><br>
Enter critically acclaimed writer and popular journalist Victoria Finlay, who here takes readers across the globe and over 
the centuries on an unforgettable tour through the brilliant history of color in art. Written for newcomers 
to the subject and aspiring young artists alike, Finlay’s quest to uncover the origins and science of color will 
beguile readers of all ages with its warm and conversational style. Her rich narrative is illustrated in full color 
throughout with 166 major works of art—most from the collections of the J. Paul Getty Museum.<br><br>
Readers of this book will revel in a treasure trove of fun-filled facts and anecdotes. Were it not for Cleopatra, 
for instance, purple might not have become the royal color of the Western world. Without Napoleon, the black graphite pencil 
might never have found its way into the hands of Cézanne. Without mango-eating cows, the sunsets of Turner might have lost their shimmering glow. 
And were it not for the pigment cobalt blue, the halls of museums worldwide might still be filled with forged Vermeers.<br><br>
Red ocher, green earth, Indian yellow, lead white—no pigment from the artist’s broad and diverse palette escapes 
Finlay’s shrewd eye in this breathtaking exploration.', 'J. Paul Getty Museum', '1-Nov-2014', 'Hardcover', 17.75);

INSERT INTO BOOKS VALUES ('9781285096926', 'New Perspectives on Computer Concepts 2014: Comprehensive 16th Edition', 'Go beyond computing basics with 
the award-winning NEW PERSPECTIVES ON COMPUTER CONCEPTS. Designed to get you up-to-speed on essential computer literacy skills, 
this market leading text goes deeper, providing technical and practical information relevant to everyday life. 
NEW PERSPECTIVES ON COMPUTER CONCEPTS 2014 incorporates significant technology trends that affect computing and everyday 
life; such as concerns for data security, personal privacy, online safety, controversy over digital rights management, 
interest in open source software and portable applications, and more. In addition, coverage of Microsoft Windows 8 
and Office 2013 will introduce you to the exciting new features of Microsoft''s next generation of software.', 
'Course Technology', '30-Jan-2013', 'Paperback', 148.82);

CREATE TABLE BOOKAUTHORS (
	isbn VARCHAR,
	author_id VARCHAR,
	FOREIGN KEY (isbn) REFERENCES BOOKS (isbn),
	FOREIGN KEY (author_id) REFERENCES AUTHORS (author_id),
	PRIMARY KEY (isbn, author_id)
);

INSERT INTO BOOKAUTHORS VALUES ('9780062305640', '1');
INSERT INTO BOOKAUTHORS VALUES ('9781250073754', '2');
INSERT INTO BOOKAUTHORS VALUES ('9781476746586', '3');
INSERT INTO BOOKAUTHORS VALUES ('9781594633669', '4');
INSERT INTO BOOKAUTHORS VALUES ('9781455520619', '5');
INSERT INTO BOOKAUTHORS VALUES ('9781606064290', '6');
INSERT INTO BOOKAUTHORS VALUES ('9781285096926', '7');
INSERT INTO BOOKAUTHORS VALUES ('9781285096926', '8');



CREATE TABLE CATEGORIES (
	category_name VARCHAR PRIMARY KEY
);

INSERT INTO CATEGORIES VALUES ('Cooking');
INSERT INTO CATEGORIES VALUES ('Fiction');
INSERT INTO CATEGORIES VALUES ('Arts');
INSERT INTO CATEGORIES VALUES ('Computer Science');
INSERT INTO CATEGORIES VALUES ('Textbooks');

CREATE TABLE BOOKCATEGORIES (
	isbn VARCHAR,
	category_name VARCHAR,
	FOREIGN KEY (isbn) REFERENCES BOOKS (isbn),
	FOREIGN KEY (category_name) REFERENCES CATEGORIES (category_name),
	PRIMARY KEY (isbn, category_name)
);

INSERT INTO BOOKCATEGORIES VALUES ('9780062305640', 'Cooking');
INSERT INTO BOOKCATEGORIES VALUES ('9781250073754', 'Cooking');
INSERT INTO BOOKCATEGORIES VALUES ('9781476746586', 'Fiction');
INSERT INTO BOOKCATEGORIES VALUES ('9781594633669', 'Fiction');
INSERT INTO BOOKCATEGORIES VALUES ('9781455520619', 'Fiction');
INSERT INTO BOOKCATEGORIES VALUES ('9781606064290', 'Arts');
INSERT INTO BOOKCATEGORIES VALUES ('9781285096926', 'Computer Science');
INSERT INTO BOOKCATEGORIES VALUES ('9781285096926', 'Textbooks');

CREATE TABLE CUSTOMERS (
	email VARCHAR PRIMARY KEY,
	password VARCHAR 
);

CREATE TABLE ORDERS (
	order_id BIGINT PRIMARY KEY,
	email VARCHAR,
	FOREIGN KEY (email) REFERENCES CUSTOMERS (email),
	first_name VARCHAR,
	last_name VARCHAR,
	address VARCHAR,
	phone VARCHAR,
	delivery_method VARCHAR,
	order_date DATE,
	order_cost FLOAT
);

CREATE TABLE ORDERITEMS (
	order_id BIGINT,
	isbn VARCHAR,
	quantity INT,
	FOREIGN KEY (order_id) REFERENCES ORDERS (order_id),
	FOREIGN KEY (isbn) REFERENCES BOOKS (isbn),
	PRIMARY KEY (order_id, isbn)
);

CREATE SEQUENCE order_numbers START 1;
