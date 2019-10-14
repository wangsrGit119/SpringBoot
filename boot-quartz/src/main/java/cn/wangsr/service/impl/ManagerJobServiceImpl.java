package cn.wangsr.service.impl;

import cn.wangsr.dao.MyJobMapper;
import cn.wangsr.entity.JobDo.JobAndTriggerInfoDo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: wjl
 * @description:
 * @time: 2019/9/25 0025 16:34
 */
@Service
public class ManagerJobServiceImpl {

    @Autowired
    MyJobMapper myJobMapper;

    /**
     * @Description  获取所有job信息
     * @author wjl
     * @date 2019/9/25 0025
     * @param [pageNum, pageSize]
     * @return java.util.List<cn.wangsr.entity.JobDto.JobAndTriggerDto>
     */
    public PageInfo<JobAndTriggerInfoDo> getAllJobAndTrigger(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List  listDo= myJobMapper.getAllJobAndTrigger();
        PageInfo<JobAndTriggerInfoDo> pageInfoResult=new PageInfo<>(listDo);
        return  pageInfoResult;
    }


    /**
     * @Description   判断是否已有
     * @author wjl
     * @date 2019/9/30 0030
     * @param [jobName]
     * @return boolean
     */
    public boolean getJobInfoByJobName(String jobName){
      JobAndTriggerInfoDo jobAndTriggerInfoDo =  myJobMapper.getJobInfoByJobName(jobName);
      if(jobAndTriggerInfoDo==null){
          return  false;
      }
      return true;
    }



}
