package com.cn.controller;

import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.User;
import com.cn.liu.enumeration.UserEnum;
import com.cn.liu.service.UserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * desc
 * date 2018/5/4-23:10
 */
@RestController
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired(required = false)
    private UserService userService;

    /***
     * 登录 同时把用户信息写到redis
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("/user/login")
    public ReponseDto login(String userName, String passWord){
        ReponseDto dto = new ReponseDto();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        logger.info("为了验证登录用户而封装的token为" + token);
        token.setRememberMe(true);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try{
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + userName + "]进行登录验证..验证通过");
            dto.setCode(UserEnum.USER_SUCCESS.getKey());
            dto.setMessage(UserEnum.USER_SUCCESS.getValue());
            dto.setStatus(CommonConstant.SUCCESS_STATUS);
            return dto;
        }catch (UnknownAccountException ex){
            dto.setMessage(ex.getMessage());
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }
        dto.setCode(UserEnum.USER_ACCOUNT_ERROR.getKey());
        dto.setStatus(CommonConstant.FAIL_STATUS);
        return dto;
    }

    /***
     * 注册
     * @param user
     */
    @RequestMapping(value = "/regist/user",method = RequestMethod.POST)
    public ReponseDto registUser(User user)throws Exception{
        ReponseDto dto = new ReponseDto();
        userService.save(user);
        dto.setCode(UserEnum.USER_SUCCESS.getKey());
        dto.setMessage(UserEnum.USER_SUCCESS.getValue());
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        return dto;
    }
    /***
     * 保存
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ReponseDto saveUser(User user) throws Exception {
        ReponseDto dto = new ReponseDto();
        userService.save(user);
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }

    /***
     * 修改
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ReponseDto updateUser(User user) throws Exception {
        ReponseDto dto = new ReponseDto();
        userService.update(user);
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }
    /***
     * 主页
     * @return
     */
    @RequestMapping("/user/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }
}
