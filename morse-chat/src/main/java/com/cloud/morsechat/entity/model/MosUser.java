package com.cloud.morsechat.entity.model;

import com.cloud.morsechat.entity.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Entity(name = "mos_user")
//@Table(name = "mos_user") //Hibernate4.3以上不再使用@Table，改这个参数直接到@Entity上定义
public class MosUser extends BaseModel {

    private String username;
    private String nickname;
    private String hash;
    private String password;
    private String salt;
    private String avatar;
    private Integer sex;
    private Integer province;
    private Integer city;
    private Integer district;
    private String email;
    private String phone;
    private String content;

    /**
     * 以下是地址的联表查询
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
        @JoinColumn(name = "province",referencedColumnName ="code",insertable=false,updatable=false),
        @JoinColumn(name = "city",referencedColumnName ="code",insertable=false,updatable=false),
        @JoinColumn(name = "district",referencedColumnName ="code",insertable=false,updatable=false)
    })
    private MosRegion mosRegion;
}
