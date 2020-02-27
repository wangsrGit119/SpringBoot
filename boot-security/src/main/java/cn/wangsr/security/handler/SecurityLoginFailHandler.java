package cn.wangsr.security.handler;

import cn.wangsr.security.result.ResultDTO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjl
 * @description: 登录失败处理器
 * @time: 2020/1/14 0014 16:04
 */
@Component
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(SecurityLoginFailHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Map map = new HashMap(10);
        ResultDTO resultDTO = ResultDTO.builder()
                .requestId((String)httpServletRequest.getAttribute("request_id"))
                .code(10003)
                .msg("login failed")
                .data(map)
                .build();
        logger.info("login failed");
        httpServletResponse.getWriter().print(JSON.toJSONString(resultDTO));

    }
}
