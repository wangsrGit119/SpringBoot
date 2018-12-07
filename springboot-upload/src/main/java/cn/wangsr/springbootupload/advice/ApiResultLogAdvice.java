package cn.wangsr.springbootupload.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Order(3)
public class ApiResultLogAdvice implements ResponseBodyAdvice {

    Logger logger= LoggerFactory.getLogger(ApiResultLogAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest request, ServerHttpResponse response) {
       Object  reqBody=ApiParamLogAdvice.requestBody.get();
        StringBuffer log = new StringBuffer();
        log.append(request.getURI().getPath())
                .append("-")
                .append(request.getMethod())
                .append("-paramBody:")
                .append(reqBody)
                .append("-resultBody:")
                .append(o.toString());
        HttpHeaders headerNames = response.getHeaders();
        log.append("-headers:").append(headerNames.toString());

        logger.info(log.toString());

        return o;
    }
}
