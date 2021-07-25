DROP TABLE IF EXISTS PLAYER;

CREATE TABLE PLAYER (
  player_Id INT  PRIMARY KEY,
  player_Name VARCHAR(250) ,
  player_Age INT,
  email_Id  VARCHAR(250) ,
  total_Score INT  
);

  commit;


DROP TABLE IF EXISTS TOKEN ;
  commit;
CREATE TABLE TOKEN (
  token_Id INT   PRIMARY KEY,
  access_Token VARCHAR(250) ,
  player_ID INT ,
  generate_Time_Stamp VARCHAR(250) 
);

  commit;

DROP TABLE IF EXISTS SUBMISSION;


CREATE TABLE SUBMISSION (
  submission_Id INT   PRIMARY KEY,
  player_ID INT ,
  token_Id INT,
  access_Token VARCHAR(250) ,
  text VARCHAR(250) ,
  score INT ,
   status VARCHAR(250) ,
   message VARCHAR(250) ,
);

  commit;





CREATE SEQUENCE PLAYER_SEQUENCE_ID START WITH (select max( PLAYER_ID) + 1 from PLAYER);

  commit;

CREATE SEQUENCE TOKEN_SEQUENCE_ID START WITH (select max(TOKEN_ID) + 1 from TOKEN);

  commit;

CREATE SEQUENCE SUBMISSION_SEQUENCE_ID START WITH (select max(SUBMISSION_ID) + 1 from SUBMISSION);

  commit;