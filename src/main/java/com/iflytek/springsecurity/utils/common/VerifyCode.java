package com.iflytek.springsecurity.utils.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author cool
 * @version V1.0
 * @className VerifyCode
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
public class VerifyCode {

    LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
    //过期时间
    private LocalDateTime localDateTime;

    public VerifyCode() {
    }
    public VerifyCode(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    public VerifyCode(int second) {
        // 多少秒后
        this.localDateTime = LocalDateTime.now().plusSeconds(second);
    }

    // 调用这个方法得到验证码
    public void getImage (HttpServletResponse response) throws IOException {
        lineCaptcha.write(response.getOutputStream());
    }

    // 返回验证码图片上的文本
    public String getText () {
        return lineCaptcha.getCode();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(localDateTime);
    }
}
