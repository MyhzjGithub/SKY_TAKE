package com.utils.PageUtils;

public class startPage {
    public static Integer getStartPage(Integer page , Integer pageSize){
        return (page - 1) * pageSize;
    }
}
