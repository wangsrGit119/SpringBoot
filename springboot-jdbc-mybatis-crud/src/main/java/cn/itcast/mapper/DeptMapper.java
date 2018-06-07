package cn.itcast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.itcast.entities.Department;

@Mapper
public interface DeptMapper {
	
	
	@Select("select * from department where id=#{id}")
	public Department getDeptById(Integer id);
	
	
	@Delete("delete from department where id=#{id}")
	public int deleteDeptById(Integer id);
	
	@Select("select * from department")
	public List<Department> getAllDept();
	
  	@Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);
	

}
