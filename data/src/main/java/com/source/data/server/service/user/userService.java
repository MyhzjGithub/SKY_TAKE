package com.source.data.server.service.user;

import com.pojo.user.Client.userCode;
import com.pojo.user.User;

import java.time.LocalDateTime;

public interface userService {
    /**
     * 根据微信授权码获取opneid查询获取用户对象
     * @param code
     * @return
     */
    User getUser(userCode code);

    /**
     * 根据用户id查询用户详细
     * @param userId
     * @return
     */
    User getUserId(Long userId);

    /**
     * 获取当前新增用户
     * @return
     */
    Integer getDeclareduser(LocalDateTime bight, LocalDateTime end);

}
