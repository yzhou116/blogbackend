# blogbackend
# myblog
Sql table:
CREATE TABLE `yiblogs`.`tb_user`(  
  `id` VARCHAR(20) NOT NULL ,
  `user_name` VARCHAR(32) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `roles` VARCHAR(100) NOT NULL,
  `avatar` VARCHAR(1024) NOT NULL,
  `email` VARCHAR(100),
  `sign` VARCHAR(100) ,
  `state` VARCHAR(1) NOT NULL COMMENT '状态：0 means delete，1 means normal',
  `reg_ip` VARCHAR(32) NOT NULL COMMENT 'register ip',
  `login_ip` VARCHAR(32) NOT NULL COMMENT 'log in Ip',
  `create_time` DATETIME NOT NULL COMMENT 'create time',
  `update_time` DATETIME NOT NULL COMMENT 'update time',
  PRIMARY KEY (`id`)stb_usertb_usertb_usert_user
) ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_images` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `user_id` varchar(20) NOT NULL COMMENT 'user id',
  `url` varchar(1024) NOT NULL COMMENT 'image url',
  `state` varchar(1) NOT NULL COMMENT '1 means delete 0 means normal',
  `create_time` datetime NOT NULL COMMENT 'creat time',
  `update_time` datetime NOT NULL COMMENT 'update time',
  PRIMARY KEY (`id`),
  KEY `fk_user_images_on_user_id` (`user_id`),
  CONSTRAINT `fk_user_images_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `yiblogs`.`tb_categories`(  
  `id` VARCHAR(20) NOT NULL COMMENT 'ID',
  `name` VARCHAR(64) NOT NULL ,
  `pinyin` VARCHAR(128) NOT NULL ,
  `description` TEXT NOT NULL COMMENT 'description',
  `order` INT(11) NOT NULL COMMENT 'order',
  `status` VARCHAR(1) NOT NULL COMMENT '0 means no support, 1 means normal',
  `create_time` DATETIME NOT NULL COMMENT 'create time',
  `update_time` DATETIME NOT NULL COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
CREATE TABLE `tb_article` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `title` varchar(256) NOT NULL COMMENT 'title',
  `user_id` varchar(20) NOT NULL COMMENT 'user id',
  `user_avatar` varchar(1024) DEFAULT NULL COMMENT 'user's photo',
  `user_name` varchar(32) DEFAULT NULL COMMENT 'user name',
  `category_id` varchar(20) NOT NULL COMMENT 'article category',
  `content` mediumtext NOT NULL COMMENT 'article content',
  `type` varchar(1) NOT NULL COMMENT '（0 means Rich Text Format，1 means markdown）',
  `state` varchar(1) NOT NULL COMMENT '状态（0 means delete ，1 means publish，2 means draft）',
  `summary` text NOT NULL COMMENT 'summary',
  `labels` varchar(128) NOT NULL COMMENT 'label',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT 'view count',
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_article_on_user_id` (`user_id`),
  KEY `fk_category_article_on_category_id` (`category_id`),
  CONSTRAINT `fk_category_article_on_category_id` FOREIGN KEY (`category_id`) REFERENCES `tb_categories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_article_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_comment` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `parent_content` text COMMENT 'parent content',
  `article_id` varchar(20) NOT NULL COMMENT 'articleID',
  `content` text NOT NULL COMMENT 'comment content',
  `user_id` varchar(20) NOT NULL COMMENT 'comment user ID',
  `user_avatar` varchar(1024) DEFAULT NULL COMMENT 'user photo',
  `user_name` varchar(32) DEFAULT NULL COMMENT 'user name',
  `state` varchar(1) NOT NULL COMMENT '状态（0 means delete，1 means normal）',
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `fk_user_comment_on_user_id` (`user_id`),
  KEY `fk_article_comment_on_article_id` (`article_id`),
  CONSTRAINT `fk_article_comment_on_article_id` FOREIGN KEY (`article_id`) REFERENCES `tb_article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_comment_on_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tb_looper` (
  `id` varchar(20) NOT NULL COMMENT 'ID',
  `title` varchar(128) NOT NULL ,
  `order` int(11) NOT NULL DEFAULT '0' COMMENT 'order',
  `state` varchar(1) NOT NULL COMMENT 'state：0 means delete，1 normal',
  `target_url` varchar(1024) DEFAULT NULL COMMENT 'URL',
  `image_url` varchar(2014) NOT NULL COMMENT 'image address',
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE `yiblogs`.`tb_daily_view_count`(  
  `id` VARCHAR(20) NOT NULL COMMENT 'ID',
  `view_count` INT NOT NULL DEFAULT 0 COMMENT 'day views',
  `create_time` DATETIME NOT NULL ,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `yiblogs`.`tb_labels`(  
  `id` VARCHAR(20) NOT NULL COMMENT 'ID',
  `name` VARCHAR(32) NOT NULL COMMENT 'label name',
  `count` INT NOT NULL DEFAULT 0 COMMENT 'count',
  `create_time` DATETIME NOT NULL ,
  `update_time` DATETIME NOT NULL ,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `yiblogs`.`tb_settings`(  
  `id` VARCHAR(20) NOT NULL COMMENT 'ID',
  `key` VARCHAR(32) NOT NULL COMMENT 'key',
  `value` VARCHAR(512) NOT NULL COMMENT 'value',
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `yiblogs`.`tb_friends`(  
  `id` VARCHAR(20) NOT NULL COMMENT 'ID',
  `name` VARCHAR(64) NOT NULL COMMENT 'friend's name',
  `logo` VARCHAR(1024) NOT NULL COMMENT 'friend'slogo',
  `url` VARCHAR(1024) NOT NULL COMMENT 'friend's url',
  `order` INT(11) NOT NULL DEFAULT 0 COMMENT 'order',
  `state` VARCHAR(1) NOT NULL COMMENT 'state:0 means no support ，1 normal',
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL ,
  PRIMARY KEY (`id`)
)
