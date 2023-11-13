package com.source.data.server.client_controller.user;

import com.pojo.RESULT.Result;
import com.pojo.user.Client.USER;
import com.pojo.user.Client.userCode;
import com.pojo.user.User;
import com.source.data.server.service.user.userService;
import com.utils.JwtUtils.JWTMessage;
import com.utils.JwtUtils.JWTUtils;
import com.utils.JwtUtils.applicationConfigProperties.JwtProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * app端 : 用户详情模块
 */
@RestController("client-userController")
@Slf4j
@RequestMapping("/user/user")
public class userController {
    @Autowired
    private userService service;
    @Autowired
    private JwtProperties properties;

    @PostMapping("/login")
    public Result userLogin(@RequestBody userCode code){
        log.info("微信登入");
        User user = service.getUser(code);  // 根据微信授权码进行查询 获取用户对象
        Map<String,Object> map = new HashMap<>();
        map.put(JWTMessage.USER_ID, user.getId());
        String jwt = JWTUtils.createJWT(properties.getClient_PasswordKey(), properties.getClient_time(), map);
        log.info("jwt令牌 : {}",jwt);
        USER obj = new USER();
        obj.setId(user.getId());
        obj.setOpenid(user.getOpenid());
        obj.setToken(jwt);
        return Result.success(obj);
    }

    @PostMapping
    public Result logout(){
        log.info("退出登入");
        return Result.success();
    }
}
