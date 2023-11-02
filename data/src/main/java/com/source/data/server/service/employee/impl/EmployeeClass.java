package com.source.data.server.service.employee.impl;


import com.pojo.Page.Pages;
import com.pojo.Query.employeeQuery;
import com.pojo.employee.Employee;
import com.pojo.employee.webEmployee.Empinsert;
import com.pojo.employee.webEmployee.EmployeeLogin;
import com.pojo.employee.webEmployee.EmpeditPassword;
import com.source.data.server.dao.employee.EmployeeMapper;
import com.source.data.server.service.employee.EmployeeService;
import com.utils.ErrorUtils.Message;
import com.utils.ExceptionUtils.NullUserException;
import com.utils.ExceptionUtils.PasswordException;
import com.utils.ExceptionUtils.UsernameException;
import com.utils.PasswordUtils.defaultPassword;
import com.utils.StatusUtils.DefaultStatus;
import com.utils.ThreadUtils.BeanThread;
import com.utils.PageUtils.startPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 员工接口service
 */
@Service
public class EmployeeClass implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    @Override
    public Employee getEmployee(EmployeeLogin employee) {
        Employee emp = mapper.getEmployee(employee.getUsername(),null);    // 根据用户名获取员工信息
        if (emp == null){
            throw new UsernameException(Message.USERNAME_ERROR);
        }
        String password = employee.getPassword();   // 获取前端输入的密码
        String empPassword = emp.getPassword();     // 根据用户名匹配的密码
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());   // 对密码进行md5加密
        if (md5Password.equals(empPassword) && emp.getStatus() == 1){   // 密码正确 并且账号未被禁用 可以放行
            return emp;
        }else {
            throw new PasswordException(Message.PASSWORD_ERROR);
        }
    }

    @Override
    public void setNEWpassword(EmpeditPassword emPeditPassword) {
        Employee emp = mapper.getEmployee(null, BeanThread.getCurrentId().toString());
        if (emp == null){
            throw new NullUserException(Message.NULLUSER_ERROR);
        }
        // 旧密码使用MD5加密之后与数据库密码进行对比
        String md5Password = DigestUtils.md5DigestAsHex(emPeditPassword.getOldPassword().getBytes());
        if (md5Password.equals(emp.getPassword())){
            // 对新密码进行加密后更新新数据库
            mapper.updatePassword(BeanThread.getCurrentId(),DigestUtils.md5DigestAsHex(emPeditPassword.getNewPassword().getBytes()));
        }else {
            throw new PasswordException(Message.PASSWORD_ERROR);
        }
    }

    @Override
    public Pages<Employee> Page(employeeQuery empQuery) {
        Integer count = mapper.getEmployeeCount();
        // 起始索引 = (查询页码 - 1) * 查询记录页
        Integer page = startPage.getStartPage(empQuery.getPage(), empQuery.getPageSize());
        empQuery.setPage(page);
        List<Employee> list = mapper.Page(empQuery);
        return new Pages<>(count, list);
    }

    @Override
    public Employee getEmployeeId(Long id) {
        return mapper.getEmployee(null, id.toString());
    }

    @Override
    public void insertEmployee(Empinsert empinsert) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(empinsert, employee);  // 属性拷贝
        employee.setStatus(DefaultStatus.ONE);  // 设置状态
        String password = DigestUtils.md5DigestAsHex(defaultPassword.DEFAULT_PASSWORD.getBytes());   // 设置为默认密码
        employee.setPassword(password);
        mapper.insertEmployee(employee);
    }

    @Override
    public void updateEmployee(Empinsert empinsert) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(empinsert, employee);  // 属性拷贝
        employee.setStatus(DefaultStatus.ONE);  // 设置状态
        mapper.updateEmployee(employee);
    }

    @Override
    public void updateEmployeeStatus(Integer status, Long id) {
        mapper.updateStatus(status, id, LocalDateTime.now());
    }
}
