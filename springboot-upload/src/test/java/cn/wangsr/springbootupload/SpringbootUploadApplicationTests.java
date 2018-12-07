package cn.wangsr.springbootupload;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootUploadApplicationTests {




    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void addInfo(){

        redisTemplate.opsForValue().set("aa","哈哈哈哈");

    }



    @Test
    public void getFiles(){
        try{
            FileInputStream in = new FileInputStream("D:\\files\\cms.txt");
            OutputStream out= new FileOutputStream("D:\\files\\cms3.txt");
            byte[] buff = new byte[1024];
            int b;
            while((b=in.read(buff)) != -1) {
                System.out.println(buff);
                out.write(buff,0,b);
            }
            in.close();
            out.close();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Test
    public void contextLoads() {

        String path="D:/files";
        String path2="file_image\\anc";
        File dir=new File(path);

        if (dir.isDirectory()){
            Arrays.stream(dir.listFiles()).forEach(filename->{
                try{
                    FileInputStream in=new FileInputStream(filename);
                    File dir2=new File(filename.toString().replace("files",path2));
                     System.out.println(filename.toString().replace("files",path2));
                    System.out.println(dir2.getParentFile());
                     if(!dir2.getParentFile().exists())
                         dir2.getParentFile().mkdir();
                    FileOutputStream out= new FileOutputStream(dir2);
                    byte[] buff=new byte[1024];
                    int b;
                    while((b=in.read(buff))!=-1){
                        out.write(buff,0,b);
                        String result=new String(buff,0,b);
                    }
                    in.close();
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }


    }

}
