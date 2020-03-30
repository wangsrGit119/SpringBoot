package cn.wangsr.oss.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.CountDownLatch;

/**
 * @author: wjl
 * @description: 文件扩展接口 2
 * @time: 2020/1/17 0017 14:47
 */
@Service
@Slf4j
public class FileServiceExtAsyncImpl {



    PartETag getUploadPartETag(String objectName, String bucketName, String uploadId,
                               InputStream instream, Long curPartSize,Integer partNum,
                               OSS ossClient, CountDownLatch countDownLatch){
        long before = System.currentTimeMillis();
        UploadPartRequest uploadPartRequest = null;
        try {
            log.debug("分片文件上传线程： {}",Thread.currentThread().getName());
            uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName(bucketName);
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber(partNum);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
           log.debug("getPartETag  ::{}" ,uploadPartResult.getPartETag().getETag());
           return uploadPartResult.getPartETag();
        }finally {
            countDownLatch.countDown();
            log.debug("线程： {}  执行完毕, 等待线程数 ：{}, 消耗时间： {}",
                    Thread.currentThread().getName(),countDownLatch.getCount(),
                    ((System.currentTimeMillis()-before)/1000)+"s");
        }
    }

}
