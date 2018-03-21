X12设备采集通信协议代码svn地址：
svn://www.cabletech.com.cn/xgxsource/source/CT_GUIZHOU_RS/CTCJSMProj

资源管理Android客户端项目
svn://www.cabletech.com.cn/xgxsource/source/android/ResourceManagerSystem/trunk
资源管理Android客户端及通讯服务器端公用模块
svn://www.cabletech.com.cn/xgxsource/source/CommunicationServers/cabletech-rms-model/trunk
资源管理通讯服务器项目
svn://www.cabletech.com.cn/xgxsource/source/CommunicationServers/cabletech-rms-proxy/trunk


/* Formatted on 2012/7/14 0:33:29 (QP5 v5.215.12089.38647) */
--效率低，不走空间索引
SELECT XTBH,ZYMC,X,Y
  FROM RES_ZDXX
 WHERE SDE.st_contains (
          SDE.st_polygon (
             'polygon ((310014 3793314, 305014 3793314, 305014 3792314, 310014 3792314, 310014 3793314))',
             2),
          shape)=1 ;
          
--搜索polygon范围内的点          
SELECT XTBH,ZYMC,SDE.ST_X(SHAPE) STX ,SDE.ST_Y(SHAPE) as STY  
  FROM RES_ZDXX
 WHERE sde.st_within (
          shape,
          SDE.st_polygon (
             'polygon ((310014 3793314, 305014 3793314, 305014 3792314, 310014 3792314, 310014 3793314))',
             2))=1 ;
--搜索以中心点为中心，指定半径范围内的点             
SELECT XTBH,ZYMC,SDE.ST_X(SHAPE) STX ,SDE.ST_Y(SHAPE) as STY  
  FROM RES_ZDXX
 WHERE sde.st_within (
          shape,
          sde.st_buffer(sde.st_point (305882, 3792338, 2),500))=1 ;
          
--搜索以中心点为中心，指定半径范围内的点，并显示与中心点的距离，结果以距离排序
SELECT XTBH,ZYMC,SDE.ST_X(SHAPE) STX ,SDE.ST_Y(SHAPE) as STY,sde.st_distance(shape, sde.st_point (305882, 3792338, 2)) distance  
  FROM RES_ZDXX
 WHERE sde.st_within (
          shape,
          sde.st_buffer(sde.st_point (305882, 3792338, 2),500))=1 
 order by distance;
          
                    
--效率高，走空间索引          
SELECT XTBH,ZYMC,LON,LAT
  FROM RES_ZDXX
 WHERE sde.st_within (
          shape,
          SDE.st_polygon (
             'polygon ((310014 3793314, 305014 3793314, 305014 3792314, 310014 3792314, 310014 3793314))',
             2))=1 ;
             
-----------------------------------------------
查询基站
http://192.168.1.179:8399/arcgis/rest/services/shanxi_zy/MapServer/0/query?geometry=305882.3744,3792338.0172,306067.358,3792229.3855&geometryType=esriGeometryEnvelope&f=json

--第一步：首先预制两个智能终端的角色，写入BASE_ROLE表两条记录
SET DEFINE OFF;
Insert into BASE_ROLE
   (ID, ROLENAME, REMARK, STATUS)
 Values
   ('000000127920', '管理终端', '移动管理人员', '7');
   
Insert into BASE_ROLE
   (ID, ROLENAME, REMARK, STATUS)
 Values
   ('000000127921', '维护终端', '代维维护人员', '7');
COMMIT;

--第二步：向BASE_MENU写入智能终端所有的菜单项(对应Android主功能界面的各个图标)
SET DEFINE OFF;
Insert into BASE_MENU
   (ID, TEXT, PARENTID, LEAF, ORDERNUM)
 Values
   ('000000123162', '终端应用', 'root', '0', 0);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000123163', '工单管理', 'RMS_ROLE_WORK_ORDER', '000000123162', '1', 
    1);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000123164', '巡检任务', 'RMS_ROLE_PATROL_TASK', '000000123162', '1', 
    2);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000123165', '隐患上报', 'RMS_ROLE_HIDDEN_REPORT', '000000123162', '1', 
    3);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000123166', '通讯录', 'RMS_ROLE_ADDRESS_BOOK', '000000123162', '1', 
    4);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000123167', '地图', 'RMS_ROLE_MAP', '000000123162', '1', 
    7);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000127915', '现场检查', 'RMS_ROLE_ON_THE_SPOT_CHECK', '000000123162', '1', 
    5);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000127916', '系统公告', 'RMS_ROLE_NOTICE_RECEPTION', '000000123162', '1', 
    0);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000127917', '数据查看', 'RMS_ROLE_DATA_VIEW', '000000123162', '1', 
    6);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000127918', '车辆申请', 'RMS_ROLE_APPLY_FOR_CAR', '000000123162', '1', 
    5);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000127919', '工具', 'RMS_ROLE_TOOLS', '000000123162', '1', 
    7);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000128742', '终端签到功能', 'RMS_ROLE_SIGNIN', '000000123162', '1', 
    0);
Insert into BASE_MENU
   (ID, TEXT, HREFURL, PARENTID, LEAF, 
    ORDERNUM)
 Values
   ('000000128743', '投诉工单处理', 'RMS_ROLE_PROBLEM_REPORT', '000000123162', '1', 
    0);
COMMIT;

--第三步：(数据库操作)因为目前通用权限功能还不支持智能终端角色菜单的分配，在BASE_ROLE表中中为'7'的显示不出来，
--所以要先将角色表中的"维护终端"角色的状态置为null，伪装为Web页面的角色。
update BASE_ROLE set STATUS=null WHERE ID='000000127921';
--第四步：(WEB操作)在通用权限管理模块中为该角色（"维护终端"）分配以上的各个菜单项
--第五步：(WEB操作)在通用权限管理模块中为某个智能终端用户分配"维护终端"的角色


--第六步：(数据库操作)将角色表中的“维护终端”角色的状态恢复为'7'，表示是智能终端的角色，
update BASE_ROLE set STATUS='7' WHERE ID='000000127921';

--最后一步：
用上面配置的用户登录Android终端，确认写分配的菜单项一至，修改该用户的权限时，重复第三步至最后一步.


本方法是因为通用权限模块还没有支持终端角色分配而采取的临时方案，供测试时临时使用，盼通用权限尽早实现在界面上分配权限的功能。
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
为了响应西安的需求，大部份资源增加所属工程ID字段,涉及到的修改包括以下资源

注意：以下字段，在加载数据时，下发的是工程ID，如果界面要显示工程名，需要再发一次0x0225请求(type="PROJECT",返回0223指令，其中的entity属性为为entity<Item>)，来加载工程名
管井              ResGjxx.projectname 
电杆              ResDgxx.projectname
标石              ResBsxx.projectname
拐点(挂墙)        ResGqxx.projectname
引上点            ResYsxx.projectname

管道段            ResGdd.projectname
杆路段            ResGld.projectname
直埋段（标石段）  ResBsd.projectname
挂墙段            ResGqd.projectname

站点              ResZdxx.projectname
机房              ResJf.projectname
地下进线室采集    ResDxjxs.projectname
ODF               ResOdf.projectname
ODM               ResOdm.projectname
光接头            ResGjt.ssgc
光交接箱          ResGjjx.ssgc
光分纤箱          ResGfxx.projectname
光终端盒          ResGzdh.ssgc

上传时，以上字段也指工程ID
－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－





