package com.source.data.server.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.user.Client.userCode;
import com.pojo.user.User;
import com.source.data.server.dao.user.UserMapper;
import com.source.data.server.service.user.userService;
import com.utils.ErrorUtils.Message;
import com.utils.ExceptionUtils.LoginException;
import com.utils.HttpClientUtils.HttpClientUtil;
import com.utils.WEUtils.weixinGetValue;
import com.utils.WEUtils.weixinProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class userClass implements userService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private weixinProperties weixinProperties;

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";   // 微信服务端网址
    @Override
    public User getUser(userCode code) {
        // 发送get微信请求 获取用户的openid
        Map<String,String> map = new HashMap<>();   // 构建需要的参数
        map.put(weixinGetValue.APPID, weixinProperties.getAppID());
        map.put(weixinGetValue.SECRET, weixinProperties.getSecret());
        map.put(weixinGetValue.JS_CODE, code.getCode());
        map.put(weixinGetValue.GRANT_TYPE, "authorization_code");
        String result = HttpClientUtil.doGet(URL, map); // 返回一个json格式的字符串
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = (String) jsonObject.get("openid");  // 通过key获取
        // 如果用户不存在 则抛出异常
        if (openid == null){
            throw new LoginException(Message.LOGIN_ERROR);
        }
        // 获取到openid后 查询数据库 无此用户 默认新建该用户
        User user = mapper.selectCode(openid);  // 获取到user对象
        if (user == null){
            // 没有改用户
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDateTime.now());
            mapper.insert(user);
        }
        // 返回用户对象
        return user;
    }

    @Override
    public User getUserId(Long userId) {
        return mapper.getUserId(userId);
    }

    @Override
    public Integer getDeclareduser(LocalDateTime bight, LocalDateTime end) {
        return mapper.getDeclareduser(bight,end);
    }
}
