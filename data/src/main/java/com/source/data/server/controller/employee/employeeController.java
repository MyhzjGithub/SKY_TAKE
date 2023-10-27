package com.source.data.server.controller.employee;

import com.pojo.Page.PAGE;
import com.pojo.RESULT.Result;
import com.pojo.employee.Employee;
import com.pojo.employee.webEmployee.*;
import com.source.data.server.service.employee.employeeService;
import com.utils.JwtUtils.JWTMessage;
import com.utils.JwtUtils.JWTUtils;
import com.utils.JwtUtils.applicationConfigProperties.JwtProperties;
import com.utils.ThreadUtils.BeanThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工相关接口
 */
@RestController
@Slf4j
@RequestMapping("/admin/employee")
public class employeeController {
    @Autowired
    private employeeService empService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result login(@RequestBody EmployeeLogin employeeLogin) {
        log.info("登入 : username {} , password , {}", employeeLogin.getUsername(), employeeLogin.getPassword());
        Employee employee = empService.getEmployee(employeeLogin);
        // 构建JWT令牌
        Map<String, Object> map = new HashMap<>();
        map.put(JWTMessage.EMP_ID, employee.getId());
        String jwt = JWTUtils.createJWT(jwtProperties.getPasswordKey(), jwtProperties.getTime(), map);
        EmployeeVO dto = new EmployeeVO();
        dto.setId(employee.getId());

        dto.setName(employee.getName());
        dto.setUserName(employee.getUsername());
        dto.setToken(jwt);  // 加入令牌
        return Result.success(dto);
    }

    @PostMapping("/logout")
    public Result logout(){
        log.info("当前用户退出系统");
        BeanThread.removeCurrentId();
        return Result.success();
    }

    @PutMapping("/editPassword")
    public Result updatePassword(@RequestBody EmpeditPassword emPeditPassword){
        log.info("修改密码 新密码为 : {}",emPeditPassword.getNewPassword());
        empService.setNEWpassword(emPeditPassword);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(EmpQuery empQuery){
        log.info("分页查询 {}",empQuery);
        PAGE<Employee> page = empService.Page(empQuery);
        return Result.success(page);
    }
    @PostMapping
    public Result insertEmployee(@RequestBody Empinsert empinsert){
        log.info("新增员工信息 : {}",empinsert);
        empService.insertEmployee(empinsert);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getEmployee(@PathVariable("id") Long id){
        log.info("查询id为{}的员工信息",id);
        Employee employee = empService.getEmployeeId(id);
        return Result.success(employee);
    }

    @PutMapping
    public Result updateEmployee(@RequestBody Empinsert empinsert){
        log.info("修改员工信息 : {}",empinsert);
        empService.updateEmployee(empinsert);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result SetStatus(@PathVariable("status") Integer status ,  Long id){
        log.info("修改员工状态");
        empService.updateEmployeeStatus(status,id);
        return Result.success();
    }

}
