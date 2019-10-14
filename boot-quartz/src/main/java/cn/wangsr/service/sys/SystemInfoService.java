package cn.wangsr.service.sys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/27 0027 16:55
 */
@Service
public class SystemInfoService {

    @Value("${ssh.linux}")
    private String sshLinux;


    /**
     * @Description  获取系统信息
     * @author wjl
     * @date 2019/9/27 0027
     * @param []
     * @return java.util.Map
     */
    public Map getSystemAllInfo(){
        Map map=new HashMap();
        Properties prop = System.getProperties();
        map.put("sysInfo",prop);
        String osN=prop.getProperty("os.name");
        if(osN !=null && osN.toLowerCase().indexOf("linux") > -1 ){
            try {
                map.put("linuxMemoryInfo",getMemInfo());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    public static Map getMemInfo() throws IOException {
        Map map=new HashMap();
        File file = new File("/proc/meminfo");
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)));
        String str = null;
        StringTokenizer token = null;
        while((str = br.readLine()) != null)
        {
            token = new StringTokenizer(str);
            if(!token.hasMoreTokens())
                continue;
            str = token.nextToken();
            if(!token.hasMoreTokens())
                continue;
            if(str.equalsIgnoreCase("MemTotal:"))
                map.put(str,token.nextToken());
            else if(str.equalsIgnoreCase("MemFree:"))
                map.put(str,token.nextToken());
            else if(str.equalsIgnoreCase("MemAvailable:"))
                map.put(str,token.nextToken());
            else if(str.equalsIgnoreCase("SwapTotal:"))
                map.put(str,token.nextToken());
            else if(str.equalsIgnoreCase("SwapFree:"))
                map.put(str,token.nextToken());
        }
        return map;
    }


    public Map getSSHLinuxInfo(){
        Map map=new HashMap();
        System.out.println(sshLinux);
        List list=new CopyOnWriteArrayList();
        if(sshLinux.indexOf(",") !=-1){
            String[] linux= sshLinux.split(",");
            Arrays.stream(linux).forEach(e->{
                list.add(e);
            });
        }else {
            list.add(sshLinux);
        }
        map.put("sshLinuxCollect",list);
        return map;
    }

}
