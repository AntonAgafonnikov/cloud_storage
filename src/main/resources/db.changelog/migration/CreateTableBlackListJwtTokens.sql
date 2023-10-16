-- changeset agafonnikov:3
create table _black_list_jwt_tokens (
    token text primary key
);
--rollback drop table _black_list_jwt_tokens