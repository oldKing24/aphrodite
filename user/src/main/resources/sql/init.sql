CREATE TABLE `user`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(20)  DEFAULT NULL COMMENT '姓名',
    `password`    varchar(100) DEFAULT NULL COMMENT '密码',
    `sex`         varchar(2)   DEFAULT NULL COMMENT '性别 0-男，1-女',
    `email`       varchar(20)  DEFAULT NULL COMMENT '邮箱',
    `mobile`      varchar(20)  DEFAULT NULL COMMENT '手机号',
    `status`      varchar(2)   DEFAULT NULL COMMENT '状态 0-有效 1-失效',
    `type`        varchar(2)   DEFAULT NULL COMMENT '类型 0-学生 1-教师',
    `image`       varchar(100) DEFAULT NULL COMMENT '头像',
    `summary`     text         DEFAULT NULL COMMENT '介绍',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO `user`
(id, name, password, sex, email, mobile, status, `type`, image, summary, create_time, update_time)
VALUES (0, 'admin', '123456', NULL, NULL, NULL, '0', '1', NULL, NULL, '2023-02-24 16:38:08', '2023-02-24 16:38:08');


CREATE TABLE `role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(20) DEFAULT NULL COMMENT '姓名',
    `status`      varchar(2)  DEFAULT NULL COMMENT '状态 0-启用 1-禁用',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

INSERT INTO `role`
    (id, name, status, create_time, update_time)
VALUES (0, '系统管理员', '0', '2023-02-24 00:00:00', '2023-02-24 00:00:00');
INSERT INTO `role`
    (id, name, status, create_time, update_time)
VALUES (1, '学生', '0', '2023-02-24 00:00:00', '2023-02-24 00:00:00');


CREATE TABLE `user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
    `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY uk_user_role(`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

CREATE TABLE `permission`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(20) DEFAULT NULL COMMENT '姓名',
    `code`        varchar(32) DEFAULT NULL COMMENT '编码',
    `status`      varchar(2)  DEFAULT NULL COMMENT '状态 0-启用 1-禁用',
    `type`        varchar(2)  DEFAULT NULL COMMENT '种类 0-菜单 1-按钮',
    `create_time` datetime    DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

CREATE TABLE `role_permission`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
    `role_id`       bigint(20) DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY role_permission(`permission_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

CREATE TABLE `course`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name`        varchar(20)   DEFAULT NULL COMMENT '课程名称',
    `content`     varchar(1000) DEFAULT NULL COMMENT '课程内容',
    `type`        varchar(10)   DEFAULT NULL COMMENT '课程形式',
    `start_time`  datetime      DEFAULT NULL COMMENT '开始时间',
    `end_time`    datetime      DEFAULT NULL COMMENT '结束时间',
    `teacher_id`  bigint(20) DEFAULT NULL COMMENT '任课老师id',
    `image`       varchar(100)  DEFAULT NULL COMMENT '封面',
    `url`         varchar(100)  DEFAULT NULL COMMENT '链接',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
    `update_time` datetime      DEFAULT NULL COMMENT '更新时间',
    `update_user` bigint(20) DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

CREATE TABLE `course_student`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `course_id`  bigint(20) NOT NULL COMMENT '课程ID',
    `student_id` bigint(20) NOT NULL COMMENT '学生id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='课程学生关联表';

CREATE TABLE `course_comment`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `course_id`   bigint(20) DEFAULT NULL COMMENT '课程ID',
    `content`     text     DEFAULT NULL COMMENT '评论内容',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
    `is_top`      bit(2)   DEFAULT 0 COMMENT '是否置顶 0-否 1-是',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='课程评论明细表';

CREATE TABLE `config`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`    varchar(100) DEFAULT NULL COMMENT '配置code',
    `content` text DEFAULT NULL COMMENT '配置内容',
    `desc`    varchar(255) DEFAULT NULL COMMENT '描述',
    `type`    varchar(32)  DEFAULT NULL COMMENT '类型',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='配置表';

