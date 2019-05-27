package com.iflytek.springsecurity.controller;

import com.google.gson.Gson;
import com.iflytek.springsecurity.constant.GlobalConstant;
import com.iflytek.springsecurity.entity.Result;
import com.iflytek.springsecurity.entity.User;
import com.iflytek.springsecurity.mapper.UserMapper;
import com.iflytek.springsecurity.utils.ThreadLocalMap;
import com.iflytek.springsecurity.utils.common.VerifyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cool
 * @version V1.0
 * @className UserController
 * @description Code Is Poetry.
 * @createDate 2019年01月25日
 */
@Api(value = "背景音", description = "背景音相关接口")
@RestController
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@Slf4j
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/verifyCode/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1.根据随机数生成图片
        VerifyCode vc = new VerifyCode(60);//设置60秒过期
        /*ImageCode imageCode = createImageCode(request);*/
        // 2.将图片存入session中
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, vc);
        // 3.将生成的图片写入到接口响应中
        ThreadLocalMap.put("SESSION_KEY",vc);
        GlobalConstant.Sys.TOKEN_AUTH_DTO=vc.getText();
        log.info("根据随机数生成图片:{}",vc.getText());
        vc.getImage(response);

    }

    @ApiOperation(value = "登录成功操作", notes = "")
    @GetMapping(value = "/index.do")
    public Result<Integer> index() {
        log.info("登录成功操作");
        return Result.sucessOf(null);
    }
    @ApiOperation(value = "基本设置", notes = "")
    @GetMapping(value = "/base")
    public Result<String> base(HttpServletRequest request) {

        log.info("基本设置:{}"+new Gson().toJson(request.getSession()));
        return Result.sucessOf("基本设置");
    }
    @ApiOperation(value = "用戶管理", notes = "")
    @GetMapping(value = "/userManage")
    public Result<String> userManage() {
        log.info("用戶管理");
        return Result.sucessOf("用戶管理");
    }
    @ApiOperation(value = "用户权限", notes = "")
    @GetMapping(value = "/userAuthor")
    public Result<String> userAuthor() {
        log.info("用户权限");
        return Result.sucessOf("用户权限");
    }
    @ApiOperation(value = "其他", notes = "")
    @GetMapping(value = "/userOther")
    public Result<String> userOther() {
        log.info("其他");
        return Result.sucessOf("其他");
    }


    @ApiOperation(value = "获取背景音分类信息", notes = "")
    @GetMapping(value = "/bgVoxClass")
    @PreAuthorize("hasRole('person')")
    public Result<User> bgVoxClass() {
        User user=userMapper.selectById(1L);
//        log.info("user--{}",user.getUserName());
        return Result.sucessOf(user);
    }


    @ApiOperation(value = "获取背景音分类信息", notes = "")
    @GetMapping(value = "/bgVoxClass2")
    @PreAuthorize("hasRole('super') and hasRole('person')")
    public Result<User> bgVoxClass2() {
        User user=userMapper.selectById(1L);
//        log.info("user--{}",user.getUserName());
        return Result.sucessOf(user);
    }

    @ApiOperation(value = "获取背景音分类信息", notes = "")
    @GetMapping(value = "/bgVoxClass3")
    public Result<User> bgVoxClass3() {
        User user=userMapper.selectById(1L);
//        log.info("user--{}",user.getUserName());
        return Result.sucessOf(user);
    }

    @ApiOperation(value = "登录开始", notes = "")
    @GetMapping(value = "/index")
    public Result<User> login() {
        log.info("成功跳转");
        return Result.sucessOf(null);
    }
}
