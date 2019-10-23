package cn.wangsr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wjl
 * @description:
 * @time: 2019/10/23 0023 11:32
 */

@ControllerAdvice
@RestController
public class SelfException {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(Exception e){
        System.out.println("异常-> {}"+e.getMessage());
        return "failed";
    }


}
