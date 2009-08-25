alter table account add foreign key (base_ccy_id) references ccy(id)

alter table tradeevent add foreign key (event_type_id) references tradeeventtype(id)

alter table tradeevent add foreign key (tradeid) references trade(id)

alter table tradeeventimage add foreign key (event_id) references tradeevent(id)

alter table transaction add foreign key (transaction_type_id) references transaction_type(id)

alter table trade add foreign key (account_id) references account(id)

alter table tradeeventtype add primary key (id)

alter table tradeevent add primary key (id)

alter table trade add primary key (id)

alter table trade add column account_id int

alter table trade alter column account_id set not null

alter table tradeevent alter column tradeid set not null

alter table tradeevent alter column event_type_id set not null

alter table transaction alter column transaction_type_id set not null

alter table account alter column base_ccy_id set not null

update trade set account_id = 1 

select * from trade

alter table account add column id2 int generated by default as identity (start with 0);
alter table account drop column id;
alter table account alter column id2 rename to id;


alter table tradeeventimage alter column img2 rename to img

alter table trade drop column id

alter table tradeevent drop constraint sys_pk_60


create table trader (id int  generated by default as identity primary key, name varchar)

alter table trade add column trader_id int

alter table trade alter column trader_id set not null


alter table trader add column id int generated by default as identity (start with 0);
alter table trader drop column id;
alter table trader alter column id2 rename to id;

alter table trader drop constraint SYS_PK_128 

update trade set trader_id = 0

alter table trade add foreign key (trader_id) references trader(id)

alter table tradeevent add column new_value double

alter table trade add column qty2 double

update  trade set qty2 = qty

alter table trade drop column qty

alter table trade  alter column qty2 rename to qty

create table instrument (id int  generated by default as identity primary key, name varchar, value_per_point double, base_ccy_id int)

alter table instrument  alter column vaule_per_point rename to value_per_point

alter table trade add column instrument_id int

alter table trade add foreign key (instrument_id) references instrument(id)

alter table instrument add foreign key (base_ccy_id) references ccy(id)

alter table trade drop column instrument

insert into instrument (name,value_per_point,base_ccy_id) 