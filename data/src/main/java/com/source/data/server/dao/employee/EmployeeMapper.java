package com.source.data.server.dao.employee;

import com.pojo.Query.EmployeeQuery;
import com.pojo.employee.Employee;
import com.utils.Annotation.AutoFill;
import com.utils.typeUtils.TYPE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名匹配员工信息并返回
     * @param empId   id
     * @param username 用户名
     * @return
     */
    Employee getEmployee(String username , String empId);
    /**
     * 修改密码
     */
    void updatePassword(Long empId, String newPassword);

    /**
     * 统计
     * @return
     */
    @Select("select count(*) from employee")
    Integer getEmployeeCount();

    /**
     * 分页查询
     * @param empQuery
     * @return
     */
    List<Employee> Page(EmployeeQuery empQuery);

    /**
     * 新增员工信息
     * @param employee
     */
    @AutoFill(value = TYPE.INSERT)  // 开启公共字段填充
    void insertEmployee(Employee employee);

    /**
     * 修改员工信息
     */
    @AutoFill(value = TYPE.UPDATE)
    void updateEmployee(Employee employee);

    /**
     * 修改员工状态
     * @param status
     * @param id
     */
    @Update("update employee set status = #{status} , update_time = #{updateTime} where id = #{id}")
    void updateStatus(Integer status, Long id, LocalDateTime updateTime);
}
