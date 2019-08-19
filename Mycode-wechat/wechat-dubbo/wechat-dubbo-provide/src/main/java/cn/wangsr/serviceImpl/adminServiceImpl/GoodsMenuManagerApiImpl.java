package cn.wangsr.serviceImpl.adminServiceImpl;

import cn.wangsr.adminOrderDo.AdminGoodsClassifyDo;
import cn.wangsr.adminOrderDo.AdminGoodsDo;
import cn.wangsr.adminOrderDto.AdminGoodsClassifyDto;
import cn.wangsr.adminOrderDto.AdminGoodsDto;
import cn.wangsr.api.adminApi.GoodsMenuManagerApi;
import cn.wangsr.mapper.adminMapper.AdminGoodsMenuManager;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(version = "1.0.0")
public class GoodsMenuManagerApiImpl implements GoodsMenuManagerApi {


    @Autowired
    AdminGoodsMenuManager adminGoodsMenuManager;

    Logger logger= LoggerFactory.getLogger(GoodsMenuManagerApiImpl.class);
    @Override
    public List<AdminGoodsClassifyDto> getAllGoodsClassify() {
        List<AdminGoodsClassifyDo> listClassify=  adminGoodsMenuManager.getGoodsClassify();
        logger.info(JSON.toJSONString(listClassify));
        List<AdminGoodsClassifyDto> list= JSONObject.parseObject(JSON.toJSONString(listClassify),List.class);
        return list;
    }

    @Override
    public String insertGoodsClassify(AdminGoodsClassifyDto goodsClassifyDto) {
        AdminGoodsClassifyDo goodsClassifyDo=new AdminGoodsClassifyDo();
        BeanUtils.copyProperties(goodsClassifyDto,goodsClassifyDo);
       int result= adminGoodsMenuManager.insertGoodsClassify(goodsClassifyDo);
        return JSON.toJSONString(result);
    }

    @Override
    public String insertGoodsInfo(AdminGoodsDto adminGoodsDto) {
        AdminGoodsDo adminGoodsDo=new AdminGoodsDo();
        BeanUtils.copyProperties(adminGoodsDto,adminGoodsDo);
        int result=adminGoodsMenuManager.insertGoodsInfo(adminGoodsDo);
        return JSON.toJSONString(result);
    }

    @Override
    public String deleGoodsByGoodsId(Integer goodsId) {
        int result= adminGoodsMenuManager.deleteGoodsById(goodsId);
        return JSON.toJSONString(result);
    }
}
