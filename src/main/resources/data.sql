DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS favorite;
DROP TABLE IF EXISTS item;

CREATE TABLE user (
     id int(11) unsigned NOT NULL AUTO_INCREMENT,
     firstname varchar(20) NOT NULL DEFAULT '',
     lastname varchar(20) NOT NULL DEFAULT '',
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
     user_id varchar(1200) unsigned NOT NULL AUTO_INCREMENT,
     order_date TIMESTAMP (23,10),
     items varchar(1200) NOT NULL DEFAULT '',
     shipping_address varchar(200) NOT NULL DEFAULT '',
     total_price varchar(200) NOT NULL DEFAULT '',
     order_status varchar(20) NOT NULL DEFAULT '',
     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE favorite (
      id int(11) unsigned NOT NULL AUTO_INCREMENT,
      user_id varchar(1500) unsigned NOT NULL AUTO_INCREMENT,
      item varchar(1500) unsigned NOT NULL AUTO_INCREMENT,
      PRIMARY KEY (id),
      FOREIGN KEY (user_id) REFERENCES user(id)
                );
CREATE TABLE item (
      id int(11) unsigned NOT NULL AUTO_INCREMENT,
      title varchar(200) NOT NULL DEFAULT '',
      photo_url varchar(200) NOT NULL DEFAULT '',
      price varchar(200) NOT NULL DEFAULT '',
      stock_count varchar(200) NOT NULL DEFAULT '',
      PRIMARY KEY (id)
);