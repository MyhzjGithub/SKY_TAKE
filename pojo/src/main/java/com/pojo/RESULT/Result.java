package com.pojo.RESULT;

import lombok.AllArgsConstructor;

/**
 * 浏览器标准返回实体类
 */
@AllArgsConstructor
public class Result {
     private Integer code;
     private String msg;
     private Object data;

    public static Result success(){
        return new Result(200,null,null);
    }
    public static Result success(Object data){
        return new Result(200,null,data);
    }
    public static Result error(String msgValue){
        return new Result(500,msgValue,null);
    }
}
