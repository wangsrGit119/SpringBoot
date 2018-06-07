package cn.itcast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.itcast.entities.Department;
import cn.itcast.entities.Employee;

@Mapper
public interface EmplMapper {
	
	
	
	
	@Select("select * from employee where id=#{id}")
	@Results({  
		//其他的属性默认就可以   内嵌对象需要特殊处理不然得到的结果中不含department
//		   @Result(property="id",column="id"),
//		   @Result(property="lastName",column="lastName"),
//		   @Result(property="email",column="email"),
//		   @Result(property="birth",column="birth"),
//		   @Result(property="gender",column="gender"),
		   @Result(property="department",column="d_id",
		   one=@One(select="cn.itcast.mapper.DeptMapper.getDeptById")) 
		   })
	public Employee getEmpById(Integer id);
	
	
	@Select("select * from employee")
	@Results({
		@Result(property="department",column="d_id",
		one=@One(select="cn.itcast.mapper.DeptMapper.getDeptById"))
	})
	public List<Employee> getAllEmp();
	
	
	 @Options(useGeneratedKeys = true,keyProperty = "id")
	@Insert("insert into employee(lastName,gender,birth,email,d_id) values(#{lastName},#{gender},#{birth},#{email},#{department.id})")
	public int insertEmpl(Employee employee);
	
	 @Delete("delete from employee where id=#{id}")
		public int deleteEmpById(Integer id); 
	 
	 @Update("update employee set "
	 		+ "lastName=#{lastName},"
	 		+ "email=#{email},"
	 		+ "gender=#{gender},"
	 		+"birth=#{birth},"
	 		+ "d_id=#{department.id}"
	 		+ " where id=#{id}")
	/* @Results({
		 @Result(property="department",column="d_id",
					one=@One(select="cn.itcast.mapper.DeptMapper.getDeptById"))
	 })*/
	 public int updateEmpById(Employee employee);

}
