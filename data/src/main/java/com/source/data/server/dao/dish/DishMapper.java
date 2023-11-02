package com.source.data.server.dao.dish;

import com.pojo.Query.dishQuery;
import com.pojo.dish.Dish;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;
import com.utils.Annotation.AutoFill;
import com.utils.typeUtils.TYPE;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DishMapper {

    @Select("select count(*) from dish")
    Integer getDishCount();


    List<Dish_page> Pages(dishQuery page);

    @Select("select id, name, category_id, price, image, description, " +
            "status, create_time, update_time, create_user, update_user " +
            "from dish where id = #{id}")
    Dish getDishID(Long id);

    @AutoFill(TYPE.UPDATE)
    void updateDish(Dish_public dishPublic);

    @AutoFill(TYPE.INSERT)
    void insertDish(Dish dish);

    @Update("update dish set status = #{status} , update_time = #{updateTime} where id = #{id}")
    void setDishStatus(Integer status, Long id, LocalDateTime updateTime);

    @Delete("delete from dish where id = #{id}")
    void deleteId(Long id);

    @Select("select id, name, category_id, price, image, description, status, create_time, " +
            "update_time, create_user, update_user from dish where category_id = #{categoryId} and status = #{status}")
    List<Dish> getCategoryId(Long categoryId, Integer status);

    @Select("select id, name, category_id, price, image, description, status, create_time, update_time, " +
            "create_user, update_user from dish where name = #{name}")
    Dish getDishName(String name);
}
