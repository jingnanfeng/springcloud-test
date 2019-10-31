package cn.com.nanfeng.service.impl;

import cn.com.nanfeng.exception.BusinessException;
import cn.com.nanfeng.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 15:37
 */
@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public void testError() {
        throw new BusinessException(1001,"异常监控");
    }
}
