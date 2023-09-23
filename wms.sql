create database WMS;

use WMS;
# 人员档案表
create table User_File
(
    u_id        int PRIMARY key auto_increment,                            #人员id
    u_account   varchar(20),                                               #人员账号
    u_pwd       varchar(20),                                               #账号密码
    u_name      varchar(20),                                               #人员名
    sex         varchar(1) check (sex = '男' or sex = '女'),               #性别
    birthdate   date,                                                      #出生日期
    id_number   VARCHAR(25) check (id_number REGEXP '^[0-9]{17}[0-9Xx]$'), #身份证号
    nationality VARCHAR(150),                                              #籍贯
    address     VARCHAR(300),                                              #家庭地址
    phone       VARCHAR(15),                                               #电话号
    is_delete   int                                                        #是否被删除
);
# # 用户权限表
# create table User_Power(
#                           up_id int PRIMARY KEY auto_increment,	#权限id
#                                 u_id int,
#                           power VARCHAR(100)
# );

# 物料表
create table Material_File
(
    m_id          int PRIMARY key auto_increment, #物料id
    m_name        VARCHAR(250),                   #物料名
    specification VARCHAR(300),                   #物料规格
    unit          VARCHAR(10),                    #物料单位
    m_number      int DEFAULT 0,                  #物料数量
    m_ps          VARCHAR(500)                    #物料备注
);

# 物料进出表
create table Material_INOUT
(
    MIO_id     int PRIMARY KEY auto_increment, #物料进出表id
    MIO_date   date,                           #日期
    u_id       int,                            #人员id
    MIO_ps     VARCHAR(500),                   #备注
    m_id       int,                            #物料id
    IN_number  int,                            #进仓数量
    OUT_number int                             #出仓数量  ## -1表示该字段无意义
);


# 物料(已存在物料)入库(人员id，物料id，入库数量，备注)
delimiter \\
create procedure old_Material_in(uid int, mid int, mnumber int, MIOps VARCHAR(500))
BEGIN
    declare mname varchar(250) default null;
    declare uname varchar(20) default null;
    select u_name into uname from User_File where u_id = uid;
    if uname is null THEN
        signal SQLSTATE 'HY000' set message_text = '操作人员不存在！';
    end if;

    select m_name into mname from material_File where m_id = mid;
    if mname is null THEN
        signal SQLSTATE 'HY000' set message_text = '要入库的物料不存在！';
    end if;
    INSERT into Material_INOUT values (null, now(), uid, MIOps, mid, mnumber, -1);
    update material_File set m_number=m_number + mnumber where m_id = mid;
end \\
delimiter ;
# drop procedure old_Material_in;


# 物料(新物料)入库(人员id，物料名，入库数量，物料单位，物料规格，备注，物料备注)
delimiter \\
create procedure new_Material_in(uid int, mname VARCHAR(250), mnumber int, unit VARCHAR(10),
                                 s_pecification VARCHAR(300), MIOps VARCHAR(500), mps VARCHAR(500))
BEGIN
    declare uname varchar(20) default null;
    declare mid int default -1;
    select u_name into uname from User_File where u_id = uid;
    if uname is null THEN
        signal SQLSTATE 'HY000' set message_text = '操作人员不存在！';
    end if;
    insert into material_File values (null, mname, s_pecification, unit, mnumber, mps);
    select m_id into mid from material_File where mname = m_name;
    INSERT into Material_INOUT values (null, now(), uid, MIOps, mid, mnumber, -1);
end \\
delimiter ;
# drop procedure new_Material_in;


# 物料出库(人员id，物料id，出库数量，备注)
delimiter \\
create procedure Material_out(uid int, mid int, mnumber int, MIOps VARCHAR(500))
BEGIN
    declare mname varchar(250) default null;
    declare uname varchar(20) default null;
    declare num int default 0;
    select u_name into uname from User_File where u_id = uid;
    if uname is null THEN
        signal SQLSTATE 'HY000' set message_text = '操作人员不存在！';
    end if;

    select m_name into mname from material_File where m_id = mid;
    if mname is null THEN
        signal SQLSTATE 'HY000' set message_text = '要入库的物料不存在！';
    end if;
    select m_number into num from material_File where m_id = mid;
    if num < mnumber THEN
        signal SQLSTATE 'HY000' set message_text = '仓库的数量不足！';
    end if;
    INSERT into Material_INOUT values (null, now(), uid, MIOps, mid, -1, mnumber);
    update material_File set m_number=m_number - mnumber where m_id = mid;
end \\
delimiter ;
# drop procedure Material_out;


# call new_Material_in(11,'电脑',50,'台','10kg','新产品入库','笔记本电脑，联想品牌');
#
# call Material_out(11,1,91,'出库11台电脑');
