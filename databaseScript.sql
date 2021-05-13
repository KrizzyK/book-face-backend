-- DROP --

DROP SEQUENCE IF EXISTS person_id_seq cascade;
DROP TABLE IF EXISTS bookface_person cascade;
DROP SEQUENCE IF EXISTS friendship_id_seq cascade;
DROP TABLE IF EXISTS bookface_friendship cascade;
DROP SEQUENCE IF EXISTS people_block_id_seq cascade;
DROP TABLE IF EXISTS bookface_people_block cascade;
DROP SEQUENCE IF EXISTS post_id_seq cascade;
DROP TABLE IF EXISTS bookface_post cascade;


-- SEQUENCES --

CREATE SEQUENCE person_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE friendship_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE people_block_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE post_id_seq START 1 INCREMENT 1;


-- TABLES --

CREATE TABLE bookface_person (
            person_id               integer     primary key default nextval('person_id_seq')    NOT NULL,
            name                    varchar                                                     NOT NULL,
            surname                 varchar                                                     NOT NULL,
            bio                     varchar                                                             ,
            user_unique_id          varchar                                                     NOT NULL    UNIQUE,
            account_creation_date   timestamp                                                   NOT NULL
);


CREATE TABLE bookface_friendship (
             friendship_id           integer     primary key default nextval('friendship_id_seq')    NOT NULL,
             person_inviting         integer                                                         NOT NULL,
             person_accepting        integer                                                                 ,
             friendship_began        timestamp                                                       NOT NULL
);

CREATE TABLE bookface_people_block (
            block_id        integer            primary key default nextval('people_block_id_seq')       NOT NULL,
            person_blocking integer                                                                     NOT NULL,
            person_blocked  integer                                                                     NOT NULL
);

CREATE TABLE bookface_post (
        post_id             integer         primary key default nextval('post_id_seq')          NOT NULL,
        author              integer                                                             NOT NULL,
        title               varchar                                                                     ,
        content             varchar                                                             NOT NULL,
        creation_date       timestamp                                                           NOT NULL
);

-- RELATIONS --

ALTER TABLE bookface_friendship
    ADD CONSTRAINT  fk_person_accepting_friendship foreign key (person_accepting) references bookface_person(person_id);
ALTER TABLE bookface_friendship
    ADD CONSTRAINT fk_person_inviting_friendship foreign key (person_inviting) references bookface_person(person_id);

ALTER TABLE bookface_people_block
    ADD CONSTRAINT fk_person_blocking_block foreign key (person_blocking) references bookface_person(person_id);
ALTER TABLE bookface_people_block
    ADD CONSTRAINT fk_person_blocked_block foreign key (person_blocked) references bookface_person(person_id);

ALTER TABLE bookface_post
    ADD CONSTRAINT fk_author_post foreign key (author) references bookface_person(person_id);

-- FUNCTIONS --

create or replace function initFriendshipBeganDate()
    returns trigger as
$$
begin
    new.friendship_began = now();
    return new;
end;
$$
    language 'plpgsql';

create or replace function initAccountCreationDate()
    returns trigger as
$$
begin
    new.account_creation_date = now();
    return new;
end;
$$
    language 'plpgsql';

create or replace function initCreationDate()
    returns trigger as
$$
begin
    new.creation_date = now();
    return new;
end;
$$
    language 'plpgsql';

-- TRIGGERS --

create trigger bookface_friendship_began_trigger
    before insert
    on bookface_friendship
    for each row
        execute procedure initFriendshipBeganDate();

create trigger bookface_account_creation_trigger
    before insert
    on bookface_person
    for each row
        execute procedure initAccountCreationDate();

create trigger bookface_post_creation_trigger
    before insert
    on bookface_post
    for each row
        execute procedure initCreationDate();