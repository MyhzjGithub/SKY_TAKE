package com.source.data.server.service.employee;

import com.pojo.Page.PAGE;
import com.pojo.employee.Employee;
import com.pojo.employee.webEmployee.EmpQuery;
import com.pojo.employee.webEmployee.Empinsert;
import com.pojo.employee.webEmployee.EmployeeLogin;
import com.pojo.employee.webEmployee.EmpeditPassword;

public interface employeeService {

    /**
     * 根据用户名查询员工信息
     */
    Employee getEmployee(EmployeeLogin employeeLogin);

    /**
     * 修改指定账号的密码
     */
    void setNEWpassword(EmpeditPassword emPeditPassword);

    /**
     * 分页查询
     */
    PAGE<Employee> Page(EmpQuery empQuery);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Employee getEmployeeId(Long id);

    /**
     * 新增员工信息
     * @param empinsert
     */
    void insertEmployee(Empinsert empinsert);

    /**
     * 修改员工信息
     * @param empinsert
     */
    void updateEmployee(Empinsert empinsert);

    /**
     * 修改员工状态
     * @param status
     * @param id
     */
    void updateEmployeeStatus(Integer status, Long id);
}
