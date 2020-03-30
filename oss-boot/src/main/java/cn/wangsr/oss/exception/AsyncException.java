package cn.wangsr.oss.exception;

import lombok.Data;

/**
 * @author: wjl
 * 异步线程使用
 * @time: 2019/3/27 0002 14:58
 */
@Data
public class AsyncException extends Exception {
    private int code;
    private String msg;

}

