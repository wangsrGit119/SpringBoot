package cn.wangsr.security.handler;

import cn.wangsr.security.result.ResultDTO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: wjl
 * @description: 登录成功处理器
 * @time: 2020/1/14 0014 16:04
 */
@Component
public class SecurityLoginSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(SecurityLoginSuccessHandler.class);
    private final String REQUEST_ID = "request_id";
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String uid =  UUID.randomUUID().toString();
        MDC.put(REQUEST_ID,uid);
        httpServletRequest.setAttribute(REQUEST_ID,uid);
        Map map = new HashMap(10);
        map.put("username",authentication.getName());
        ResultDTO resultDTO = ResultDTO.builder()
                .requestId(uid)
                .code(200)
                .msg("success")
                .data(map)
                .build();
        logger.info("login success");
        httpServletResponse.getWriter().print(JSON.toJSONString(resultDTO));
    }
}
