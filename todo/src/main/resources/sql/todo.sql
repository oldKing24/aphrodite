CREATE TABLE `category`
(
    `id`         bigint(20)              NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id`   bigint(20)   default -1 NOT NULL COMMENT 'id',
    `name`        varchar(100) DEFAULT NULL COMMENT '名称',
    `icon`        varchar(255) DEFAULT NULL COMMENT '图标',
    `type`        varchar(10)  DEFAULT NULL COMMENT '类型',
    `remark`     varchar(255) DEFAULT NULL COMMENT '备注',
    `create_user` varchar(100) DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_user` varchar(100) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8 COMMENT ='分类';

CREATE TABLE `information`
(
    `id_`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `category_id`   bigint(20) NOT NULL COMMENT 'category_id',
    `title`         varchar(255) DEFAULT NULL COMMENT '标题',
    `summary`       varchar(255) DEFAULT NULL COMMENT '描述',
    `img`           varchar(255) DEFAULT NULL COMMENT '封面图',
    `link_url`      varchar(255) DEFAULT NULL COMMENT '链接地址',
    `comment_count` bigint(20)   DEFAULT 0 COMMENT '评论数',
    `create_user`   varchar(100) DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime     DEFAULT NULL COMMENT '创建时间',
    `update_user`   varchar(100) DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1000
  DEFAULT CHARSET = utf8 COMMENT ='信息表';