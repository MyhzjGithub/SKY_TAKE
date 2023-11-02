package com.source.data.server.dao.dish;

import com.pojo.dish.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    List<DishFlavor> getDishId(Long id);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteDishFlavorId(Long dishId);

    void insertDishFlavor(List<DishFlavor> flavors);
}
