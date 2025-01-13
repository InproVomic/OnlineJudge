DROP TABLE IF EXISTS  'tb_sys_user';

create table tb_sys_user(
    user_id bigint unsigned primary key comment '用户ID(主键)',
    user_account varchar(20) not null comment '用户账号',
    password varchar(20) not null comment '用户密码',
    create_time datetime not null comment '创建时间',
    create_by bigint unsigned not null comment '创建人ID',
    update_time datetime not null comment '更新时间',
    update_by bigint unsigned not null comment '更新人ID',
    unique key `idx_user_account`(`user_account`)
);