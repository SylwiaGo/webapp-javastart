insert into
    users (email, password, first_name)
values
    ('admin@example.com', '{noop}admin', 'Sylwia'),   -- 1
    ('user@example.com', '{noop}user', 'Bolek');     -- 2

insert into
    user_role (name, description)
values
    ('ADMIN', 'wszystkie uprawnienia'),   -- 1
    ('USER', 'podstawowe uprawnienia, oddawanie glosow');   -- 2

insert into
    user_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);