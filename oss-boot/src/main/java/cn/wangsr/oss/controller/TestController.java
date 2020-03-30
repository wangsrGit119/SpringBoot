package cn.wangsr.oss.controller;

import cn.wangsr.oss.impl.FIleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wjl
 */
public class TestController {


    @Autowired
    FIleServiceImpl fIleService;

    @RequestMapping("/upload")
    public void testUpload(MultipartFile file){


    }

}
