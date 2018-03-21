create table user (
  login_name varchar(32)  not null, 
  login_password varchar(64) not null,
  constraint pk_login_name primary key (login_name)
);

create table released_apk (
  id int not null primary key,
  app_name varchar(128), 
  version_code int not null, 
  version_name varchar(128) not null, 
  file_name varchar(128) not null, 
  file_path varchar(512) not null, 
  file_size int, 
  uploader varchar(32), 
  desc varchar(512), 
  uploadtime date
);


--创建资源统一的Sequence
--DROP SEQUENCE SEQ_RESOURCE_ID;

CREATE SEQUENCE SEQ_RES_XTBH
  START WITH 500
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20;
  
CREATE SEQUENCE SEQ_PROJ_PROJECT_INFO
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20;
  
CREATE SEQUENCE SEQ_PROJ_DANGERPOINT
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20;
CREATE SEQUENCE SEQ_PROJ_MATERIALREPORT
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20;  
CREATE SEQUENCE SEQ_PROJ_REPORTPROBLEM
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20; 
CREATE SEQUENCE SEQ_PROJ_MOBILE_SIGNIN
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20; 
  
  CREATE SEQUENCE SEQ_GD_SIGNINADDRESS
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20; 
  
  CREATE SEQUENCE SEQ_GD_SIGNIN
  START WITH 0
  INCREMENT BY 1
  MAXVALUE 9999999999999999
  MINVALUE 0 
  CACHE 20; 
  
  
ALTER TABLE RES_ODM
MODIFY(XTBH VARCHAR2(12 BYTE))

  
  
ALTER TABLE RES_JF
MODIFY(XTBH NVARCHAR2(12));

ALTER TABLE RES_GPRS_RECORD
ADD(SIMID VARCHAR2(16));

CREATE INDEX IDX_WPLAN_PATROLRECORD_ID ON WPLAN_PATROLRECORD(ID);
CREATE INDEX IDX_WPLAN_PATROLRECORD_ITEM ON WPLAN_PATROLRECORD(EXECUTERESULT_ID,SUBITEM_ID);

ALTER TABLE RES_GPRS_RECORD
 ADD (CT_X  FLOAT);

ALTER TABLE RES_GPRS_RECORD
 ADD (CT_Y  FLOAT);
 
--将FILEPATHINFO的FILEID字段扩充为36个字符 
ALTER TABLE FILEPATHINFO MODIFY(FILEID VARCHAR2(36 BYTE));


--DROP TABLE GD_SIGNINADDRESS;
CREATE TABLE GD_SIGNINADDRESS
(
  POINT_ID     VARCHAR2(36 BYTE)                PRIMARY KEY NOT NULL ,
  POINT_NAME   VARCHAR2(100 BYTE),
  LON          NUMBER,
  LAT          NUMBER,
  ADDRESS      VARCHAR2(500 BYTE),
  LAC          VARCHAR2(100 BYTE),
  CI           VARCHAR2(100 BYTE),
  ORG_ID       VARCHAR2(30 BYTE),
  USER_ID      VARCHAR2(30 BYTE),
  CREATE_DATE  DATE,
  STATE        VARCHAR2(10 BYTE)                DEFAULT '1'                   NOT NULL,
  OBJECTID     INTEGER                           NOT NULL,
  SHAPE        SDE.ST_GEOMETRY
);

--DROP TABLE GD_SIGNIN;
CREATE TABLE GD_SIGNIN
(
  SIGNIN_ID    VARCHAR2(36),
  POINT_ID     VARCHAR2(100),
  LON          NUMBER,
  LAT          NUMBER,
  LAC          VARCHAR2(100),
  CI           VARCHAR2(100),
  USER_ID      VARCHAR2(36),
  SIGNIN_TIME  DATE,
  IMEI         VARCHAR2(30),
  OBJECTID     INTEGER                           NOT NULL,
  SHAPE        SDE.ST_GEOMETRY
);

