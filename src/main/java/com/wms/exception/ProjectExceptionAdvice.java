package com.wms.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //记录日志
        //发送消息给运维
        //发邮件给开发人员
        return new Result(ex.getCode(),null,ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){


        return new Result(ex.getCode(),null,ex.getMessage());
    }

//    @ExceptionHandler(Exception.class)
//    public Result doException(Exception ex){
//        return new Result(ResultCode.SYSTEM_UNKNOW_ERR.getCode(),null,"系统繁忙，请稍后在试！");
//    }

}
