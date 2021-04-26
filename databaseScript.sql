-- DROP --

DROP SEQUENCE IF EXISTS person_id_seq cascade;
DROP TABLE IF EXISTS bookface_person cascade;
DROP SEQUENCE IF EXISTS friendship_id_seq cascade;
DROP TABLE IF EXISTS bookface_friendship cascade;

-- SEQUENCES --

CREATE SEQUENCE person_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE friendship_id_seq START 1 INCREMENT 1;

-- TABLES --

CREATE TABLE bookface_person (
                                 person_id   integer     primary key default nextval('person_id_seq')    NOT NULL,
                                 name        varchar                                                     NOT NULL,
                                 surname     varchar                                                     NOT NULL

);


CREATE TABLE bookface_friendship (
                                     friendship_id           integer     primary key default nextval('friendship_id_seq')    NOT NULL,
                                     person_inviting         integer                                                         NOT NULL,
                                     person_accepting        integer                                                                 ,
                                     friendship_began        timestamp                                                       NOT NULL

);

-- RELATIONS --

ALTER TABLE bookface_friendship
    ADD CONSTRAINT  fk_person_accepting_friendship foreign key (person_accepting) references bookface_person(person_id);
ALTER TABLE bookface_friendship
    ADD CONSTRAINT fk_person_inviting_friendship foreign key (person_inviting) references bookface_person(person_id);

-- FUNCTIONS --

create or replace function createFriendshipBeganDate()
    returns trigger as
$$
begin
    new.friendship_began = now();
    return new;
end;
$$
    language 'plpgsql';

-- TRIGGERS --

create trigger bookface_friendship_began_trigger
    before insert
    on bookface_friendship
    for each row
        execute procedure createFriendshipBeganDate();


-- COMMENTS --

comment on sequence person_id_seq is 'Primary key sequence for bookface_person table';

comment on table bookface_person is 'Table storing people information';

-- comment on column bookface_person is 'PK for conntento_product table';
