package cn.wangsr.cloud.maincloud.mainprovide02.Service;


import cn.wangsr.cloud.maincloud.mainapi.bean.Dept;
import cn.wangsr.cloud.maincloud.mainapi.service.DeptService;
import org.springframework.stereotype.Service;


@Service
public class DeptServiceImpl implements DeptService {

    @Override
    public Dept getAll(Dept dept) {

        dept.setEmail("44444@qq.com");
        dept.setId(1);
        dept.setName("wangsr");


        return dept;
    }
}
