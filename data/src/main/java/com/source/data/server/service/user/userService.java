package com.source.data.server.service.user;

import com.pojo.user.Client.userCode;
import com.pojo.user.User;

public interface userService {
    /**
     * 根据微信授权码获取opneid查询获取用户对象
     * @param code
     * @return
     */
    User getUser(userCode code);
}
