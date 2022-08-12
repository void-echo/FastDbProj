package com.echo.fastdbproj.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.echo.fastdbproj.entity.Admin;
import com.echo.fastdbproj.service.AdminService;
import com.echo.fastdbproj.service.CustomerService;
import com.echo.fastdbproj.service.DriverService;
import com.echo.fastdbproj.util.SHA256Util;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 登录测试
 */
@RestController
@CrossOrigin
@RequestMapping("/acc/")
public class LoginController {
    @Resource
    AdminService adminService;
    @Resource
    CustomerService customerService;
    @Resource
    DriverService driverService;

    // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
    @RequestMapping("doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
        }
        return SaResult.error("登录失败");
    }

    // 查询登录状态  ---- http://localhost:8081/acc/isLogin
    @RequestMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    // 测试注销  ---- http://localhost:8081/acc/logout
    @RequestMapping("logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }


    @Resource
    private SHA256Util sha;


    @RequestMapping("login")
    public String login(String user, String pwdSha, Optional<String> userType) {
        String type = userType.orElse("customer");
        String dbSha = switch (type) {
            case "driver" -> driverService.queryById(user).getPasswordSha256();
            case "admin" -> adminService.queryById(user).getPasswordSha256();
            default -> customerService.queryById(user).getPasswordSha256();
        };
        if ( dbSha == null || pwdSha == null ) return "failed";
        var flag = sha.checkSHA(dbSha, pwdSha);
        if (flag) {
            StpUtil.login(user);
            return "success";
        }
        return "failed";
    }
}