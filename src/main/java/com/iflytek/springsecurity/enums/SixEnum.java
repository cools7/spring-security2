package com.iflytek.springsecurity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author cool
 * @version V1.0
 * @className SixEnum
 * @description Code Is Poetry.
 * @createDate 2019年01月25日
 */
public enum SixEnum implements IEnum{

    BOY(1, "男"),
    GRIL(2, "女");



    SixEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @EnumValue
    private final  int  value;
    private final  String desc;

    @JsonValue
    @Override
    public Serializable getValue() {
        return value;
    }

//    @JsonValue
    public String getDesc() {
        return desc;
    }


}
