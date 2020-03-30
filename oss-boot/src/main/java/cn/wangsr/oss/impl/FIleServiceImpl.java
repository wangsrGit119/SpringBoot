package cn.wangsr.oss.impl;

import cn.wangsr.oss.dto.OssParamDTO;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author wjl
 */
@Service
@Slf4j
public class FIleServiceImpl {
    @Qualifier("taskExecutor")
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    FileServiceExtAsyncImpl fileServiceExtAsync;
    /** 阿里oss  */
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.folder}")
    private String folder;
    /**
     * oss分片上传
     * @param ossParamDTO
     * @param multipartFile
     * @return
     */
    public CompleteMultipartUploadResult uploadBigFileForProd(OssParamDTO ossParamDTO, MultipartFile multipartFile){
        Long before = System.currentTimeMillis();
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ossParamDTO.getEndpoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = ossParamDTO.getAccessKeyId();
        String accessKeySecret = ossParamDTO.getAccessKeySecret();
        String bucketName = ossParamDTO.getBucketName();
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = ossParamDTO.getObjectName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建InitiateMultipartUploadRequest对象。
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, objectName);

        // 初始化分片。
        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
        // 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = upresult.getUploadId();

        // partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags =  new ArrayList<>();
        // 计算文件有多少个分片 15MB
        final long partSize = 2 * 1024 * 1024L;
        long fileLength = multipartFile.getSize();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        // 遍历分片上传。
        log.info("分片数量  {}",partCount);
        List<Future<PartETag>> futureList = Collections.synchronizedList(new ArrayList());
        CountDownLatch countDownLatch = new CountDownLatch(partCount);
        for (int i = 0; i < partCount; i++) {
            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = null;
            try {
                instream = multipartFile.getInputStream();
            }  catch (IOException e) {
                e.printStackTrace();
            }
            // 跳过已经上传的分片。
            try {
                instream.skip(startPos);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int finalI = i;
            InputStream finalInstream = instream;
            Future<PartETag> partETagFuture = taskExecutor.submit(() ->
                    fileServiceExtAsync.getUploadPartETag(objectName, bucketName, uploadId, finalInstream, curPartSize, finalI + 1, ossClient, countDownLatch));
            futureList.add(partETagFuture);
        }
        try {
            countDownLatch.await();
            for (Future<PartETag> tagFuture : futureList) {
                partETags.add(tagFuture.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 创建CompleteMultipartUploadRequest对象。
        List<PartETag> collect = partETags.stream().sorted(Comparator.comparing(PartETag::getPartNumber)).collect(Collectors.toList());
        // 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        log.debug("文件开始合并");
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, collect);

        // 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
        completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);
        // 完成上传。
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
        log.debug("消耗总时间：  {}",((System.currentTimeMillis()-before)/1000)+"s");
        return completeMultipartUploadResult;
    }


    public void uploadOrdinaryFile(MultipartFile multipartFile){
        // 计算文件有多少个分片 15MB
        final long partSize = 2 * 1024 * 1024L;
        long fileLength = multipartFile.getSize();
        int partCount = (int) (fileLength / partSize);
        if (fileLength % partSize != 0) {
            partCount++;
        }
        for (int i = 0; i < partCount; i++) {

        }

    }


}
