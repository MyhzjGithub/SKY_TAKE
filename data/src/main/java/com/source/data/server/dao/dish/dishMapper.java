package com.source.data.server.dao.dish;

import com.pojo.Query.dishQuery;
import com.pojo.dish.Dish;
import com.pojo.dish.WEBdish.Dish_page;
import com.pojo.dish.WEBdish.Dish_public;
import com.utils.Annotation.AutoFill;
import com.utils.typeUtils.TYPE;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface dishMapper {

    @Select("select count(*) from dish")
    Integer getDishCount();


    List<Dish_page> Pages(dishQuery page);

    @Select("select id, name, category_id, price, image, description, " +
            "status, create_time, update_time, create_user, update_user " +
            "from dish where id = #{id}")
    Dish getDishID(Long id);

    @AutoFill(TYPE.UPDATE)
    void updateDish(Dish_public dishPublic);
}
