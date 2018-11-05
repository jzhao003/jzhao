CREATE TABLE ssq (
    lottery_date varchar(255) NOT NULL,
    red1 varchar(255),
    red2 varchar(255),
    red3 varchar(255),
    red4 varchar(255),
    red5 varchar(255),
    red6 varchar(255),
    blue varchar(255),
    PRIMARY KEY (lottery_date)
);

CREATE TABLE red_total (
    lottery_date varchar(255) NOT NULL,
    red_total varchar(255),
    PRIMARY KEY (lottery_date)
);