package com.newbie.community.controller;

import com.newbie.community.model.User;
import com.newbie.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping({"/", "index"})
    public String index(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        /*
        当访问首页的时候，循环所有cookie
        找到 token ， 去数据库里面查，是否有这条记录
        如果有则放到session里面
        如果没有登陆过，就没有token，就不会登陆成功
        这个可以解决什么问题：
            用户正在使用网站时，服务器直接重启或者宕机时
            每次修复好就不用用户重新登陆了， 不然每次重启，都会使用户重新登陆

            此方法只有手动删除cookie或者关闭浏览器才能显出登陆状态

            后期可以用 redis 去做，现在暂时用mysql做， 其实就是用mysql代替redis
         */
        if (cookies!=null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = service.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        return "index";
    }

}
