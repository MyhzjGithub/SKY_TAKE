package com.source.data.server.dao.setmeal;

import com.pojo.setmeal.setmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    void insert(List<setmealDish> dishes);

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteSetmealId(Long setmealId);

    @Select("select id, setmeal_id, dish_id, name, price, copies from setmeal_dish where setmeal_id = #{setmealID}")
    List<setmealDish> selectSetmealID(Long setmealID);
}
