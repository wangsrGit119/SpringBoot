package cn.wangsr.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;

import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;



@ControllerAdvice
@Order(3)
public class ApiResultLogAdvice implements ResponseBodyAdvice {

    Logger logger= LoggerFactory.getLogger(ApiResultLogAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        Object reqBodyInfo=ApiParamLogAdvice.requestBody.get();
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(serverHttpRequest.getURI())
        .append("paramBody:").append(reqBodyInfo).append("---resultBody:")
                .append(o.toString());
        logger.info(stringBuffer.toString());

        return o;
    }
}
