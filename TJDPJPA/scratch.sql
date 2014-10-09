alter table trade drop column trader_id

alter table trade drop constraint SYS_FK_126 

delete from tradeevent where id > 250

update  tradeeventimage set event_type_id = 1 where event_type_id > 6

update  tradeevent set event_type_id=7 where event_type_id =0


select * from tradeevent order by tradeid