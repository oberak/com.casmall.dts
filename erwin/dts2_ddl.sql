-->>>TEST<<<-- ;
CREATE TABLE TS_PRDT_MST 
(
  PRDT_CD                  NUMERIC( 10, 0) NOT NULL,
  PRDT_MGT_CD              VARCHAR(    20),
  PRDT_NM                  VARCHAR(    30),
  UNT_PRC                  NUMERIC( 10, 0),
  NT                       VARCHAR(    50),
  MGT_YN                   VARCHAR(     1),
  DEL_YN                   VARCHAR(     1),
  TRN_DT                   TIMESTAMP,
  RGN_DT                   TIMESTAMP,
  RGN_ID                   VARCHAR(    20),
  EDT_DT                   TIMESTAMP,
  EDT_ID                   VARCHAR(    20),
  CONSTRAINT PK_TS_PRDT_MST PRIMARY KEY (PRDT_CD)
);

CREATE TABLE TS_CST_MST 
(
  CST_CD                    NUMERIC( 10, 0) NOT NULL,
  CST_MGT_CD                VARCHAR(    20),
  CST_NM                    VARCHAR(    30),
  RPR_NM                    VARCHAR(    30),
  TEL                       VARCHAR(    14),
  NT                        VARCHAR(    50),
  CHNG_CST_CD               NUMERIC( 10, 0),
  MGT_YN                    VARCHAR(     1),
  DEL_YN                    VARCHAR(     1),
  TRN_DT                    TIMESTAMP,
  RGN_DT                    TIMESTAMP,
  RGN_ID                    VARCHAR(    20),
  EDT_DT                    TIMESTAMP,
  EDT_ID                    VARCHAR(    20),
  CONSTRAINT PK_TS_CST_MST PRIMARY KEY (CST_CD)
);

CREATE TABLE TS_CAR_MST 
(
			  CAR_CD                  NUMERIC( 10, 0) NOT NULL,
			  CAR_MGT_CD              VARCHAR(    20),
			  CAR_NUM                 VARCHAR(    50),
			  DRVR                    VARCHAR(    10),
			  EMTCAR_WGH              NUMERIC( 10, 2),
			  PRDT_CD                 NUMERIC( 10, 0),
			  CST_CD                  NUMERIC( 10, 0),
			  NT                      VARCHAR(    50),
			  MGT_YN                  VARCHAR(     1),
			  DEL_YN                  VARCHAR(     1),
			  TRN_DT                  TIMESTAMP,
			  RGN_DT                  TIMESTAMP,
			  RGN_ID                  VARCHAR(    20),
			  EDT_DT                  TIMESTAMP,
			  EDT_ID                  VARCHAR(    20),
        CONSTRAINT PK_TS_CAR_MST PRIMARY KEY (CAR_CD),
        CONSTRAINT FK_TS_CAR_MST_01 FOREIGN KEY (PRDT_CD) REFERENCES TS_PRDT_MST(PRDT_CD),
        CONSTRAINT FK_TS_CAR_MST_02 FOREIGN KEY (CST_CD) REFERENCES TS_CST_MST(CST_CD)
);

CREATE TABLE TS_CARD_MST (
       CARD_CD             NUMERIC(10) NOT NULL,
       CARD_NUM             VARCHAR(20),
       PRDT_CD             NUMERIC(10),
       CST_CD              NUMERIC(10),
       OWNER_NM             VARCHAR(10),
       MM                   VARCHAR(20),
       DEL_YN               VARCHAR(20),
       TRN_DT               TIMESTAMP,
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       CAR_CD              NUMERIC(10),
       EDT_ID               VARCHAR(20),
       CONSTRAINT PK_TS_CARD_MST PRIMARY KEY (CARD_CD),
       CONSTRAINT FK_TS_CARD_MST_01 FOREIGN KEY (CAR_CD) REFERENCES TS_CAR_MST(CAR_CD),
       CONSTRAINT FK_TS_CARD_MST_02 FOREIGN KEY (CST_CD) REFERENCES TS_CST_MST(CST_CD),
       CONSTRAINT FK_TS_CARD_MST_03 FOREIGN KEY (PRDT_CD) REFERENCES TS_PRDT_MST(PRDT_CD)
);

