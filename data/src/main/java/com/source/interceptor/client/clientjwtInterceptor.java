package com.source.interceptor.client;

import com.utils.JwtUtils.JWTMessage;
import com.utils.JwtUtils.JWTUtils;
import com.utils.JwtUtils.applicationConfigProperties.JwtProperties;
import com.utils.ThreadUtils.BeanThread;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
public class clientjwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    /**
     *  校验JWT令牌
     * @param request   请求
     * @param response  响应
     * @param handler   资源
     * @return  是否放行
     * @throws Exception    异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getClient_token());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            // 解析令牌
            Claims claims = JWTUtils.parseJWT(jwtProperties.getClient_PasswordKey(), token);
            // 获取令牌中存储的唯一标识
            Long userId = Long.valueOf(claims.get(JWTMessage.USER_ID).toString());  // 获取登入用户的id
            BeanThread.setCurrentId(userId);    // 将身份凭证存储进线程空间
            log.info("当前用户id:{}", userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
