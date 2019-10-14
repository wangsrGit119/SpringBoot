package cn.wangsr.controller;

import cn.wangsr.resultCode.ResultDto;
import cn.wangsr.service.sys.SystemInfoService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/27 0027 17:39
 */
@RestController
public class SysController {
    private static final String GIF = "image/gif;charset=UTF-8";
    private static final String JPG = "image/jpeg;charset=UTF-8";
    private static final String PNG = "image/png;charset=UTF-8";
    private static final String PDF = "application/pdf;charset=UTF-8";
    private static final String ZIP = "application/zip;charset=UTF-8";

    @Autowired
    SystemInfoService systemInfoService;
    ResultDto resultDto;

    /**
     * @Description  获取系统信息
     * @author wjl
     * @date 2019/9/29 0029
     * @param [request]
     * @return java.lang.String
     */
    @GetMapping("/getSysInfo")
    public String getSysInfo(HttpServletRequest request){
        resultDto=new ResultDto();
        Map map= systemInfoService.getSystemAllInfo();
        return JSON.toJSONString(resultDto.successDto(map));
    }

    /**
     * @Description  文件下载
     * @author wjl
     * @date 2019/9/30 0030
     * @param [path]
     * @return org.springframework.http.ResponseEntity<byte[]>
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("path") String path){
        resultDto=new ResultDto();
        String fileName=null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
           File file=new File(path);
           FileInputStream inputStream=new FileInputStream(file);
           IOUtils.copy(inputStream,byteArrayOutputStream);
            fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
        }catch (Exception e){
           e.printStackTrace();
        }
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition","attachment;filename="+fileName);
        ResponseEntity<byte[]> response=new ResponseEntity<>(byteArrayOutputStream.toByteArray(),headers, HttpStatus.CREATED);
        return response;
    }

    /**
     * @Description  日志预览
     * @author wjl
     * @date 2019/9/30 0030
     * @param [response, path]
     * @return java.lang.String
     */
    @ResponseBody
    @GetMapping("/scanFile")
    public String scanFile(HttpServletResponse response,@RequestParam("path") String path) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        File file=new File(path);
        FileInputStream inputStream=new FileInputStream(file);
        IOUtils.copy(inputStream,byteArrayOutputStream);
        if (path.endsWith(".gif")) {
            response.setContentType(GIF);
        } else if (path.endsWith(".jpg")) {
            response.setContentType(JPG);
        } else if (path.endsWith(".png")) {
            response.setContentType(PNG);
        } else if (path.endsWith(".pdf")) {
            response.setContentType(PDF);
        } else if (path.endsWith(".gif")) {
            response.setContentType(GIF);
        } else if (path.endsWith(".zip")) {
            response.setContentType(ZIP);
        }
        ServletOutputStream out=response.getOutputStream();
        out.write(byteArrayOutputStream.toByteArray());
        out.flush();
        out.close();
        return "success";
    }

    @GetMapping("/getSshLinuxIpInfo")
    public String getSshLinuxInfo(HttpServletRequest request){
        resultDto=new ResultDto();
        Map map= systemInfoService.getSSHLinuxInfo();
        return JSON.toJSONString(resultDto.successDto(map));
    }



}
