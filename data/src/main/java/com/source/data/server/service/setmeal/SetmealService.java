package com.source.data.server.service.setmeal;

import com.pojo.Page.Pages;
import com.pojo.Query.setmealQuery;
import com.pojo.setmeal.WEBsetmeal.SetmealPage;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Insert;
import com.pojo.setmeal.WEBsetmeal.Setmeal_Select;
import com.pojo.setmeal.WEBsetmeal.Setmeal_update;
import com.pojo.setmeal.setmeal;

import java.util.List;

public interface SetmealService {
    /**
     * 分页查询
     * @param query
     * @return
     */
    Pages<SetmealPage> Page(setmealQuery query);

    /**
     * 新增套餐数据
     * @param data
     */
    void insertSetmeal(Setmeal_Insert data);

    /**
     * 修改套餐状态
     * @param status
     * @param id
     */
    void updateStatus(Integer status, Long id);

    /**
     * 根据id删除套餐
     * @param ids
     */
    void delete(Long[] ids);

    /**
     * 根据id查询对应的套餐信息
     * @param id
     * @return
     */
    Setmeal_Select selectID(Long id);

    /**
     * 修改套餐数据
     * @param setmealUpdate
     */
    void updateSetmeal(Setmeal_update setmealUpdate);

    /**
     * 根据分类id查询套餐信息
     * @param categoryId
     * @return
     */
    List<setmeal> selectCategoryId(Long categoryId);
}
