package cn.wangsr.utils;

import cn.wangsr.socket.WebSocket;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.javassist.bytecode.ByteArray;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * @author: wjl
 * @description:
 * @time: 2019/9/29 0029 10:11
 */
public class SocketUtils {

    private  static  String encoding = System.getProperty("file.encoding");


    /**
     * @Description  socket同步
     * @author wjl
     * @date 2019/9/29 0029
     * @param [webSocket, filePath, userName]
     * @return void
     */
    public static void sendLogsToFront(WebSocket webSocket,String filePath,String userName){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            InputStreamReader sr = new InputStreamReader(fileInputStream);
            BufferedReader br=new BufferedReader(sr);
            String line;
            while ((line = br.readLine()) != null) {
                webSocket.sendMessageOneToOne(line,userName);
            }
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static byte[] toByteArray(String filename) {

        File f = new File(filename);
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

          return bos.toByteArray();
    }

    public static void main(String[] args) {
//        sendLogsToFront(null,"D:\\hdfsTemp\\2019-9-29-1569735834321-execute.log",null);
        byte bytes[]=toByteArray("D:\\hdfsTemp\\2019-9-29-1569737053831-wangsr-execute.log");
        System.out.println(new String(bytes));
    }
}