--以下在SDE服务器的命令行运行
--注册空间表
--sdetable -o register -t GD_SIGNINADDRESS -c OBJECTID -C SDE -u dwuser -p dwuser
--sdetable -o register -t GD_SIGNIN -c OBJECTID -C SDE -u dwuser -p dwuser
--注册图层
--sdelayer -o register -l GD_SIGNINADDRESS,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser
--sdelayer -o register -l GD_SIGNIN,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser
--删除图层注册信息
--sdelayer -o delete -l GD_SIGNINADDRESS,SHAPE  -u dwuser -p dwuser
--sdelayer -o delete -l GD_SIGNIN,SHAPE  -u dwuser -p dwuser
--空间表注册信息
--sdetable -o unregister -t GD_SIGNINADDRESS -u dwuser -p dwuser
--sdetable -o unregister -t GD_SIGNIN -u dwuser -p dwuser

--2013-04-03 BY YLY
--添加三张表：工程表（RES_PROJECT  ）、施工单位表（RES_BUIDLER ）、施工单位和工程关联表（RES_PROJECT_BUILDER ）
--1.工程表：ID(系统ID）、PROJECT_NAME（工程项目名称）、PROJECT_CODE（工程项目编号）
create table RES_PROJECT  (
   ID              VARCHAR2(30)                    not null,
   PROJECT_NAME      VARCHAR2(300),
   PROJECT_CODE             VARCHAR2(100),
   constraint PK_RES_PROJECT primary key ("ID")
);
--2.施工单位表：ID(系统ID）、BUILDER_NAME（施工单位名称）
create table RES_BUIDLER  (
   ID                   VARCHAR2(30)   not null,
   BUILDER_NAME         VARCHAR2(300),
   constraint PK_RES_BUILDER primary key (ID)
);
 
--3.工程和施工单位关联表：BUILDER_ID（施工单位ID)、PROJECT_ID   （工程ID）
create table RES_PROJECT_BUILDER  (
   BUILDER_ID           VARCHAR2(30),
   PROJECT_ID           VARCHAR2(30),
   ID                   VARCHAR2(30)               not null,
   constraint PK_RES_PROJECT_BUILDER primary key (ID)
);

--ODF:(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_ODF ADD PROJECT_NAME VARCHAR2(30);
--ODM:(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_ODM ADD PROJECT_NAME VARCHAR2(30);
--引上:(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_YS ADD PROJECT_NAME VARCHAR2(30);
--挂墙：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_GQXX ADD PROJECT_NAME VARCHAR2(30);
--挂墙段：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_GQD ADD PROJECT_NAME VARCHAR2(30);
--杆路段：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_GLD ADD PROJECT_NAME VARCHAR2(30);
--标石段：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_BSD ADD PROJECT_NAME VARCHAR2(30);
--管道段：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_GDD ADD PROJECT_NAME VARCHAR2(30);
--站点：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_ZDXX ADD PROJECT_NAME VARCHAR2(30);
--机房：(PROJECT_NAME 即为工程的ID）
ALTER TABLE RES_JF ADD PROJECT_NAME VARCHAR2(30);
--地下进线室
ALTER TABLE RES_DXJXS ADD PROJECT_NAME VARCHAR2(30);
 


ALTER TABLE RES_BSXX ADD (
        PROJECT_NAME VARCHAR2(30),
		pri_cons_unit VARCHAR2(300),
		other_cons_unit VARCHAR2(300)
		);
		
ALTER TABLE RES_GJXX ADD (        
        PROJECT_NAME VARCHAR2(30),
        CREATEDATE DATE,
        PRI_CONS_UNIT VARCHAR2(300),
        OTHER_CONS_UNIT VARCHAR2(300),
        cons_corp VARCHAR2(300),
        supervision_corp VARCHAR2(300),
        asset_num VARCHAR2(300)
        );
ALTER TABLE RES_DGXX ADD ( 
        PROJECT_NAME VARCHAR2(30),
		pri_cons_unit VARCHAR2(300),
		other_cons_unit VARCHAR2(300),
		asset_num VARCHAR2(300)
);
        
--点图层========================================================================
--注册标石空间表
--sdetable -o register -t RES_BSXX -c OBJECTID -C SDE -u dwuser -p dwuser
--注册标石图层
--sdelayer -o register -l RES_BSXX,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册管井空间表
--sdetable -o register -t RES_GJXX -c OBJECTID -C SDE -u dwuser -p dwuser
--注册管井图层
--sdelayer -o register -l RES_GJXX,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册电杆空间表
--sdetable -o register -t RES_DGXX -c OBJECTID -C SDE -u dwuser -p dwuser
--注册电杆图层
--sdelayer -o register -l RES_DGXX,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册挂墙空间表
--sdetable -o register -t RES_GQXX -c OBJECTID -C SDE -u dwuser -p dwuser
--注册挂墙图层
--sdelayer -o register -l RES_GQXX,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册引上空间表
--sdetable -o register -t RES_YS -c OBJECTID -C SDE -u dwuser -p dwuser
--注册引上图层
--sdelayer -o register -l RES_YS,SHAPE -e p -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--线段图层========================================================================
--注册标石段空间表
--sdetable -o register -t RES_BSD -c OBJECTID -C SDE -u dwuser -p dwuser
--注册标石段图层
--sdelayer -o register -l RES_BSD,SHAPE -e l -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册管道段空间表
--sdetable -o register -t RES_GDD -c OBJECTID -C SDE -u dwuser -p dwuser
--注册管道段图层
--sdelayer -o register -l RES_GDD,SHAPE -e l -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册杆路段空间表
--sdetable -o register -t RES_GLD -c OBJECTID -C SDE -u dwuser -p dwuser
--注册杆路段图层
--sdelayer -o register -l RES_GLD,SHAPE -e l -t ST_GEOMETRY -R 5 -u dwuser -p dwuser

--注册挂墙段空间表
--sdetable -o register -t RES_GQD -c OBJECTID -C SDE -u dwuser -p dwuser
--注册挂墙段图层
--sdelayer -o register -l RES_GQD,SHAPE -e l -t ST_GEOMETRY -R 5 -u dwuser -p dwuser
   
--清除测试用的RFID的扫描记录
delete WPLAN_RESOURCECHECK where RFID='BEF37AE3D40804006263646566676869' or RFID='1EC36AE3540804006263646566676869';
   
CREATE OR REPLACE FUNCTION STR_TO_GEO_POINT(gps varchar2) RETURN MDSYS.SDO_GEOMETRY IS
  x number;
  y number;
begin
   x := to_number(substr(gps,9,3))+(to_number(substr(gps,12,6))/600000);
   y := to_number(substr(gps,1,2))+(to_number(substr(gps,3,6))/600000);
   RETURN TO_GEO_POINT(x, y);

END STR_TO_GEO_POINT;
/

CREATE OR REPLACE FUNCTION TO_GEO_POINT(x in number,y in number) RETURN MDSYS.SDO_GEOMETRY IS
  Result MDSYS.SDO_GEOMETRY;
begin

   Result := MDSYS.SDO_GEOMETRY(2001,
                      8307,
                      null,
                      MDSYS.SDO_ELEM_INFO_ARRAY(1, 1, 1),
                      MDSYS.SDO_ORDINATE_ARRAY(x, y));

  return(Result);
END TO_GEO_POINT;
/

CREATE OR REPLACE FUNCTION COORD_TO_LON(gps varchar2) RETURN number IS
begin
   return to_number(substr(gps,9,3))+(to_number(substr(gps,12,6))/600000);
END COORD_TO_LON;
/

CREATE OR REPLACE FUNCTION COORD_TO_LAT(gps varchar2) RETURN number IS
begin   
   return to_number(substr(gps,1,2))+(to_number(substr(gps,3,6))/600000);
END COORD_TO_LAT;
/
--查询在线车辆
select c.carno,m.simid,m.activetime,coord_to_lon(m.coordinate) lon,coord_to_lat(m.coordinate) lat 
from onlineman m,car_info c 
where businesstype='C35' and m.activetime>sysdate-100 and m.simid=c.simid;

--所有资源索引表--用于查询
CREATE TABLE RES_ALL_INDEX
(
  KEYID              VARCHAR2(64 BYTE) NOT NULL PRIMARY KEY,
  RES_TYPE           VARCHAR2(16 BYTE)          NOT NULL,
  RES_ID             VARCHAR2(64 BYTE)          NOT NULL,
  RES_THIRDPARTY_ID  VARCHAR2(64 BYTE),
  RES_NAME           VARCHAR2(512 BYTE)         NOT NULL,
  REGION_ID          VARCHAR2(8 BYTE),
  ADDRESS            VARCHAR2(512 BYTE),
  RFID               VARCHAR2(128 BYTE),
  BARCODE            VARCHAR2(128 BYTE),
  CREATEDATE         DATE      DEFAULT SYSDATE               NOT NULL,
  SPATIAL_TYPE       INTEGER   DEFAULT -1                    NOT NULL,   --空间类型：1 点 
  LON                FLOAT(126),
  LAT                FLOAT(126)
);

--所有资源空间表--用于查询
CREATE TABLE RES_ALL_SPATIAL
(
  KEYID  VARCHAR2(64 BYTE)                      NOT NULL PRIMARY KEY,
  SHAPE  MDSYS.SDO_GEOMETRY
);

--创建空间表的元数据
--中国的经纬度范围大约为：纬度3.86~53.55，经度73.66~135.05
INSERT INTO USER_SDO_GEOM_METADATA 
	VALUES (
	'RES_ALL_SPATIAL',
	'shape',
	MDSYS.SDO_DIM_ARRAY(  
	MDSYS.SDO_DIM_ELEMENT('X', 73.6, 135.1, 0.005),
	MDSYS.SDO_DIM_ELEMENT('Y', 3.8, 53.6, 0.005)
	),
	'8307'  
);

--删除空间索引
--drop index idx_res_all_spatial_shape;
--创建空间索引
CREATE INDEX idx_res_all_spatial_shape
ON RES_ALL_SPATIAL(shape)
INDEXTYPE IS MDSYS.SPATIAL_INDEX;


--创建同义词
Create public synonym SEQ_ANNEX_ADD_ONE_ID for SEQ_ANNEX_ADD_ONE_ID@base;

Create public synonym mm_taskinfo for mm_taskinfo@base;

Create public synonym FILEPATHINFO for FILEPATHINFO@base;
Create public synonym view_org for view_org@base;

Create public synonym view_userinfo for view_userinfo@base;
Create public synonym ANNEX_ADD_ONE for ANNEX_ADD_ONE@base;


--res_all_index 数据初始化
--导入机房数据
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'AD701',xtbh,zymc,regionid,szjzw,ewm,createdate,'1',sszd from res_jf where zymc is not null;

--导入站点数据
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,lon,lat,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A29',xtbh,zymc,regionid,dlmc,ewm,createdate,'1',lon,lat,'' from res_zdxx where zymc is not null and createdate is not null;

--导入铁塔数据
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A25',tower_id,tower_name,'','','',build_date,'1',site_id from res_tower where tower_name is not null and build_date is not null;

--天线
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A39',id,name,region_id,'','',install_time,'1',tower_id from res_antenna where install_time is not null and name is not null;

--BSC
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A44',id,name,'','','',create_date,'1',room_id from res_bsc where name is not null and create_date is not null;

--机柜
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A42',id,name,'','','',sysdate,'1',room_id from res_cabinet where name is not null;


--板卡
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A47',id,name,'','','',sysdate,'1',GROOVE_ID from res_card where name is not null;


--cell
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A40',id,name,region_id,'','',OPEN_TIME,'1',bts_ID from res_cell where name is not null and open_time is not null;


--机框
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A45',id,name,'','','',sysdate,'1',cabinet_ID from res_frame where name is not null;


--机槽
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A46',id,name,'','','',sysdate,'1',frame_ID from res_groove where name is not null;


--直放站
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A27',REPEATER_ID,REPEATER_NAME,'','','',OPENING_TIME,'1',SITE_ID from res_repeater where REPEATER_NAME is not null and OPENING_TIME is not null;


--RNC
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A43',id,name,'','','',create_date,'1',room_id from res_rnc where name is not null and create_date is not null;

--端口
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A48',id,name,'','','',create_date,'1',CARD_ID from res_port where name is not null and create_date is not null;

--UtranCell
insert into res_all_index(keyid,res_type,res_id,res_name,region_id,address,barcode,createdate,spatial_type,parent_id) select to_char(seq_res_all_index_id.nextval,'FM000000000000'),'A41',id,name,'','','',open_time,'1',room_id from res_UtranCell where name is not null and open_time is not null;
--NODEB
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type, parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A37',  node_id,  ne_name,  region_id,  '',  '',  opening_time,  '1',  room_id FROM res_nodeb  WHERE ne_name IS NOT NULL AND opening_time IS NOT NULL;

--btssite
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type, parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A38',  btssite_id,  ne_name,  region_id,  '',  '',  opening_time,  '1',  site_id FROM res_btssite  WHERE ne_name IS NOT NULL and opening_time is not null ;
--管井
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A21',  xtbh,  zymc,  regionid,  dlmc,  ewm,  createdate,  '1',lon,lat FROM res_gjxx  WHERE zymc IS NOT NULL and createdate is not null ;
--电杆
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A20',  xtbh,  zymc,  regionid,  dlmc,  ewm,  createdate,  '1',lon,lat FROM res_dgxx  WHERE zymc IS NOT NULL and createdate is not null ;
--标石
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A22',  xtbh,  zymc,  regionid,  dlmc,  ewm,  createdate,  '1',lon,lat FROM res_bsxx  WHERE zymc IS NOT NULL and createdate is not null ;
--挂墙
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A23',  xtbh,  zymc,  regionid,  '',  ewm,  createdate,  '1',lon,lat FROM res_gqxx  WHERE zymc IS NOT NULL and createdate is not null ;
--机房
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD701',  xtbh,  zymc,  regionid,  '',  ewm,  createdate,  '1',sszd FROM res_jf  WHERE zymc IS NOT NULL and createdate is not null ;
--站点
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A29',  xtbh,  zymc,  regionid,  '',  ewm,  createdate,  '1','',lon,lat FROM res_zdxx  WHERE zymc IS NOT NULL and createdate is not null ;
--添加spatial
insert into   RES_ALL_SPATIAL (keyid,shape) select keyid,"MDSYS"."SDO_GEOMETRY"(2001,8307,"MDSYS"."SDO_POINT_TYPE"(lon,lat,NULL),NULL,NULL) from res_all_index where lon is not null ;

--20131029
--伪线
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A100',  id,  name, regionid,  '',  '',  sysdate,  '1','' FROM RES_TI_GUISEWIRE  WHERE name IS NOT NULL;
--分光器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A101',  id,  name,  regionid,  '',  '',  createdate,  '1',olt_id FROM RES_TI_OPICALSPLIT  WHERE name IS NOT NULL and parent_id is null;
--波道

INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A102',  id,  name,  regionid,  '',  '',  sysdate,  '1','' FROM RES_TI_WAVES  WHERE name IS NOT NULL;

--隧道
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A103',  id,  name,  regionid,  '',  '',  sysdate,  '1','' FROM RES_TI_TUNNEL  WHERE name IS NOT NULL;

--资源点
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A104',  id,  name,   regionid,'',   address,  createdate,  '1','',lon,lat FROM RES_RSPOINT  WHERE name IS NOT NULL;

--集团客户
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A34',  xtbh,  zymc,  regionid,  groupaddr,  '',  createtime,  '1','',lon,lat FROM res_groupcustomer  WHERE zymc IS NOT NULL;

--校园网
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A34',  xtbh,  zymc,  regionid,  camaddr,  '',  createtime,  '1','',lon,lat FROM res_campus  WHERE zymc IS NOT NULL;

--宽带小区
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A35',  xtbh,  zymc,  regionid,  commaddr,  '',  createtime,  '1','',lon,lat FROM res_broadcommunty  WHERE zymc IS NOT NULL;

--营业厅
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A36',  xtbh,  zymc,  regionid,  yytdz,  '',  createtime,  '1','',lon,lat FROM res_yyt  WHERE zymc IS NOT NULL;

--AC
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A50',  id,  name,  region_id,'',  '',  sysdate,  '1','' FROM RES_AC  WHERE name IS NOT NULL;

--AP
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A51',  ap_id,  ap_name,  region_id,'',  '',  sysdate,  '1','' FROM RES_AP  WHERE ap_name IS NOT NULL;

--热点
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A52',  id,  host_name,  region_id,'',  '',  sysdate,  '1','',lon,lat FROM RES_WLAN  WHERE host_name IS NOT NULL;

--交换机
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A53',  id,  name,  region_id,'',  '',  sysdate,  '1','' FROM RES_SWITCH  WHERE name IS NOT NULL;

--自动电源切换屏
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A54',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_AUTOSWITCH_PANEL  WHERE name IS NOT NULL;

--中央空调主机
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A55',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_CENAIR_HOST  WHERE name IS NOT NULL;

--中央空调系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A56',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_CENAIR_SYS  WHERE name IS NOT NULL;

--交流配电柜
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A57',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_EXHELE_CAB  WHERE name IS NOT NULL;

--高压配电柜
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A58',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_HIGHELE_CAB  WHERE name IS NOT NULL;

--逆变设备
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A59',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_INVERSION_EQU  WHERE name IS NOT NULL;

--低压配电柜
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A60',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_LOWELE_CAB  WHERE name IS NOT NULL;

--稳压器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A61',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_MANOSTAT  WHERE name IS NOT NULL;

--移动发电机
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A62',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_MOBELEC  WHERE name IS NOT NULL;

--直流配电柜
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A64',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_VERHELE_CAB  WHERE name IS NOT NULL;

--蓄电池组
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A65',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_BATTERY_SET  WHERE name IS NOT NULL;

--电池恒温箱
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A66',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_BATTERY_THERMOSTAT  WHERE name IS NOT NULL;

--普通空调
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A67',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_COMMON_AIR_COND  WHERE name IS NOT NULL;

--机楼基站外电
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A68',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_EXT_POWER  WHERE name IS NOT NULL;

--发电机组
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A69',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_GENERATING_SET  WHERE name IS NOT NULL;

--换热系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A70',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_HEAT_EXC_SYS  WHERE name IS NOT NULL;

--动环监控设备
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A71',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_POWER_WUPERV_EQU  WHERE name IS NOT NULL;

--太阳能控制器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A72',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_SOLAR_CON  WHERE name IS NOT NULL;

--太阳能设备
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A73',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_SOLAR_EQU  WHERE name IS NOT NULL;

--专用空调
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A74',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_SPE_AIR_COND  WHERE name IS NOT NULL;

--开关电源
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A75',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_SWICH_SOURCE  WHERE name IS NOT NULL;

--变压器或调压器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A76',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_TRANSFORMER  WHERE name IS NOT NULL;

--UPS
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A77',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_UPS  WHERE name IS NOT NULL;

--通风系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A78',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_VENTILATION_SYS  WHERE name IS NOT NULL;

--风能设备
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A79',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_WIND_ENERGY_EQU  WHERE name IS NOT NULL;

--风电控制器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A80',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_WIND_POWER_CON_EQU  WHERE name IS NOT NULL;

--风光互补控制器
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A81',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_WS_COMPL_CON_EQU  WHERE name IS NOT NULL;

--动环专业内输出分路
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A82',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_RING_OUTBRANCH  WHERE name IS NOT NULL;

--维护仪器仪表
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A83',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_INSTRUMENT_PRE  WHERE name IS NOT NULL;

--跨专业输出分路
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A84',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_INTERDISCIP_OUTBRANCH  WHERE name IS NOT NULL;

--其他设备
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A85',  id,  name,  region_id,'',  '',  sysdate,  '1',room_id FROM RES_PW_OTHER_EQU  WHERE name IS NOT NULL;

--单板
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A86',  id,  BOARDNAME,  region_id,'',  '',  sysdate,  '1',transnetwork FROM RES_SINGLEBOARD  WHERE BOARDNAME IS NOT NULL;

--端口
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A87',  id,  boardname,  region_id,'',  '',  sysdate,  '1',board_id FROM RES_BOARD_PORT  WHERE boardname IS NOT NULL;

--DDM模块
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A88',  id,  ddmname,  region_id,'',  '',  sysdate,  '1','' FROM RES_DDM_MO  WHERE ddmname IS NOT NULL;

--DDF端子
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A90',  id,  ddmtermin_name,  region_id,'',  '',  sysdate,  '1','' FROM RES_DDF_TER  WHERE ddmtermin_name IS NOT NULL;

--分光器端口
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A92',  id,  port_name,  region_id,'',  '',  sysdate,  '1',opticl_id FROM RES_OPTICL_PORT  WHERE port_name IS NOT NULL;

--EMS
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A93',  id,  name,  regionid,'',  '',  sysdate,  '1','' FROM RES_TI_EMS  WHERE name IS NOT NULL;

--OLT
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A94',  id,  name,  regionid,'',  '',  createdate,  '1',host_obj_id FROM RES_TI_OLT  WHERE name IS NOT NULL;

--ONU
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A95',  id,  name,  regionid,'',  '',  createdate,  '1',opicalsplit_id FROM RES_TI_ONU  WHERE name IS NOT NULL;

--SDH通道
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A96',  id,  name,  regionid,'',  '',  sysdate,  '1','' FROM RES_TI_SDH  WHERE name IS NOT NULL;

--传输子网
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A97',  id,  name,  regionid,'',  '',  sysdate,  '1','' FROM RES_TI_SUBNET  WHERE name IS NOT NULL;

--传输电路
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A98',  id,  name,  regionid,'',  '',  sysdate,  '1','' FROM RES_TI_ELECLINE  WHERE name IS NOT NULL;

--传输网元
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id, address, barcode, createdate, spatial_type,parent_id) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A99',  id,  name,  regionid,'',  '',  sysdate,  '1',subnet_id FROM RES_TI_ELEMENT  WHERE name IS NOT NULL;
--标石系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD608', xtbh,zymc,  regionid, '1' FROM RES_BSD_XT  WHERE ZYMC IS NOT NULL;
--挂墙系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD609', xtbh,zymc,  regionid, '1' FROM RES_GQD_XT  WHERE ZYMC IS NOT NULL;
--管道系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD606', xtbh,zymc,  regionid, '1' FROM RES_GDD_XT  WHERE ZYMC IS NOT NULL;
--杆路系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD607', xtbh,zymc,  regionid, '1' FROM RES_GLD_XT  WHERE ZYMC IS NOT NULL;
--引上系统
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD6010', xtbh,zymc,  regionid, '1' FROM RES_YS_XT  WHERE ZYMC IS NOT NULL;
--光缆盘留
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD706', xtbh,zymc,  regionid, '1',lon,lat FROM RES_GLPL  WHERE ZYMC IS NOT NULL;
--光缆段路由
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'A33', xtbh,zymc,  regionid, '1',createdate FROM RES_GLD_LY  WHERE ZYMC IS NOT NULL;
--光分纤箱
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AA004', xtbh,zymc,  regionid, '1',createdate,lon,lat FROM res_gfxx  WHERE ZYMC IS NOT NULL;
--光交接箱
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AA003', xtbh,zymc,  regionid, '1',createdate,lon,lat FROM res_gjjx  WHERE ZYMC IS NOT NULL;
--光终端箱
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AA006', xtbh,zymc,  regionid, '1',createdate,lon,lat FROM res_gzdh  WHERE ZYMC IS NOT NULL;
--光接头
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate,lon,lat) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AA005', xtbh,zymc,  regionid, '1',createdate,lon,lat FROM res_gjt  WHERE ZYMC IS NOT NULL;
--odf
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AA001', xtbh,zymc,  regionid, '1',createdate FROM res_odf  WHERE ZYMC IS NOT NULL;
--光缆
INSERT INTO res_all_index (keyid, res_type, res_id, res_name, region_id,spatial_type,createdate) SELECT TO_CHAR (seq_res_all_index_id.NEXTVAL, 'FM000000000000'),  'AD6011', xtbh,zymc,  regionid, '1',createdate FROM res_glxx  WHERE ZYMC IS NOT NULL;

--添加spatial
insert into   RES_ALL_SPATIAL (keyid,shape) select keyid,"MDSYS"."SDO_GEOMETRY"(2001,8307,"MDSYS"."SDO_POINT_TYPE"(lon,lat,NULL),NULL,NULL) from res_all_index where lon is not null ;

commit;

------------------------------------------
ALTER TABLE POINTINFO
 ADD (POINT_NUMBER  VARCHAR2(128));

ALTER TABLE POINTINFO
 ADD (IRMS_ID  VARCHAR2(128));

ALTER TABLE POINTINFO
 ADD (RFID  VARCHAR2(500));

ALTER TABLE POINTINFO
 ADD (BUSINESS_TYPE  VARCHAR2(32));

ALTER TABLE POINTINFO
 ADD (MAINTENANCE_TYPE  VARCHAR2(32));

ALTER TABLE POINTINFO
 ADD (BUSINESS_GRADE  VARCHAR2(32));

ALTER TABLE POINTINFO
 ADD (SUBLINE_NAME  VARCHAR2(500));

ALTER TABLE POINTINFO
 ADD (REGIONNAME  VARCHAR2(200));

ALTER TABLE POINTINFO
 ADD (CONTRACTOR_NAME  VARCHAR2(200));
 -------------------------------------------------
ALTER TABLE WPLAN_PATROLSUBITEM
ADD (WANT_PHOTOS VARCHAR2(3 BYTE));
 -------------------------------------------------
ALTER TABLE WPLAN_RESOURCECHECK
ADD (REMARK VARCHAR2(500 BYTE));
 -------------------------------------------------
ALTER TABLE RS_PATROLADDRESSINFO
 ADD (EQU_TYPE  VARCHAR2(12));

ALTER TABLE RS_PATROLADDRESSINFO
 ADD (EQU_ID  VARCHAR2(36));
 
ALTER TABLE RS_PATROLADDRESSINFO
 ADD (DATA_TYPE  VARCHAR2(8) DEFAULT 'QRCODE' NOT NULL);
 -------------------------------------------

--添加排序字段
ALTER TABLE RES_ALL_INDEX
 ADD (res_sort_num  VARCHAR2(12 BYTE));
--res_all_index 添加索引 
CREATE INDEX RES_ALL_INDEX_RES_NAME ON RES_ALL_INDEX(RES_NAME);
CREATE INDEX RES_ALL_INDEX_RES_TYPE ON RES_ALL_INDEX(RES_TYPE);
CREATE INDEX RES_ALL_INDEX_RES_ID ON RES_ALL_INDEX(RES_ID);
CREATE INDEX RES_ALL_INDEX_PARENT_ID ON RES_ALL_INDEX(PARENT_ID);
CREATE INDEX RES_ALL_INDEX_RES_SORT_NUM ON RES_ALL_INDEX(RES_SORT_NUM);
create index IDX_INSPECTCOUNT on RES_ALL_INDEX (inspectcount);


--添加片区
insert into res_all_index(keyid,res_type,res_id,res_name)  select seq_res_all_index_id.nextval,'A108',codevalue,lable  from base_sysdictionary@base where columntype='XASSGLQ' and status is null;
