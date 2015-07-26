
CREATE TABLE TS_CAR_MST (
       CAR_SEQ              NUMERIC(10) NOT NULL,
       CAR_MGT_CD           VARCHAR(20),
       CAR_NUM              VARCHAR(20),
       DRVR                 VARCHAR(10),
       EMTCAR_WGH           INTEGER,
       NT                   VARCHAR(20),
       MGT_YN               VARCHAR(20),
       DEL_YN               VARCHAR(20),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       CST_SEQ              NUMERIC(10),
       EDT_DT               DATE,
       PRDT_SEQ             NUMERIC(10),
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_CAR_MST ON TS_CAR_MST
(
       CAR_SEQ
);


ALTER TABLE TS_CAR_MST
       ADD PRIMARY KEY (CAR_SEQ);


CREATE TABLE TS_CARD_MST (
       CARD_SEQ             NUMERIC(10) NOT NULL,
       CARD_NUM             VARCHAR(20),
       PRDT_SEQ             NUMERIC(10),
       CST_SEQ              NUMERIC(10),
       OWNER_NM             VARCHAR(10),
       MM                   VARCHAR(20),
       DEL_YN               VARCHAR(20),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       EDT_DT               DATE,
       CAR_SEQ              NUMERIC(10),
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_CARD_MST ON TS_CARD_MST
(
       CARD_SEQ
);


ALTER TABLE TS_CARD_MST
       ADD PRIMARY KEY (CARD_SEQ);


CREATE TABLE TS_CST_MST (
       CST_SEQ              NUMERIC(10) NOT NULL,
       CST_MGT_CD           VARCHAR(20),
       CST_NM               VARCHAR(30),
       RPR_NM               VARCHAR(30),
       TEL                  VARCHAR(14),
       NT                   VARCHAR(20),
       CHNG_CST_SEQ         NUMERIC(10),
       MGT_YN               VARCHAR(20),
       DEL_YN               VARCHAR(20),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       EDT_DT               DATE,
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_CST_MST ON TS_CST_MST
(
       CST_SEQ
);


ALTER TABLE TS_CST_MST
       ADD PRIMARY KEY (CST_SEQ);


CREATE TABLE TS_PRDT_MST (
       PRDT_SEQ             NUMERIC(10) NOT NULL,
       PRDT_MGT_CD          VARCHAR(20),
       PRDT_NM              VARCHAR(30),
       UNT_PRC              NUMERIC(10),
       NT                   VARCHAR(20),
       MGT_YN               VARCHAR(20),
       DEL_YN               VARCHAR(20),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       EDT_DT               DATE,
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_PRDT_MST ON TS_PRDT_MST
(
       PRDT_SEQ
);


ALTER TABLE TS_PRDT_MST
       ADD PRIMARY KEY (PRDT_SEQ);


CREATE TABLE TS_WGT_INF (
       WGT_SEQ              NUMERIC(10) NOT NULL,
       FST_WGT_DT           DATE,
       SCND_WGT_DT          DATE,
       FST_WGT_IMAGE_PATH   VARCHAR(20),
       SCND_WGT_IMAGE_PATH  VARCHAR(20),
       FST_WGH              INTEGER,
       SCND_WGH             INTEGER,
       DSCNT_BSS_CD         VARCHAR(2),
       DSCNT_VAL            INTEGER,
       RL_WGH               INTEGER,
       DSCNT                INTEGER,
       UNT_PRC              VARCHAR(2),
       AMT                  INTEGER,
       DRVR                 VARCHAR(10),
       IO_FLG               VARCHAR(2),
       EXT_1                VARCHAR(20),
       WGT_FLG_CD           VARCHAR(2),
       EXT_2                VARCHAR(20),
       NT                   VARCHAR(20),
       MGT_CAR_YN           VARCHAR(20),
       EXT_5                VARCHAR(20),
       EXT_6                VARCHAR(20),
       CAR_SEQ              NUMERIC(10),
       DEL_YN               VARCHAR(20),
       EXT_3                VARCHAR(20),
       TRN_DT               DATE,
       PRDT_SEQ             NUMERIC(10),
       RGN_DT               DATE,
       EXT_4                VARCHAR(20),
       RGN_ID               VARCHAR(20),
       CST_SEQ              NUMERIC(10),
       EDT_DT               DATE,
       CARD_SEQ             NUMERIC(10),
       EDT_ID               VARCHAR(20),
       WGT_NUM              VARCHAR(20),
       WGT_STAT_CD          VARCHAR(2)
);

CREATE UNIQUE INDEX XPKTS_WGT_INF ON TS_WGT_INF
(
       WGT_SEQ
);


ALTER TABLE TS_WGT_INF
       ADD PRIMARY KEY (WGT_SEQ);


ALTER TABLE TS_WGT_INF
       ADD FOREIGN KEY (CARD_SEQ)
                             REFERENCES TS_CARD_MST;


ALTER TABLE TS_WGT_INF
       ADD FOREIGN KEY (CST_SEQ)
                             REFERENCES TS_CST_MST;


ALTER TABLE TS_WGT_INF
       ADD FOREIGN KEY (PRDT_SEQ)
                             REFERENCES TS_PRDT_MST;


ALTER TABLE TS_WGT_INF
       ADD FOREIGN KEY (CAR_SEQ)
                             REFERENCES TS_CAR_MST;


ALTER TABLE TS_CAR_MST
       ADD FOREIGN KEY (PRDT_SEQ)
                             REFERENCES TS_PRDT_MST;


ALTER TABLE TS_CAR_MST
       ADD FOREIGN KEY (CST_SEQ)
                             REFERENCES TS_CST_MST;


ALTER TABLE TS_CARD_MST
       ADD FOREIGN KEY (CAR_SEQ)
                             REFERENCES TS_CAR_MST;


ALTER TABLE TS_CARD_MST
       ADD FOREIGN KEY (CST_SEQ)
                             REFERENCES TS_CST_MST;


ALTER TABLE TS_CARD_MST
       ADD FOREIGN KEY (CST_SEQ)
                             REFERENCES TS_CST_MST;


ALTER TABLE TS_CARD_MST
       ADD FOREIGN KEY (PRDT_SEQ)
                             REFERENCES TS_PRDT_MST;


CREATE TABLE TS_PRT_ATTR (
       PRT_SEQ              NUMERIC(10) NOT NULL,
       ATTR_SEQ             NUMERIC(10) NOT NULL,
       ATTR_FLG_CD          VARCHAR(2),
       ATTR_NM              VARCHAR(200),
       ATTR_CD              VARCHAR(50),
       DATA_TYPE_CD         VARCHAR(2),
       DATA_FMT             VARCHAR(50),
       FONT                 VARCHAR(200),
       FONT_COLOR           VARCHAR(11),
       BG_COLOR             VARCHAR(11),
       AREA                 VARCHAR(50),
       STYLE                INTEGER,
       TKN                  INTEGER,
       PRT_YN               VARCHAR(1),
       DEL_YN               VARCHAR(1),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       EDT_DT               DATE,
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_PRT_ATTR ON TS_PRT_ATTR
(
       PRT_SEQ,
       ATTR_SEQ
);


ALTER TABLE TS_PRT_ATTR
       ADD PRIMARY KEY (PRT_SEQ, ATTR_SEQ);


CREATE TABLE TS_PRT_INF (
       PRT_SEQ              NUMERIC(10) NOT NULL,
       PRT_NM               VARCHAR(30),
       PAPER_WIDTH          INTEGER,
       PAPER_HEIGHT         INTEGER,
       BSS_CDNT_X           INTEGER,
       BSS_CDNT_Y           INTEGER,
       PRT_CNT              INTEGER,
       WDT_PRT_YN           VARCHAR(1),
       BSS_FONT             VARCHAR(200),
       DEL_YN               VARCHAR(1),
       TRN_DT               DATE,
       RGN_DT               DATE,
       RGN_ID               VARCHAR(20),
       EDT_DT               DATE,
       EDT_ID               VARCHAR(20)
);

CREATE UNIQUE INDEX XPKTS_PRT_INF ON TS_PRT_INF
(
       PRT_SEQ
);


ALTER TABLE TS_PRT_INF
       ADD PRIMARY KEY (PRT_SEQ);


ALTER TABLE TS_PRT_ATTR
       ADD FOREIGN KEY (PRT_SEQ)
                             REFERENCES TS_PRT_INF;







