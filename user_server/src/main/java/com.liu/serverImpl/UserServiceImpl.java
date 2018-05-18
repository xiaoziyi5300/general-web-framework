package com.liu.serverImpl;

import com.cn.liu.constant.CommonConstant;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.util.Md5Utils;
import com.liu.mapper.UserMapper;
import com.cn.liu.dto.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cn.liu.service.UserService;

import java.util.Date;

/**
 * @author lzf
 * desc
 * date 2018/5/4-20:26
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        int count = userMapper.getUserInfo(user.getUserName());
        if (count > CommonConstant.INTEGER_ZERO) {
            throw new BusinessException("用户名已经用过了");
        }
        rewardBean(user);
        user.setPassWord(Md5Utils.MD5Encode((user.getPassWord() + CommonConstant.SECRET_KEY), "iso8859-1", false));
        userMapper.save(user);
    }

    @Override
    public void update(User user) {
        int count = userMapper.getUserInfo(user.getUserName());
        if (count == CommonConstant.INTEGER_ZERO) {
            throw new BusinessException("用户不存在");
        }
        userMapper.update(user);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public User login(String userName, String passWord) {
        logger.info(userName + passWord);
        return userMapper.login(userName,passWord);
    }

    private User rewardBean(User user) {
        user.setCreateDate(new Date());
        user.setCreateUserId("10003");
        user.setCreateUserName("系统管理员");
        user.setModifyDate(new Date());
        user.setModifyUserId("10004");
        user.setModifyUserName("123");
        return user;
    }
}