CREATE TABLE TS_WGT_INF 
(
  WGT_CD                           NUMERIC( 10, 0) NOT NULL,
  CAR_CD                           NUMERIC( 10, 0),
  FST_WGT_DT                     	 TIMESTAMP,
  FST_WGH                          NUMERIC( 10, 2),
  FST_WGT_IMAGE_PATH               VARCHAR(    50),
  WGT_NUM                          VARCHAR(    20),
  CST_CD                           NUMERIC( 10, 0),
  PRDT_CD                          NUMERIC( 10, 0),
  SCND_WGT_DT                      TIMESTAMP,
  SCND_WGH                         NUMERIC( 10, 2),
  SCND_WGT_IMAGE_PATH              VARCHAR(    50),
  DSCNT_BSS_CD                     VARCHAR(     2),
  DSCNT_VAL                        NUMERIC( 10, 2),
  DSCNT                            NUMERIC( 10, 2),
  RL_WGH                           NUMERIC( 10, 2),
  IO_FLG                           VARCHAR(     2),
  NT                               VARCHAR(    50),
  WGT_FLG_CD                       VARCHAR(     2),
  WGT_STAT_CD                      VARCHAR(     2),
  EXT_1                            VARCHAR(    50),
  EXT_2                            VARCHAR(    50),
  EXT_3                            VARCHAR(    50),
  EXT_4                            VARCHAR(    50),
  EXT_5                            VARCHAR(    50),
  EXT_6                            VARCHAR(    50),
  DRVR                             VARCHAR(    10),
  AMT                              NUMERIC( 10, 2),
  CARD_CD                          NUMERIC(    10),
  DEL_YN                           VARCHAR(     1),
  TRN_DT                           TIMESTAMP,
  RGN_DT                           TIMESTAMP,
  RGN_ID                           VARCHAR(    20),
  EDT_DT                           TIMESTAMP,
  EDT_ID                           VARCHAR(    20),
  UNT_PRC                          INTEGER,
	CONSTRAINT PK_TS_WGT_INF PRIMARY KEY (WGT_CD),
	CONSTRAINT FK_TS_WGT_INF_CARD_CD FOREIGN KEY (CARD_CD) REFERENCES TS_CARD_MST(CARD_CD),
	CONSTRAINT FK_TS_WGT_INF_CST_CD FOREIGN KEY (CST_CD) REFERENCES TS_CST_MST(CST_CD),
	CONSTRAINT FK_TS_WGT_INF_PRDT_CD FOREIGN KEY (PRDT_CD) REFERENCES TS_PRDT_MST(PRDT_CD),
	CONSTRAINT FK_TS_WGT_INF_CAR_CD FOREIGN KEY (CAR_CD) REFERENCES TS_CAR_MST(CAR_CD)
);

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
       TRN_DT               TIMESTAMP,
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       EDT_ID               VARCHAR(20),
       CONSTRAINT PK_TS_PRT_INF PRIMARY KEY (PRT_SEQ)
);

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
       TKN                  NUMERIC(5,2),
       PRT_YN               VARCHAR(1),
 			 LINE_COLOR           VARCHAR(11),
       DEL_YN               VARCHAR(1),
       TRN_DT               TIMESTAMP,
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       EDT_ID               VARCHAR(20),
       CONSTRAINT PK_TS_PRT_ATTR PRIMARY KEY (PRT_SEQ, ATTR_SEQ),
       CONSTRAINT FK_TS_PRT_ATTR_01 FOREIGN KEY (PRT_SEQ) REFERENCES TS_PRT_INF(PRT_SEQ)
);

