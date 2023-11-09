package com.source.data.server.dao.shoppingCart;

import com.pojo.shopping.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Select("select id, name, image, user_id, dish_id, setmeal_id, dish_flavor, " +
            "number, amount, create_time from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> getUseridShoppingCart(Long userId);

    @Select("select id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, " +
            "amount, create_time from shopping_cart where dish_id = #{dishId} and user_id = #{userId}")
    ShoppingCart getDishIDShoppingCart(Long dishId, Long userId);

    @Select("select id, name, image, user_id, dish_id, setmeal_id, dish_flavor, number, " +
            "amount, create_time from shopping_cart where setmeal_id = #{setmealId} and user_id = #{userId}")
    ShoppingCart getSetmealIDShoppingCart(Long setmealId, Long userId);

    void insert(ShoppingCart shoppingCart);

    void update(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where id = #{id}")
    void DeleteShoppingCart(ShoppingCart shoppingCart);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void DeleteShoppingCarts(Long userId);

    void inserts(List<ShoppingCart> shoppingCartList);
}
