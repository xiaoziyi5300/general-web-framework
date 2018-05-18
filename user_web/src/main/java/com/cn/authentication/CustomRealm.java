package com.cn.authentication;

import com.alibaba.fastjson.JSON;
import com.cn.liu.dto.User;
import com.cn.liu.service.UserService;
import com.cn.liu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lzf
 * desc 自定义realm编码
 * date 2018/5/4-23:18
 */
public class CustomRealm extends AuthorizingRealm {

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("ustomRealm");
    }
    @Autowired(required = false)
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /***
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("1111");
        return null;
    }

    /***
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken t =(UsernamePasswordToken)token;
        String userName =t.getUsername();
        String passWord =String.valueOf(t.getPassword());
        System.out.println("账号" +userName +"密码"+passWord);
        User adminUser = userService.login(userName,passWord);
        // 如果查询不到返回null
        if (adminUser == null) {
            throw new UnknownAccountException("验证未通过,未知账户");
        }
        try{
            //redisUtil.set("loginInfo", JackSonUtil.getSting(adminUser),86400L);
            redisUtil.set("loginInfo", JSON.toJSONString(adminUser),86400L);
        }catch (Exception ex){
            System.out.println("插入redis异常");
            ex.printStackTrace();
        }
        return new SimpleAuthenticationInfo(adminUser, passWord, this.getName());
    }
    // 清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