CREATE TABLE CM_MSG (
       MSG_ID               VARCHAR(20) NOT NULL,
       STT_CHAR             VARCHAR(20),
       END_CHAR             VARCHAR(20),
       MSG_TOT_LEN          INTEGER,
       DEL_YN               VARCHAR(1),
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       EDT_ID               VARCHAR(20),
       SVR_TRN_DT           TIMESTAMP,
       CONSTRAINT PK_CM_MSG PRIMARY KEY (MSG_ID)
);

CREATE TABLE CM_MSG_ATTR (
       MSG_ID               VARCHAR(20) NOT NULL,
       ATTR_SEQ             NUMERIC(10) NOT NULL,
       ATTR_PRIOR           NUMERIC(2),
       ATTR_NM              VARCHAR(30),
       ATTR_LEN             NUMERIC(3),
       ATTR_TYPE_CD         VARCHAR(2),
       STT_POS              NUMERIC(  5, 0),
       DEL_YN               VARCHAR(1),
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       EDT_ID               VARCHAR(20),
       SVR_TRN_DT           TIMESTAMP,
       CONSTRAINT PK_CM_MSG_ATTR PRIMARY KEY (MSG_ID, ATTR_SEQ),
       CONSTRAINT FK_CM_MSG_ATTR_01 FOREIGN KEY (MSG_ID) REFERENCES CM_MSG(MSG_ID)
);

CREATE TABLE CM_OS_MC (
       MC_ID                VARCHAR(20) NOT NULL,
       PORT_NM              VARCHAR(10),
       MC_NM                VARCHAR(30),
       BAUD_RATE            INTEGER,
       DATA_BITS            INTEGER,
       PARITY_BITS          INTEGER,
       STOP_BITS            INTEGER,
       PORT_OPEN_WAIT_TIME  INTEGER,
       READ_BUFF_SIZE       INTEGER,
       READ_WAIT_TIME       INTEGER,
       READ_RETRY_CNT       INTEGER,
       DEL_YN               VARCHAR(1),
       RGN_DT               TIMESTAMP,
       RGN_ID               VARCHAR(20),
       EDT_DT               TIMESTAMP,
       EDT_ID               VARCHAR(20),
       SVR_TRN_DT           TIMESTAMP,
       CONSTRAINT PK_CM_OS_MC PRIMARY KEY (MC_ID)
);

CREATE TABLE CM_CD_MST 
(
  CD_ID                VARCHAR(    30) NOT NULL,
  CD_DESC              VARCHAR(    50),
  CONSTRAINT PK_CM_CD_MST PRIMARY KEY (CD_ID)
);

CREATE TABLE CM_CD 
(
  CD_ID                   VARCHAR(    30) NOT NULL,
  CD_VAL                  VARCHAR(    30) NOT NULL,
  CD_NM                   VARCHAR(    200),
  MARK_PRIOR              NUMERIC(  2, 0),
  CONSTRAINT PK_CM_CD PRIMARY KEY (CD_ID, CD_VAL),
  CONSTRAINT FK_CM_CD_01 FOREIGN KEY (CD_ID) REFERENCES CM_CD_MST(CD_ID)
);

CREATE TABLE CM_USR_INF 
(
  USR_SEQ              NUMERIC( 10, 0) NOT NULL,
  LGN_ID               VARCHAR(    20),
  LGN_PW               VARCHAR(    64),
  USR_NM               VARCHAR(    10),
  TEL                  VARCHAR(    14),
  ATH_GRD              VARCHAR(    10),
  NT                   VARCHAR(    50),
  DEL_YN               VARCHAR(     1),
  RGN_DT               TIMESTAMP,
  RGN_ID               VARCHAR(    20),
  EDT_DT               TIMESTAMP,
  EDT_ID               VARCHAR(    20),
  TRN_DT               TIMESTAMP,
  ATH_CD               INTEGER,
  CONSTRAINT PK_CM_USR_INF PRIMARY KEY (USR_SEQ)
);
