package com.cloud.morsechat.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.27
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
@MappedSuperclass //继承字段
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //自增
    protected Long id;

    @Column(name = "state",length = 1)
    protected Integer state;

    @Column(name = "deleted",length = 1)
    protected Integer deleted;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "updatetime")
    private Date updateTime;
}
