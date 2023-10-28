package com.source.data.server.dao.category;

import com.pojo.Query.categoryQuery;
import com.pojo.category.Category;
import com.utils.Annotation.AutoFill;
import com.utils.typeUtils.TYPE;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface categoryMapper {
    @Select("select count(*) from category")
    Integer getCategoryCount();


    List<Category> Pages(categoryQuery query);

    @AutoFill(TYPE.INSERT)
    void insertCategory(Category category);

    @Update("update category set status = #{status} where id = #{id}")
    void setCategoryStatus(Integer status, Long id);

    @Delete("delete from category where id = #{id}")
    void deleteCategory(Long id);

    List<Category> selectCategory(Integer type);

    @AutoFill(TYPE.UPDATE)
    void updateCategory(Category category);

    @Select("select id, type, name, sort, status, create_time, update_time, " +
            "create_user, update_user from category where id = #{categoryId}")
    Category getCategoryId(Long categoryId);
}
