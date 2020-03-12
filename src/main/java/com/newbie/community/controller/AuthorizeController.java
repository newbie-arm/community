package com.newbie.community.controller;

import com.newbie.community.dto.AccessTokenDto;
import com.newbie.community.dto.GithubUser;
import com.newbie.community.model.User;
import com.newbie.community.provider.GithubProvider;
import com.newbie.community.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        /*
        判断user是否为空
            登陆成功，写cookie和session
            失败，重新登陆
         */
        if (githubUser!= null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);  // 利用token绑定前端和后端登陆状态
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            System.out.println(user);
            userService.addUser(user);

            /*
            当github登录成功之后，获取用户信息在生成一个token 存入对象 写入数据库
            并且把token放在cookie里面
             */
            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/";
    }
}









