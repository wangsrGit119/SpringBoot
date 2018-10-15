package cn.wangsr.cloud.maincloud.mainconsume01.Controller;

import cn.wangsr.cloud.maincloud.mainapi.bean.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class DeptControllerConsume {

    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://provide-01";


    @RequestMapping("/consume/getall")
    @ResponseBody
    public Dept getAll(){

     return    restTemplate.getForObject(REST_URL_PREFIX+"/provide/getAll" ,Dept.class);

    }


}
