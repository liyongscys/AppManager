package com.cabletech.rms.proxy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cabletech.common.util.GsonUtils;
import com.cabletech.common.util.ResInspectGsonUtils;
import com.cabletech.common.util.SpringContextHolder;
import com.cabletech.rms.proxy.handler.Handler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 处理消息的Spring MVC控制器
 *
 * @author YuLeyuan
 */
@Controller
public class CommandController extends BaseController {
    /**
     * 处理器类的前缀
     */
    private static final String HANDLE_PRE = "HandlerFor";

    private static final String WIRELESS_PATROLPLAN_RESOURCE_CACHE = "WIRELESS_PATROLPLAN_RESOURCE_CACHE";
    private static final String SDEINFO_CACHE = "SDEINFO_CACHE";
    private static final String WPLAN_PATROL_TEMPLATE_CACHE = "WPLAN_PATROL_TEMPLATE_CACHE";
    private static final String USER_CACHE = "USER_CACHE";
    private static final String RES_GPRSRECORD_CACHE = "RES_GPRSRECORD_CACHE";


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final Gson GSON_BUILDER;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
        GSON_BUILDER = gsonBuilder.create();
    }


    /**
     * 处理指令
     *
     * @param cmdId    指令ID
     * @param deviceId 设备ID(imei)
     * @param json     json内容
     * @param request  HttpRequest对象
     * @param response HttpResponse对象
     * @return 返回json格式的内容
     * @throws Exception 异常
     */
    @RequestMapping(value = "/cmd/{cmdId}.do", method = RequestMethod.POST,produces = "application/json;charset=UTF-8",consumes = APPLICATION_JSON)
    @ResponseBody
    public final String execute(@PathVariable("cmdId") String cmdId,
                                @RequestParam(value = "deviceId", required = true) String deviceId,
                                @RequestBody String json, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        StringBuilder requestParams = new StringBuilder(512);
        requestParams.append(">>\nURL:").append(request.getRequestURL());
        requestParams.append("?deviceId=").append(deviceId);
        requestParams.append("\nJson content:\n").append(json);

        logger.info("请求参数:{}", requestParams.toString());
        String beanName = HANDLE_PRE + cmdId;
        Handler handler = SpringContextHolder.getBean(beanName);
        if (handler == null) {
            logger.error("指令对应的bean没有找到,beanName:{}", beanName);
        }
        Object resp = handler.handle(deviceId, cmdId, json);
        if (resp == null) {
            resp = fail();
        }
//        response.setContentType(RESP_CONTENT_TYPE);
        String result = null;
        if (resp instanceof String) {
            result = (String) resp;
        } else {
            if (cmdId.equals("0218")) {
                result = ResInspectGsonUtils.toJson(resp);
            } else {
                result = GsonUtils.toJson(resp);
            }
        }
        logger.info("<<\n{}", result);
        return result;
    }

    private JsonResponse fail() {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setSuccess(false);
        jsonResponse.setMessage("服务端异常,指令返回为null");
        return jsonResponse;
    }
}
