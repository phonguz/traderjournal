alter table trade drop column trader_id

alter table trade drop constraint SYS_FK_126

SET SCRIPTFORMAT COMPRESSED;
SET SCRIPTFORMAT BINARY;

SHUTDOWN ;