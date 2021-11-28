package com.cloud.morsechat.entity.model;

import com.cloud.morsechat.entity.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.27
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
@EqualsAndHashCode(callSuper = true)
@Entity(name = "mos_friend")
public class MosFriend extends BaseModel {

    private Long userid;

    private Long friendid;

    /**
     * 以下是用户的联表查询
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friendid",insertable=false,updatable=false)
    private MosUser mosUser;

}
