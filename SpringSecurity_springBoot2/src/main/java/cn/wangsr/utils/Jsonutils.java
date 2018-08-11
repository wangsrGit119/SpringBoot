package cn.wangsr.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class Jsonutils {
	
	public static String getJSONData(int code,String msg,Long count,Object data) {
//		//这里转换日期格式为yyyy-MM-dd
//		JsonConfig config = new JsonConfig();
//		JsonDateValueProcessor jsonDateValueProcessor  = new JsonDateValueProcessor();
//		config.registerJsonValueProcessor(Date.class, jsonDateValueProcessor);
		
		Object js = JSONArray.toJSON(data);
		String result = "{\"code\":\""+code+"\",\"msg\":\""+msg+"\",\"count\":"+count+",\"data\":"+js+"}";
		System.out.println(result);
		return result;
		
		
	}
	
	public static void main(String[] args) {
		
		int[] ids= {1,2,3,4,5};
		//转成json过程
		Object js = JSONArray.toJSON(ids);
		String result = "{\"code\":\""+0+"\",\"data\":"+js+"}";
		System.out.println(result);
		//逆过程
		JSONObject arr2=JSONObject.parseObject(result);
		JSONArray arr3=arr2.getJSONArray("data");
		
		System.out.println(arr3);
		
	}

}
