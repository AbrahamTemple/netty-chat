package com.cloud.morsechat.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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

    /*
     * 主键策略支持
     * AUTO、SEQUENCE：Mysql不支持
     * IDENTITY：Oracle不支持
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    protected Long id;

    @Column(name = "state",length = 1,columnDefinition = "0")
    @Generated(GenerationTime.INSERT)
    protected Integer state;

    @Column(name = "deleted",length = 1,columnDefinition = "0")
    protected Integer deleted;

    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    @Column(name = "updatetime")
    private Date updateTime;
}
