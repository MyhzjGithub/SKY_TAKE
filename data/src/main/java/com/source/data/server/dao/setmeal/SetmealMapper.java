package com.source.data.server.dao.setmeal;

import com.pojo.Query.setmealQuery;
import com.pojo.setmeal.WEBsetmeal.SetmealPage;
import com.pojo.setmeal.setmeal;
import com.utils.Annotation.AutoFill;
import com.utils.typeUtils.TYPE;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SetmealMapper {
    @Select("select count(*) from setmeal")
    Integer getCount();

    List<SetmealPage> Page(setmealQuery query);

    @AutoFill(TYPE.INSERT)
    void insertSetmeal(setmeal data);

    @Update("update setmeal set status = #{status} , update_time = #{updateTime} where id = #{id}")
    void updateStatus(Integer status, Long id, LocalDateTime updateTime);

    @Delete("delete from setmeal where id = #{id}")
    void deleteId(Long id);

    @Select("select id, category_id, name, price, status, description, image, create_time, " +
            "update_time, create_user, update_user from setmeal where id = #{id}")
    setmeal SelectId(Long id);

    @AutoFill(TYPE.UPDATE)
    void updateSetmeal(setmeal setmeal);

    @Select("select * from setmeal where category_id = #{categoryId} and status = #{status}")
    List<setmeal> selectCategoryId(Long categoryId, Integer status);
}
