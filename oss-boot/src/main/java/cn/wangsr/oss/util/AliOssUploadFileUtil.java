package cn.wangsr.oss.util;

import cn.wangsr.oss.dto.OssParamDTO;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import java.io.File;
import java.io.InputStream;

/**
 * oss文件操作
 * @author WJL
 */
public class AliOssUploadFileUtil {

    /**
     * oss上传
     * @param ossParamDTO
     * @param inputStream
     * @return
     */
    public static PutObjectResult uploadFile(OssParamDTO ossParamDTO, InputStream inputStream){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossParamDTO.getEndpoint(), ossParamDTO.getAccessKeyId(), ossParamDTO.getAccessKeySecret());
        PutObjectResult putObjectResult = null;
        // 上传文件流。
        try {
            putObjectResult = ossClient.putObject(ossParamDTO.getBucketName(), ossParamDTO.getObjectName(), inputStream);
            //权限设置
            ossClient.setBucketAcl(ossParamDTO.getBucketName(), CannedAccessControlList.PublicRead);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
      return  putObjectResult;
    }

    public static void uploadBigFile(OssParamDTO ossParamDTO,String path, File file,Long fileId) throws Throwable {
        System.out.println("上传时间："+System.currentTimeMillis());
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossParamDTO.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ossParamDTO.getAccessKeyId();
        String accessKeySecret = ossParamDTO.getAccessKeySecret();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata meta = new ObjectMetadata();
        // 指定上传的内容类型。
        meta.setContentType("text/plain");

        // 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new UploadFileRequest(ossParamDTO.getBucketName(),ossParamDTO.getObjectName());
        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(path);
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(ossParamDTO.getTask());
        // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(ossParamDTO.getNumber() * 1024 * 1024);

        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        Callback callback = new Callback();
        callback.setCalbackBodyType(Callback.CalbackBodyType.URL);
        callback.setCallbackBody("fileId="+fileId+"&fileName=${object}&uploadStatus=1");
        callback.setCallbackUrl("http://3m8wv2.natappfree.cc/web/common/callBack");
        uploadFileRequest.setCallback(callback);

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);
        //权限设置
        ossClient.setBucketAcl(ossParamDTO.getBucketName(), CannedAccessControlList.PublicRead);
        // 关闭OSSClient。
        ossClient.shutdown();
    }



    /**
     * 文件地址获取
     * @param ossParamDTO
     * @return
     */
    public static String getUrl(OssParamDTO ossParamDTO){
        String url = "https://"+ossParamDTO.getBucketName()+"."+ossParamDTO.getEndpoint()+"/"+ossParamDTO.getObjectName();
        return url;
    }

    /**
     * oss文件删除
     * @param ossParamDTO
     */
    public static void   deleteFileFromOss(OssParamDTO ossParamDTO){
        OSS ossClient = new OSSClientBuilder().build(ossParamDTO.getEndpoint(), ossParamDTO.getAccessKeyId(), ossParamDTO.getAccessKeySecret());
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(ossParamDTO.getBucketName(), ossParamDTO.getObjectName());
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 线程计算  单线程处理5M
     * @param fileSize
     * @return
     */
    public static Integer taskNumber(Long fileSize){
        Long standard = 5*1024*1024L;
        if(fileSize< standard){
            return 1;
        }else {
            Long number = fileSize / standard;
            return number.intValue();
        }
    }
}
