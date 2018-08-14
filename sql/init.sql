-- Create table
create table T_USER
(
  id   NUMBER not null,
  name VARCHAR2(1000),
  age  NUMBER
)
;
-- Add comments to the table
comment on table T_USER
  is '用户表';
-- Add comments to the columns
comment on column T_USER.id
  is '主键';
comment on column T_USER.name
  is '用户名';
comment on column T_USER.age
  is '年龄';
-- Create/Recreate primary, unique and foreign key constraints
alter table T_USER
  add constraint T_USER_ID primary key (ID)
  ;

create sequence t_user_s
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cache 20;
