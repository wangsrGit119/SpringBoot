package cn.wangsr.springbootupload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/file")

public class uploadController {

    Logger logger= LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/index")
    public String toIndex(Model model){
        String path="D:/files";

        File dir=new File(path);
        File[] lists= dir.listFiles();
        List list=new ArrayList();

        Arrays.stream(lists).forEach(filename->{
            list.add(filename.getName());
        });

        model.addAttribute("files",list);


        return "showFiles";
    }

    /**
     * @Author wjl
     * @Description  单文件上传
     * @Date 14:51 2018/11/6
     * @Param
     * @return
     **/
    @PostMapping("/addFile")
    @ResponseBody
  public String upFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){


        Long start=System.currentTimeMillis();
        try{
            if(file.isEmpty())
                return "no file";
            String originalFilename=file.getOriginalFilename();
            logger.info(originalFilename);
            String suffixname=originalFilename.substring(originalFilename.lastIndexOf("."));

            String filePath="D:/files/";
            String path=filePath+originalFilename;

            File dir=new File(path);
            logger.info(path);
            if(!dir.getParentFile().exists())
                dir.getParentFile().mkdir();
            file.transferTo(dir);

//
//            InputStream in=file.getInputStream();
//            FileOutputStream out=new FileOutputStream(dir);
//            byte[] buff=new byte[1024];
//            int b;
//            while((b=in.read(buff))!=-1){
//                out.write(buff,0,b);
//            }
//           in.close();
//            out.close();


            System.out.println("time:"+(System.currentTimeMillis()-start));
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "failed";
  }

/**
 * @Author wjl
 * @Description  多文件上传
 * @Date 16:26 2018/11/6
 * @Param
 * @return
 **/
   @PostMapping("/addFiles")
   @ResponseBody
  public String uploadFiles(@RequestParam("files") List<MultipartFile> files){
       if(files.isEmpty())
           return "no file";

       files.forEach(file->{
           try{
               String originalFilename=file.getOriginalFilename();
               logger.info(originalFilename);
               String suffixname=originalFilename.substring(originalFilename.lastIndexOf("."));

               String filePath="D:/files/";
               String path=filePath+originalFilename;

               File dir=new File(path);
               logger.info(path);
               if(!dir.getParentFile().exists())
                   dir.getParentFile().mkdir();
               file.transferTo(dir);
           }catch (Exception e){
               e.printStackTrace();
           }
       });


        return "suc";
  }




  @GetMapping("/testDownload")
  @ResponseBody
  public  String  downloadFile(HttpServletRequest request,HttpServletResponse response){
      response.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      response.setContentType("multipart/form-data");


      String url = request.getParameter("url");


     logger.info(url);

      String path="D:/files";
      File dir=new File(path);

      if (dir.isDirectory()){
          Arrays.stream(dir.listFiles()).forEach(filename->{
              try{
                  response.setHeader("Content-Disposition", "attachment;fileName="+ filename);
                  FileInputStream in=new FileInputStream(filename);
                  OutputStream out= response.getOutputStream();
                  byte[] buff=new byte[1024];
                  int b;
                  while((b=in.read(buff))!=-1){
                      out.write(buff,0,b);
                  }
                  in.close();
                  out.close();
              }catch (Exception e){
                  e.printStackTrace();
              }
          });
      }

    return  "ok";

  }


}
