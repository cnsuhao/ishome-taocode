CREATE TABLE wmui01
(
    puk VARCHAR(20) DEFAULT '0' NOT NULL,
    k01 VARCHAR(20),
    k02 VARCHAR(20),
    k03 VARCHAR(20),
    f01 VARCHAR(20) DEFAULT '0',
    f02 VARCHAR(20) DEFAULT '0',
    f03 VARCHAR(20),
    f04 VARCHAR(20),
    f05 VARCHAR(20),
    f06 VARCHAR(20),
    f07 VARCHAR(20) DEFAULT '0',
    f08 VARCHAR(20) DEFAULT '0',
    f09 VARCHAR(20) DEFAULT '0',
    f10 VARCHAR(20) DEFAULT '0',
    f11 VARCHAR(40),
    f12 VARCHAR(80),
    f13 VARCHAR(20),
    f14 VARCHAR(20),
    f15 VARCHAR(20),
    f16 VARCHAR(20),
    f17 VARCHAR(20),
    bbb VARCHAR(80),
    fb1 VARCHAR(20),
    fb2 VARCHAR(40),
    fb3 VARCHAR(80),
    fb4 VARCHAR(20) DEFAULT '0',
    fb5 VARCHAR(20),
    eb1 VARCHAR(20),
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
