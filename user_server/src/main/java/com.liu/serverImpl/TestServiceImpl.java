package com.liu.serverImpl;

import org.springframework.stereotype.Service;
import com.cn.liu.service.TestService;

/**
 * @author lzf
 * desc
 * date 2018/5/2-22:20
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public String getUserInfo(String userName) {
        return userName +"测试名称";
    }
}
