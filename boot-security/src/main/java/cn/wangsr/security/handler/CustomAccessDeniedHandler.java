package cn.wangsr.security.handler;

import cn.wangsr.security.result.ResultDTO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjl
 * @description:
 * @time: 2020/1/14 0014 16:56
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Map map = new HashMap(10);
        ResultDTO resultDTO = ResultDTO.builder()
                .requestId((String)httpServletRequest.getAttribute("request_id"))
                .code(10004)
                .msg("AccessDenied")
                .data(map)
                .build();
        logger.info("AccessDenied");
        httpServletResponse.getWriter().print(JSON.toJSONString(resultDTO));
    }
}
