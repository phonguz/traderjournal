alter table Account add column TRADER_ID Integer
alter table Account add foreign key (TRADER_ID) references trader(id)