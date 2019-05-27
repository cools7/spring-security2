package com.iflytek.springsecurity.security.handler;

import com.iflytek.springsecurity.security.ValidateCodeException;
import com.iflytek.springsecurity.utils.common.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 登录失败处理类
 *               用户登录系统失败后需要做的业务操作
 *               包括：分类返回登录失败原因
 * @Author: zule
 * @Date: 2019/5/6
 */
@Component
@Slf4j
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
       logger.info(e);
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            //可在此记录登录失败次数，进行锁定
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"用户名或密码错误"));
        } else if (e instanceof DisabledException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"账户被禁用，请联系管理员"));
        }else if (e instanceof ValidateCodeException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"验证码无效"));
        }else if (e instanceof AccountExpiredException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"账号过期"));
        } else if (e instanceof LockedException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"用户被锁"));
        }else if (e instanceof CredentialsExpiredException) {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"密码过期"));
            //可以新增登录异常次数超限LoginFailLimitException
        } else {
            ResponseUtil.out(response, ResponseUtil.resultMap(401,"登录失败"));
        }
    }
}
