package cn.wangsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangsr.entits.Bar;
import cn.wangsr.entits.Bar_list;
import cn.wangsr.mapper.BarMapper;

@Service
public class BarServices {
	
	@Autowired
	BarMapper barMapper;
	
	/**
	 * 得到栏目条
	 * @return
	 */
	public Bar getBarByRoId(Integer id){
		return  barMapper.getBarByRoId(id);
	}

	
	/**
	 * 得到所有的栏目信息以便修改
	 * @return
	 */
	public List<Bar_list> getAllBar_List() {
		return barMapper.getAllBar_List();
	}


	/**
	 * 删除栏目信息
	 * @param id
	 */
	public void deleteBLById(Integer id) {
		
		barMapper.deleteBar_List(id);
		
	}


	/**
	 * 得到所有主栏目条
	 * @return
	 */
	public List<Bar> getAllBar() {
		
		return barMapper.getAllBar();
	}


	/**
	 * 添加子栏目信息
	 * @param bar_list
	 */
	
	public void addbarList(Bar_list bar_list) {
		barMapper.addBar_List(bar_list);
		
	}

	/**
	 * 添加权限栏目
	 * @param bar
	 */

	public void addBar(Bar bar) {
		barMapper.addBar(bar);
		
	}

}
