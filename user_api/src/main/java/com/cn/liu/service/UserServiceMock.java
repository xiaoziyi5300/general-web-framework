package com.cn.liu.service;

import com.cn.liu.dto.User;
import org.apache.log4j.Logger;

/**
 * @author lzf
 * desc dubbo 服务降级测试
 * date 2018/5/13-15:44
 */
public class UserServiceMock implements UserService{

    private static Logger logger = Logger.getLogger(UserServiceMock.class);
    @Override
    public void save(User user) {
        logger.info("保存方法调用异常");
    }

    @Override
    public void update(User user) {
        logger.info("更新方法调用异常");
    }

    @Override
    public void deleteById(String id) {
        logger.info("删除方法调用异常");
    }

    @Override
    public User login(String userName, String passWord) {
        logger.info("登陆方法调用异常");
        return null;
    }
}
