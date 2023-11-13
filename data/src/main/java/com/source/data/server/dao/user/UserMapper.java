package com.source.data.server.dao.user;

import com.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    @Select("select id, openid, name, phone, sex, id_number, avatar, create_time from user where openid = #{openid}")
    User selectCode(String openid);

    void insert(User user);

    @Select("select id, openid, name, phone, sex, id_number, avatar, create_time from user where id = #{userId}")
    User getUserId(Long userId);
    @Select("select count(*) from user where create_time between #{bight} and #{end}")
    Integer getDeclareduser(LocalDateTime bight, LocalDateTime end);
}
