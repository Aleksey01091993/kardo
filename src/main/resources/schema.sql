CREATE TABLE IF NOT EXISTS users
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    password varchar NOT NULL,
    first_name varchar(50),
    surname varchar(50),
    patronymic varchar(50),
    email varchar(70) NOT NULL unique,
    birthday TIMESTAMP WITHOUT TIME ZONE,
    phone varchar(12),
    social_media_url varchar(255),
    portfolio_url varchar(255),
    country varchar(40),
    city varchar(40),
    region varchar (60),
    role varchar(15)
    );

CREATE TABLE IF NOT EXISTS videos
(
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title varchar NOT NULL,
    video_path varchar NOT NULL,
    user_id INT,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id)
);

create table if not exists events
(
    event_date_end   date,
    event_date_start date,
    id               bigserial not null,
    category         varchar(255),
    description      varchar(255),
    name             varchar(255),
    primary key (id)
);

create table if not exists epic
(
    event_id    bigint    not null,
    id          bigserial not null,
    description varchar(255),
    name        varchar(255),
    primary key (id),
    foreign key (event_id) references events(id)
);

create table if not exists request
(
    event_id                  bigint,
    id                        bigserial not null,
    requester_id              bigint,
    current_place             varchar(255),
    current_stage             varchar(255),
    current_stage_description varchar(255),
    user_status               varchar(255) check (user_status in ('PARTICIPANT', 'RETIRED', 'PASSED')),
    primary key (id),
    foreign key (event_id) references events(id),
    foreign key (requester_id) references users(id)
);

create table if not exists stages
(
    stage_date_end   date,
    stage_date_start date,
    event_id         bigint,
    id               bigserial not null,
    description      varchar(255),
    name             varchar(255),
    task             varchar(255),
    primary key (id),
    foreign key (event_id) references events(id)
);

create table if not exists request_stage
(
    id         bigserial not null,
    request_id bigint    not null,
    stage_id   bigint,
    result     varchar(255),
    status     varchar(255) check (status in ('PARTICIPANT', 'RETIRED', 'PASSED')),
    primary key (id),
    foreign key (request_id) references request(id),
    foreign key (stage_id) references stages(id)
);
