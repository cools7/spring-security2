package com.iflytek.springsecurity.security.filter;

import com.iflytek.springsecurity.constant.GlobalConstant;
import com.iflytek.springsecurity.security.ValidateCodeException;
import com.iflytek.springsecurity.security.handler.MyAuthenticationFailHandler;
import com.iflytek.springsecurity.utils.ThreadLocalMap;
import com.iflytek.springsecurity.utils.common.VerifyCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * @author cool
 * @version V1.0
 * @className ValidateCodeFilter
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Value("${security.loginPath}")
    String securityLoginPath;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("--{}",request.getRequestURI());
        if (StringUtils.equals(securityLoginPath, request.getRequestURI())
                &&StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                System.out.println("验证码1");
                validate(new ServletWebRequest(request));
            }
            catch (ValidateCodeException e) {
                System.out.println("验证码2");
                myAuthenticationFailHandler.onAuthenticationFailure(request, response, e);
                // 不继续执行
                return;
            }
        }
        // 继续执行下一步
        filterChain.doFilter(request, response);
    }
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        System.out.println("验证码3");
        // 从Session中获取imageCode对象
//        VerifyCode verifyCode = (VerifyCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        log.info("codeInRequest--{}",codeInRequest);
//        if (StringUtils.isBlank(codeInRequest)) {
//            System.out.println("纳尼？？？为空");
//            throw new ValidateCodeException("验证码为空或者不存在");
//        }
//        if(verifyCode == null){
//            System.out.println("纳尼？？？不存在");
//            throw new ValidateCodeException("验证码不存在，请刷新验证码");
//        }
//        if (verifyCode.isExpired()) {
//            //从session移除过期的验证码
//            System.out.println("纳尼？？？过期");
//            ThreadLocalMap.remove("SESSION_KEY");
//            throw new ValidateCodeException("验证码过期");
//        }
//        if (!StringUtils.equalsIgnoreCase(verifyCode.getText(), codeInRequest)) {
//            System.out.println("纳尼？？？不匹配");
//            throw new ValidateCodeException("验证码不匹配");
//        }
        // session 中移除key
        ThreadLocalMap.remove("SESSION_KEY");
    }



}
