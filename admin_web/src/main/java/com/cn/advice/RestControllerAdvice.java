package com.cn.advice;

import com.cn.exception.SystemException;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzf
 * desc 全局异常处理类
 * date 2018/5/16-20:42
 */
@Component
@ControllerAdvice(annotations = RestController.class)//监听restController异常
public class RestControllerAdvice {

    public static final String STATUS = "-1";
    public static final String ERROR_MESSAGE = "系统异常";
    public static final String ERROR_CODE = "500";

    /***
     * 封装同意异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ReponseDto errorReponse(Exception ex) {
        ReponseDto dto = new ReponseDto();
        ex.printStackTrace();
        if (ex instanceof SystemException) {
            dto.setMessage(ex.getMessage());
            dto.setCode(((BusinessException) ex).getErrorCode());
        } else {
            dto.setMessage(ERROR_MESSAGE);
        }
        dto.setStatus(STATUS);
        dto.setCode(ERROR_CODE);
        return dto;
    }
}
