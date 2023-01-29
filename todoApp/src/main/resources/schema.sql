CREATE DATABASE todo_app;

CREATE TABLE IF NOT EXISTS `category`(
	`category_id` int not null auto_increment,
	`name` varchar(20),
	`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	PRIMARY KEY(`category_id`)
);

CREATE TABLE IF NOT EXISTS `task`(
	`task_id` int not null auto_increment,
	`description` TINYTEXT,
        `category_id_fk` int not null,
        `completed` bit DEFAULT 0,
	`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	PRIMARY KEY(`task_id`),
        CONSTRAINT category_id_FK FOREIGN KEY (category_id_fk)
        REFERENCES category(category_id)
);

ALTER TABLE task DROP CONSTRAINT category_id_FK;

ALTER TABLE task
ADD FOREIGN KEY (category_id_FK) REFERENCES category(category_id) ON DELETE CASCADE;