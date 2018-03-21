package com.cabletech.rms.proxy.web.exceptionHandler;

import com.cabletech.common.util.GsonUtils;
import com.cabletech.rms.proxy.web.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 * //todo ajax 与非ajax请求异常需要区分
 * Created by liyong on 2018/3/20.
 */
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        JsonResponse jsonResponse = new JsonResponse();
        String exceptionMessage = e.getMessage();
        jsonResponse.setMessage(exceptionMessage == null ? e.toString() : exceptionMessage);
        jsonResponse.setSuccess(false);
        response.setCharacterEncoding("UTF-8"); //避免乱码
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        logger.error(exceptionMessage, e);
        try {
            response.getWriter().write(GsonUtils.toJson(jsonResponse));
        } catch (IOException e1) {
            logger.error("通讯异常", e);
        }
        return modelAndView;
    }
}
