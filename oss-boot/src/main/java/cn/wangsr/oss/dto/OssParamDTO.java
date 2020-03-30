package cn.wangsr.oss.dto;


import lombok.Builder;
import lombok.Data;

/**
 * @author WJL
 */
@Data
@Builder
public class OssParamDTO {

    /**
     * 参考官方配置 根据自己的oss配置参数
     */

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String folder;
    /**
     * objectName = folder + fileName
     */
    private String objectName;

    /**
     * 上传线程
     */
    private Integer task;

    /**
     * 每个线程处理大小 分片大小
     */
    private Integer number;

}
