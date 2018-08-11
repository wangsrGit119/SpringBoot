package cn.wangsr.mapper;

import java.util.List;

import cn.wangsr.entits.Bar;
import cn.wangsr.entits.Bar_list;

public interface BarMapper {
	
	public Bar getBarByRoId(Integer id);//with Bar_list
	
	public Bar_list getAllById(Integer id);//通过bar表的id查询barlist的内容
 
	public List<Bar> getAllBar();
	
	
	//==============对资源的操作==========================
	
	public List<Bar_list> getAllBar_List();
	public Bar_list updateBar_List();
	public int addBar_List(Bar_list bar_list);
	public int deleteBar_List(int id);

	public void addBar(Bar bar);
	
}
