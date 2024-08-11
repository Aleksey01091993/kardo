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
