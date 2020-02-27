package cn.wangsr.security.result;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: wjl
 * @description:
 * @time: 2020/1/14 0014 16:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String requestId;
    private Integer code;
    private String msg;
    private Map<String, Object> data;

}
