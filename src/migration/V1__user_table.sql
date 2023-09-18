create table board
(
    `uuid`         VARCHAR(100)                       NOT NULL COMMENT 'uuid',
    `title`        VARCHAR(400)                       NOT NULL COMMENT '게시글 제목',
    `content`      Text                               NOT NULL COMMENT '게시글 내용',
    `version`      BIGINT                             NOT NULL COMMENT '버전',
    `created_by`   VARCHAR(50)                        NOT NULL COMMENT '생성자',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일',
    `updated_by`   VARCHAR(50)                        COMMENT '수정자',
    `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',

    PRIMARY KEY (uuid)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8MB4;
