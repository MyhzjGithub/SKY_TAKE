package com.source.ExceptionHandler;

import com.pojo.RESULT.Result;
import com.utils.ErrorUtils.Message;
import com.utils.ExceptionUtils.NullUserException;
import com.utils.ExceptionUtils.OrderException;
import com.utils.ExceptionUtils.PasswordException;
import com.utils.ExceptionUtils.UsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.LoginException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice   // 声明全局异常处理
@Slf4j
public class My_ExceptionHandler {

    @ExceptionHandler(PasswordException.class)
    public Result Password(PasswordException e){
        String message = e.getMessage();    // 获取错误信息
        log.info("捕获到: PasswordException异常  异常信息为 : {}",message);
        return Result.error(message);
    }

    @ExceptionHandler(NullUserException.class)
    public Result NullUser(NullUserException e){
        String message = e.getMessage();    // 获取错误信息
        log.info("捕获到: NullUserException异常  异常信息为 : {}",message);
        return Result.error(message);
    }

    @ExceptionHandler(UsernameException.class)
    public Result Username(UsernameException e){
        String message = e.getMessage();    // 获取错误信息
        log.info("捕获到: UsernameException异常  异常信息为 : {}",message);
        return Result.error(message);
    }
    @ExceptionHandler(LoginException.class)
    public Result Login(LoginException e){
        String message = e.getMessage();    // 获取错误信息
        log.info("捕获到: LoginException异常  异常信息为 : {}",message);
        return Result.error(message);
    }

    @ExceptionHandler(OrderException.class)
    public Result Order(OrderException e){
        String message = e.getMessage();    // 获取错误信息
        log.info("捕获到: OrderException异常  异常信息为 : {}",message);
        return Result.error(message);
    }

    @ExceptionHandler(Exception.class)
    public Result EXCEPTION(Exception e){
        log.info("无法捕获到当前异常 : 异常信息为 {}",e.getMessage());
        e.printStackTrace();    // 输出异常信息到控制台
        return Result.error(Message.NETWORK_ERROR);
    }
}
