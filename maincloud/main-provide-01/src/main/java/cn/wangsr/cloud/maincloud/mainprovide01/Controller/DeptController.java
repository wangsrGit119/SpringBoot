package cn.wangsr.cloud.maincloud.mainprovide01.Controller;


import cn.wangsr.cloud.maincloud.mainapi.bean.Dept;
import cn.wangsr.cloud.maincloud.mainprovide01.Service.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {

    @Autowired
    DeptServiceImpl deptServiceImple;

    @RequestMapping("/provide/getAll")
    @ResponseBody
    public Dept  getAll(@RequestBody Dept dept){

        Dept dept1= deptServiceImple.getAll(dept);

        System.out.println("provide01:"+dept);

        return  dept1;
    }

}
