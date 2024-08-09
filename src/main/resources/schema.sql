CREATE TABLE users (
                       birthday date NULL,
                       id bigserial NOT NULL,
                       city varchar(255) NULL,
                       country varchar(255) NULL,
                       email varchar(255) NOT NULL,
                       first_name varchar(255) NULL,
                       last_name varchar(255) NULL,
                       "password" varchar(255) NULL,
                       phone varchar(255) NULL,
                       portfolio_url varchar(255) NULL,
                       "role" varchar(255) NULL,
                       social_media_url varchar(255) NULL,
                       surname varchar(255) NULL,
                       CONSTRAINT users_email_key UNIQUE (email),
                       CONSTRAINT users_phone_key UNIQUE (phone),
                       CONSTRAINT users_pkey PRIMARY KEY (id),
                       CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ROLE_ADMIN'::character varying, 'ROLE_EXPERT'::character varying, 'ROLE_USER'::character varying])::text[])))
);

CREATE TABLE videos (
                        id bigserial NOT NULL,
                        user_id int8 NULL,
                        title varchar(255) NULL,
                        video_path varchar(255) NULL,
                        CONSTRAINT videos_pkey PRIMARY KEY (id),
                        CONSTRAINT fk75696octon297ywni28sk19ek FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE events (
                        event_date_end date NULL,
                        event_date_start date NULL,
                        event_id int8 NOT NULL,
                        id bigserial NOT NULL,
                        category varchar(255) NULL,
                        event_description varchar(255) NULL,
                        "name" varchar(255) NULL,
                        CONSTRAINT events_pkey PRIMARY KEY (id),
                        CONSTRAINT fk6a9ca9blpgs4t0swnpddck5l0 FOREIGN KEY (event_id) REFERENCES events(id)
);

CREATE TABLE request (
                         event_id int8 NULL,
                         id bigserial NOT NULL,
                         requester_id int8 NULL,
                         admin_status varchar(255) NULL,
                         current_place varchar(255) NULL,
                         current_stage varchar(255) NULL,
                         current_stage_description varchar(255) NULL,
                         user_status varchar(255) NULL,
                         CONSTRAINT request_admin_status_check CHECK (((admin_status)::text = ANY ((ARRAY['NEW'::character varying, 'CONFIRMED'::character varying, 'REJECTED'::character varying])::text[]))),
	CONSTRAINT request_pkey PRIMARY KEY (id),
	CONSTRAINT request_user_status_check CHECK (((user_status)::text = ANY ((ARRAY['PARTICIPANT'::character varying, 'VISITOR'::character varying, 'RETIRED'::character varying, 'PASSED'::character varying])::text[])))
);

CREATE TABLE stages (
                        stage_date_end date NULL,
                        stage_date_start date NULL,
                        event_id int8 NULL,
                        id bigserial NOT NULL,
                        event_description varchar(255) NULL,
                        "name" varchar(255) NULL,
                        task varchar(255) NULL,
                        CONSTRAINT stages_pkey PRIMARY KEY (id)
);

CREATE TABLE request_stage (
                               id bigserial NOT NULL,
                               request_id int8 NOT NULL,
                               stage_id int8 NULL,
                               "result" varchar(255) NULL,
                               status varchar(255) NULL,
                               CONSTRAINT request_stage_pkey PRIMARY KEY (id),
                               CONSTRAINT request_stage_status_check CHECK (((status)::text = ANY ((ARRAY['PARTICIPANT'::character varying, 'VISITOR'::character varying, 'RETIRED'::character varying, 'PASSED'::character varying])::text[]))),
	CONSTRAINT fk49lbuyq4kfhp7frph6secn140 FOREIGN KEY (request_id) REFERENCES request(id),
	CONSTRAINT fkeuodrag2i1pp3kv0uj1oe3yri FOREIGN KEY (stage_id) REFERENCES stages(id)
);
