CREATE TABLE wmbm01
(
    puk VARCHAR(20) DEFAULT '0' NOT NULL,
    k01 VARCHAR(20),
    k02 VARCHAR(20),
    k03 VARCHAR(20),
    f05 VARCHAR(20),
    f06 VARCHAR(20),
    f15 VARCHAR(20),
    f16 VARCHAR(20),
    f17 VARCHAR(20),
    f18 VARCHAR(20),
    f19 VARCHAR(200),
    bbb VARCHAR(80),
    fb1 VARCHAR(20),
    fb2 VARCHAR(20) DEFAULT '0',
    fb3 VARCHAR(20) DEFAULT '0',
    fb4 VARCHAR(20) DEFAULT '0',
    fb5 VARCHAR(20) DEFAULT '0',
    eb1 VARCHAR(20) DEFAULT '0',
    eb2 VARCHAR(20),
    eb3 VARCHAR(20),
    eb4 VARCHAR(20),
    eb5 VARCHAR(20),
    ddd CHAR(1) DEFAULT '0' NOT NULL,
    cc1 VARCHAR(24) DEFAULT '2013/5/1 17:14:13' NOT NULL,
    cc2 VARCHAR(20) DEFAULT 'Spook' NOT NULL,
    uu1 VARCHAR(24),
    uu2 VARCHAR(20),
PRIMARY KEY (puk)
)