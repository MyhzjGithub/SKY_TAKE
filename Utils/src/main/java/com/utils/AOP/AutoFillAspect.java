package com.utils.AOP;

import com.utils.Annotation.AutoFill;
import com.utils.ReflexUtils.AutoFillReflex;
import com.utils.ThreadUtils.BeanThread;
import com.utils.typeUtils.TYPE;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面类 公共字段自动填充
 *  使用该注解进行填充时: 必须遵守的约定
 *      实体类对象 放在方法第一个参数 否者无法获取
 */
@Aspect
@Slf4j
@Component
public class AutoFillAspect {

    @Around("@annotation(com.utils.Annotation.AutoFill)")
    public Object AutoFill(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("开始进行公共字段填充");
        // 获取当前被拦截的方法上数据库操作类型
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);  // 获得方法上的注解对象
        TYPE value = annotation.value();    // 获取数据库操作类型
        // 获取方法参数 -- 实体类对象
        Object[] pointArg = proceedingJoinPoint.getArgs();
        if (pointArg == null || pointArg.length == 0){
            return null;
        }
        Object result = pointArg[0];    // 获取实体类对象

        // 准备赋值数据 -- 时间 & 当前登入用户的id
        LocalDateTime time = LocalDateTime.now();   // 时间
        Long currentId = BeanThread.getCurrentId(); // id

        // 根据不同的操作类型 为对应的属性进行赋值  -- 使用反射
        Class<?> resultClass = result.getClass();   // 获取到对象的字节码文件
        if (value == TYPE.INSERT){  // 需要给四个公共字段赋值
            // 获取四个属性的Set方法
            Method setCreateTime = resultClass.getDeclaredMethod(AutoFillReflex.SET_CREATE_TIME, LocalDateTime.class);
            Method setUpdateTime = resultClass.getDeclaredMethod(AutoFillReflex.SET_UPDATE_TIME, LocalDateTime.class);
            Method setCreateUser = resultClass.getDeclaredMethod(AutoFillReflex.SET_CREATE_USER, Long.class);
            Method setUpdateUser = resultClass.getDeclaredMethod(AutoFillReflex.SET_UPDATE_USER, Long.class);
            setCreateTime.invoke(result, time);
            setUpdateTime.invoke(result, time);
            setCreateUser.invoke(result, currentId);
            setUpdateUser.invoke(result, currentId);
        }else if (value == TYPE.UPDATE){    // 需要给两个公共字段赋值
            // 获取两个属性的Set方法
            Method setUpdateTime = resultClass.getDeclaredMethod(AutoFillReflex.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = resultClass.getDeclaredMethod(AutoFillReflex.SET_UPDATE_USER, Long.class);
            setUpdateTime.invoke(result, time);
            setUpdateUser.invoke(result, currentId);
        }
        Object proceed = proceedingJoinPoint.proceed(); // 启动原始方法
        return proceed;
    }

}
