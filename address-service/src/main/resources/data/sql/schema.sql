create sequence public.address_sequence start with 100;

create table public.address(
    id bigint default next value for public.address_sequence not null,
    city varchar(255),
    district varchar(255));

alter table public.address add constraint public.address_pk primary key(id);