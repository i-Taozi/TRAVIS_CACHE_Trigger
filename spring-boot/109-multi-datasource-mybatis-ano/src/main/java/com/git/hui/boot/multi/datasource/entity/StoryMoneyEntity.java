package com.git.hui.boot.multi.datasource.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author yihui
 * @date 20/12/27
 */
@Data
public class StoryMoneyEntity {
    private Integer id;

    private String name;

    private Long money;

    private Integer isDeleted;

    private Timestamp createAt;

    private Timestamp updateAt;
}
