CREATE TABLE TRADEEVENTIMAGE (
		ID INTEGER,
		EVENT_ID INTEGER,
		DESCRIPTION VARCHAR(2147483647),
		ID2 INTEGER,
		IMG2 LONGVARBINARY
	);

CREATE TABLE ACCOUNT (
		ID INTEGER NOT NULL,
		NAME VARCHAR(250) NOT NULL,
		BALANCE DOUBLE NOT NULL,
		BASE_CCY_ID INTEGER
	);

CREATE TABLE TRANSACTION (
		ID INTEGER NOT NULL,
		TRANSACTION_TYPE_ID INTEGER NOT NULL,
		TRANSACTION_VALUE DOUBLE NOT NULL
	);

CREATE TABLE TRADEEVENT (
		ID INTEGER,
		EVENT_DATE TIMESTAMP,
		EVENT_TYPE_ID INTEGER,
		DESCRIPTION VARCHAR(2147483647),
		EVENTORDER INTEGER,
		TRADEID INTEGER
	);

CREATE TABLE TRANSACTION_TYPE (
		ID INTEGER NOT NULL,
		TYPE VARCHAR(250) NOT NULL
	);

CREATE TABLE TRADEEVENTTYPE (
		ID INTEGER,
		NAME VARCHAR(2147483647)
	);

CREATE TABLE CCY (
		ID INTEGER NOT NULL,
		NAME VARCHAR(25) NOT NULL
	);

CREATE TABLE TRADE (
		ID INTEGER,
		OPEN_TRADE_DATE TIMESTAMP,
		OPENPRICE DOUBLE,
		CLOSE_TRADE_DATE TIMESTAMP,
		CLOSEPRICE DOUBLE,
		STOPLOSS DOUBLE,
		TP DOUBLE,
		QTY INTEGER,
		INSTRUMENT VARCHAR(2147483647),
		ENTRYCOMS DOUBLE,
		EXITCOMS DOUBLE,
		ENTRYFEE DOUBLE,
		EXITFEE DOUBLE,
		PL DOUBLE,
		REFERENCE VARCHAR(2147483647),
		CARRYCOST DOUBLE
	);

CREATE UNIQUE INDEX SYS_IDX_53 ON CCY (ID ASC);

CREATE UNIQUE INDEX SYS_IDX_51 ON TRANSACTION (ID ASC);

CREATE UNIQUE INDEX SYS_IDX_52 ON TRANSACTION_TYPE (ID ASC);

CREATE UNIQUE INDEX SYS_IDX_50 ON ACCOUNT (ID ASC);

ALTER TABLE TRANSACTION ADD CONSTRAINT PRIM_KEY PRIMARY KEY (ID);

ALTER TABLE TRANSACTION_TYPE ADD CONSTRAINT TRANSACTION_TYPE_PRIM_KEY PRIMARY KEY (ID);

ALTER TABLE ACCOUNT ADD CONSTRAINT PRIMKEY PRIMARY KEY (ID);

ALTER TABLE CCY ADD CONSTRAINT CCY_PRIMARY_KEY PRIMARY KEY (ID);
