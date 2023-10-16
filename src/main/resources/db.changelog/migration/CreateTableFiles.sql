-- changeset agafonnikov:2
create table _files (
                        name text,
                        type text,
                        data bytea,
                        user_id integer,
                        primary key (name, user_id),
                        constraint fk_user
                            foreign key (user_id)
                                references _user (id)
);
--rollback drop table _files