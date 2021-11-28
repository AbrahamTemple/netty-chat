package com.cloud.morsechat.entity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.11.27
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"}) //允许某字段为空
@Entity(name = "mos_region")
public class MosRegion implements Serializable {

    @Id
    private Integer code;
    private Integer parentid;
    private String grade;
    private String name;
    private String shortname;
    private String parentpath;
    private String province;
    private String city;
    private String district;
    private String provinceshortname;
    private String cityshortname;
    private String districtshortname;
    private String provincepinyin;
    private String citypinyin;
    private String districtpinyin;
    private String citycode;
    private String zipcode;
    private String pinyin;
    private String jianpin;
    private String firstchar;
    private String lng;
    private String lat;
    private String remark1;
    private String remark2;


}
