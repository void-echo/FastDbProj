package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.service.AdminService;
import com.echo.fastdbproj.service.CustomerService;
import com.echo.fastdbproj.service.DriverService;
import com.echo.fastdbproj.util.SHA256Util;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    @Resource
    private SHA256Util sha;

    @Resource
    MainController mainController;

    public Set<String> mySet = new HashSet<>();

    void in(String id) {
        mySet.add(id);
    }

    void out(String id) {
        mySet.remove(id);
    }

    boolean isIn(String id) {
        return mySet.contains(id);
    }

    boolean isOut(String id) {
        return !isIn(id);
    }


    @RequestMapping("login")
    public String login(String user, String pwdSha, Optional<String> userType) {
        String type = userType.orElse("customer");
        String dbSha = switch (type) {
            case "driver" -> {
                var dbDriver = driverService.queryById(user);
                if (dbDriver == null) {
                    yield null;
                }
                yield dbDriver.getPasswordSha256();
            }
            case "admin" -> {
                var dbAdmin =  adminService.queryById(user);
                yield dbAdmin == null ? null : dbAdmin.getPasswordSha256();
            }
            default -> {
                var dbCustomer = customerService.queryById(user);
                if (dbCustomer == null) {
                    yield null;
                }
               yield  dbCustomer.getPasswordSha256();
            }
        };
        if ( dbSha == null || pwdSha == null ) return "failed";
        var flag = sha.checkSHA(dbSha, pwdSha);
        if (flag) {
            in(user);
            return "success";
        }
        return "failed";
    }
}