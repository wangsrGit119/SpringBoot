package cn.wangsr.dao;

import cn.wangsr.entity.JobDo.JobAndTriggerInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface MyJobMapper {


    /**
     * @Description 获取任务信息
     * @author wjl
     * @date 2019/9/26 0026
     * @param []
     * @return java.util.List<cn.wangsr.entity.JobDo.JobAndTriggerInfoDo>
     */
    List<JobAndTriggerInfoDo> getAllJobAndTrigger();


    /**
     * @Description  判断启动job之前是否存在
     * @author wjl
     * @date 2019/9/30 0030
     * @param []
     * @return cn.wangsr.entity.JobDo.JobAndTriggerInfoDo
     */
    JobAndTriggerInfoDo  getJobInfoByJobName(@Param("jobName") String jobName );




}
