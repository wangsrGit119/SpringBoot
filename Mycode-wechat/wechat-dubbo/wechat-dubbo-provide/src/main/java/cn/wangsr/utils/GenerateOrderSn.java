package cn.wangsr.utils;

import cn.wangsr.adminOrderDto.AdminQueryDto;
import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GenerateOrderSn {
	
	
	public static String  generateOrderSnByTime() {
		LocalDateTime dateTime=LocalDateTime.now();
		LocalDateTime.now().getDayOfYear();
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("SN");
		stringBuffer.append(dateTime.getYear());
		stringBuffer.append(String.format("%02d", dateTime.getMonthValue()));
		stringBuffer.append(String.format("%02d",dateTime.getDayOfMonth()));
		stringBuffer.append(String.format("%02d",dateTime.getHour())).append(String.format("%02d",dateTime.getMinute())).append(String.format("%02d",dateTime.getSecond()));
		stringBuffer.append(String.format("%04d",dateTime.getMinute()+dateTime.getSecond()));
		return String.valueOf(stringBuffer);
	}
	
	
	public static void main(String[] args) {
		Byte status[]={0,1};
		Set<Byte> set=new HashSet<>();
		set.addAll(Arrays.asList(status));
		AdminQueryDto adminQueryDo=new AdminQueryDto();
		adminQueryDo.setAllStatus(set);
		adminQueryDo.setKeys("s");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="2019-01-09 20:44:46";
		try{
			adminQueryDo.setStartTime(format.parse(time));
		}catch (Exception e){
			throw new RuntimeException(e);
		}

		adminQueryDo.setEndTime(new Date());


	   System.out.println(JSON.toJSONString(adminQueryDo));
	}

}
