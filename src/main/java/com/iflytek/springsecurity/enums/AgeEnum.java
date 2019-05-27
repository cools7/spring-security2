package com.iflytek.springsecurity.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author cool
 * @version V1.0
 * @className AgeEnum
 * @description Code Is Poetry.
 * @createDate 2019年01月25日
 */
public enum AgeEnum  implements IEnum {

    ONE(1, "一岁"),
    TWO(2, "二岁");

    private int age;
    private String desc;

    AgeEnum(final int age, final String desc) {
        this.age = age;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.age;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
