package com.source.data.server.server_controller.setmeal;

import com.pojo.Page.Pages;
import com.pojo.Query.SetmealQuery;
import com.pojo.RESULT.Result;
import com.pojo.setmeal.WEBsetmeal.SetmealPage;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Insert;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Select;
import com.pojo.setmeal.WEBsetmeal.Setmeal_update;
import com.source.data.server.service.setmeal.SetmealService;
import com.utils.StatusUtils.DefaultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * web端 : 套餐模块
 */
@RestController("server_SetmealController")
@Slf4j
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService service;

    @GetMapping("/page")
    public Result page(SetmealQuery query){
        log.info("套餐分页查询 : {}",query);
        Pages<SetmealPage> pages = service.Page(query);
        return Result.success(pages);
    }

    @PostMapping
    @CachePut(value = "server-Setmeal",key = "#data.id")
    public Result insert(@RequestBody Setmeal_Insert data){
        log.info("新增套餐数据: {}",data);
        service.insertSetmeal(data);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @CacheEvict(value = {"server-Setmeal","client-Setmeal"} , allEntries = true)
    public Result setStatus(@PathVariable("status")Integer status, Long id){
        log.info("修改id为:{} 的套餐状态为 {}",id, status.equals(DefaultStatus.ZERO) ? "停售" : "启售");
        service.updateStatus(status,id);
        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(value = {"server-Setmeal","client-Setmeal"},allEntries = true)
    public Result delSetmeal(Long[] ids){
        log.info("删除id为 : {} 的套餐", Arrays.toString(ids));
        service.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "server-Setmeal",key = "#id")
    public Result getSetmealId(@PathVariable("id") Long id){
        log.info("查询id为: {} 的套餐信息",id);
        Setmeal_Select setmealSelect = service.selectID(id);
        return Result.success(setmealSelect);
    }

    @PutMapping
    @CacheEvict(value = {"server-Setmeal","client-Setmeal"},allEntries = true)
    public Result updateSetmeal(@RequestBody Setmeal_update setmealUpdate){
        log.info("修改套餐数据 data = {}",setmealUpdate);
        service.updateSetmeal(setmealUpdate);
        return Result.success();
    }
}
