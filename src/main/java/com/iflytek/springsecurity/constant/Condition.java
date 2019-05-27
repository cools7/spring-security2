package com.iflytek.springsecurity.constant;

/**
 * @author cool
 * @version V1.0
 * @className Condition
 * @description Problem In Chair, Not In Computer.
 * @createDate 2018年06月11日
 */
public interface  Condition {

    enum UseRecently implements Condition{

        /**
         * 朗读员
         */
        READER,

        /**
         * 背景音
         */
        BGVOX
    }
    enum UserType implements Condition {

        /**
         * 普通用户
         */
        GENERAL_USER,
        /**
         * 老高级vip
         */
        OLD_VIP_USER,
        /**
         * 过期用户
         */
        EXPIRED_USER,
        /**
         * 老超级vip
         */
        OLD_SUPER_VIP_USER,

        /**
         * 新高级vip
         */
        NEW_VIP_USER,
        /**
         * 新超级vip
         */
        NEW_SUPER_VIP_USER

    }
    enum SubscriptionEvent implements Condition{

        /**
         * 收听
         */
        LISTEN_NUM,

        /**
         * 收藏
         */
        PRAISE_NUM,

        /**
         * 分享
         */
        SHARE_NUM
    }


    enum NyStatisticsEvent implements Condition{

        /**
         * 收听
         */
        LISTEN_NUM,

        /**
         * 收藏
         */
        PRAISE_NUM,

        /**
         * 分享
         */
        SHARE_NUM
    }


}
