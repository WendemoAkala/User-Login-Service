DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS favorite;
DROP TABLE IF EXISTS item;

CREATE TABLE user (
     id int(11) unsigned NOT NULL AUTO_INCREMENT,
     firstName varchar(20) NOT NULL DEFAULT '',
     lastName varchar(20) NOT NULL DEFAULT '',
     email varchar(20) NOT NULL DEFAULT '',
     phone varchar(20) NOT NULL DEFAULT '',
     address varchar(20) NOT NULL DEFAULT '',
     username varchar(20) NOT NULL DEFAULT '',
     password varchar(20) NOT NULL DEFAULT '',
     active tinyint(1) NOT NULL DEFAULT '1',
     roles varchar(200) NOT NULL DEFAULT '',
     permissions varchar(200) NOT NULL DEFAULT '',
     PRIMARY KEY (id)
     );

INSERT INTO user (username, password, active, roles, permissions) VALUES
    ('dan', '1234', 1, '', ''),
    ('alon', '12345', 1, '', '');

CREATE TABLE orders (
     id int(11) unsigned NOT NULL AUTO_INCREMENT,
     userId varchar(1200) unsigned NOT NULL AUTO_INCREMENT,
     item varchar(1200) NOT NULL AUTO_INCREMENT,
     orderDate TIMESTAMP (23,10),
     shippingAddress varchar(200) NOT NULL DEFAULT '',
     totalPrice DOUBLE(200) NOT NULL DEFAULT '',
     orderStatus varchar(20) NOT NULL DEFAULT '',
     PRIMARY KEY (id),
     FOREIGN KEY (userId) REFERENCES user (id)
     );



CREATE TABLE favorite (
      id int(11) unsigned NOT NULL AUTO_INCREMENT,
      userId int(11) unsigned NOT NULL AUTO_INCREMENT,
      item varchar(1500) unsigned NOT NULL DEFAULT '',
      PRIMARY KEY (id),
      FOREIGN KEY (userId) REFERENCES user (id)
       );


CREATE TABLE item (
      id int(11) unsigned NOT NULL AUTO_INCREMENT,
      title varchar(200) NOT NULL DEFAULT '',
      photoUrl varchar(200) NOT NULL DEFAULT '',
      price DOUBLE(20) NOT NULL DEFAULT '',
      stockCount int(11) NOT NULL DEFAULT '',
      PRIMARY KEY (id)
      );
