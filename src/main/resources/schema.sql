--create database bobFriend;
use bobFriend;

DROP TABLE IF EXISTS gatherings;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS places;

create table users (
	id bigint auto_increment primary key,
    name varchar(30) not null, /*realname*/
    email varchar(50) not null unique,
	password varchar(300) not null,
    is_active boolean default true,
    created_at timestamp default current_timestamp
);

create table places (
	id bigint auto_increment primary key,
	name varchar(50) unique,
    content text,
    address varchar(100) not null,
    category enum('KOR', 'JPN', 'CHN', 'WES', 'ETC') not null
);

create table gatherings (
	id bigint auto_increment primary key,
	title varchar(100) not null,
    content text not null,
    gathering_at datetime not null,
    closing_at datetime not null,
    created_at timestamp default current_timestamp,
    max_participant int not null,
    state enum('ING', 'END') not null,
    talk_thema varchar(100),
    talk_flag enum('I', 'E', 'N') not null,
	is_deleted boolean default false,
    user_id bigint not null, /*fk from users table*/
    place_id bigint, /*fk from places table*/
    foreign key (user_id) references users (id),
    foreign key (place_id) references places (id)
);

select * from users;