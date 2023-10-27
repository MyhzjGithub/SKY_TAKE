package com.pojo.RESULT;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 浏览器标准返回实体类
 */
@AllArgsConstructor
@Data
public class Result implements Serializable {
     private Integer code;  // 返回编码 1成功 0失败
     private String msg;    // 错误信息
     private Object data;   // 数据

    public static Result success(){
        return new Result(1,null,null);
    }
    public static Result success(Object data){
        return new Result(1,null,data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
