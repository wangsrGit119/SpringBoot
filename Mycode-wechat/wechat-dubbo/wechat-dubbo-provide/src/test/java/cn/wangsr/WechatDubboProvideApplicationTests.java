package cn.wangsr;

import java.util.*;


import cn.wangsr.mapper.adminMapper.AdminOrderManager;
import cn.wangsr.serviceImpl.adminServiceImpl.OrderManagerApiImpl;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import cn.wangsr.mapper.CartInfoManager;
import cn.wangsr.mapper.ClassifyGoodsInfo;
import cn.wangsr.mapper.ClassifyGoodsItems;
import cn.wangsr.mapper.UserInfoManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatDubboProvideApplicationTests {

	@Autowired
	ClassifyGoodsInfo classifyGoodsInfo;
	@Autowired
	ClassifyGoodsItems classifyGoodsItems;
	@Autowired
	CartInfoManager cartInfoManager;
	
	@Autowired
	UserInfoManager userInfoManager;
	
	@Autowired
	AdminOrderManager adminOrderManager;

	@Autowired
	OrderManagerApiImpl orderManagerApiIml;

	
	Logger logger=LoggerFactory.getLogger(WechatDubboProvideApplicationTests.class);
	
    @Test
    public void contextLoads() {

//    	CartInformationDo cartInformationDo=new CartInformationDo();
//    	cartInformationDo.setId(UUID.randomUUID().toString());
//    	cartInformationDo.setGoodsCount(5);
//    	cartInformationDo.setGoodsId(1);
//    	cartInformationDo.setOnlyId("opgCm5Hc7cJeuTz5Bb6sIdxb4Z4A");
//
//    	WechatUserInfoDo wechatUserInfoDo=new WechatUserInfoDo();
//    	BigDecimal decimal=new BigDecimal("80");
//    	wechatUserInfoDo.setAccount(decimal);
//    	wechatUserInfoDo.setRedPackets(decimal);
//    	wechatUserInfoDo.setReceiptAddress("hh");
//    	wechatUserInfoDo.setOpenId("opgCm5Hc7cJeuTzIdxb4Z4A");
//    	userInfoManager.updateUserInfo(wechatUserInfoDo);
//    	cartInfoManager.deleteInfoFromCart("opgCm5Hc7cJeuTz5BbsIdxb4Z4A", 1);

//    	cartInfoManager.addInfoToMyCart(cartInformationDo);
//		Byte status[]={0};
//		Set<Byte> set=new HashSet<>();
//		set.addAll(Arrays.asList(status));
//		AdminQueryDo adminQueryDo=new AdminQueryDo();
//		adminQueryDo.setAllStatus(set);
//		adminQueryDo.setKeys("s");
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time="2019-01-09 20:44:46";
//		try{
//			adminQueryDo.setStartTime(format.parse(time));
//		}catch (Exception e){
//			throw new RuntimeException(e);
//		}
//
//		adminQueryDo.setEndTime(new Date());
//		logger.info("ceshishuju:"+JSON.toJSONString(adminOrderManager.getOrderInfoByKeys(adminQueryDo)));
//    logger.info("测试数据get："+ JSON.toJSONString(userInfoManager.getUserInfoByOpneId("opgCm5Hc7cJeuTz5Bb6sIdxb4Z4A")));

		logger.info("测试数据"+ JSON.toJSONString(adminOrderManager.updateOrderStatus(Arrays.asList("SN201901092033320000","SN201901092132590000"),(byte) 1)));
   
    }
  

    @Test
    public void getAdminOrderInfo(){
		String queryInfo="{\"allStatus\":[0,1,2],\"endTime\":'',\"keys\":\"s\",\"pageNum\":0,\"pageSize\":4,\"startTime\":''}";
    	String  adminOrderInfoDoList= orderManagerApiIml.getOrderByKeys(queryInfo);
    	System.out.println("adminInfo"+adminOrderInfoDoList);

	}


    
 

}

