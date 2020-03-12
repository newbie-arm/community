package com.newbie.community.provider;

import com.alibaba.fastjson.JSON;
import com.newbie.community.dto.AccessTokenDto;
import com.newbie.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            // access_token=adbb9c4607be006ab9e3b4c9328641058d573f5a&scope=&token_type=bearer
            // 需要截取 =  和 & 中间的 token
            String[] split = str.split("&");
            String[] tokenSplit = split[0].split("=");

            return tokenSplit[1];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public GithubUser getUser(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
