package com.cabletech.rms.proxy.web.exceptionHandler;

import com.cabletech.common.util.GsonUtils;
import com.cabletech.rms.proxy.web.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * bindException 处理
 * 使用spring 验证处理时，验证通不过 也会抛出BindException
 * Created by liyong on 2018/3/20.
 */
@ControllerAdvice
public class BindExceptionHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 数据找不到异常
     *
     * @param ex
     * @return
     * @throws IOException
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handleException(BindException ex) {
        logger.error(ex.getMessage(), ex);
        JsonResponse jsonResponse = new JsonResponse();
        StringBuilder errorMsg = new StringBuilder(64);
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
            errorMsg.append(objectError.getDefaultMessage());
            errorMsg.append("; ");
        }
        jsonResponse.setMessage(errorMsg.toString());
        jsonResponse.setSuccess(false);
        final String json = GsonUtils.toJson(jsonResponse);
        logger.debug(json);
        //final ResponseEntity<String> stringResponseEntity = new ResponseEntity<String>(json, HttpStatus.OK);
        return json;
    }


}
