package com.liu.mapper;

import com.cn.liu.dto.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author lzf
 * desc
 * date 2018/5/4-20:27
 */
public interface UserMapper {

    void save(User user);

    void update(User user);

    void deleteById(@Param("id") String id);

    User login(@Param("userName") String userName, @Param("passWord") String passWord);

    int getUserInfo(@Param("userName") String userName);
}
