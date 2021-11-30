package com.cloud.morsechat.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.12.1
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Data
public class LogRequestBody implements Serializable {
    private String hash1;
    private String hash2;
}
