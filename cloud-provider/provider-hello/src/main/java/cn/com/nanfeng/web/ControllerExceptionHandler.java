package cn.com.nanfeng.web;

import cn.com.nanfeng.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-31 15:30
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handlerException(BusinessException e){
        Map<String,Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("message",e.getMessage());
        return map;
    }
}
