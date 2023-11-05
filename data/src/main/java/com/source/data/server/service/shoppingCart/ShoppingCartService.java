package com.source.data.server.service.shoppingCart;

import com.pojo.shopping.Client.ShoppingCartadd;
import com.pojo.shopping.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 查询当前登入用户id的购物车信息
     * @return
     */
    List<ShoppingCart> selectUserIDShoppingCart();

    /**
     * 根据参数信息新增购物车信息
     * @param shoppingCartadd
     */
    void insert(ShoppingCartadd shoppingCartadd);

    /**
     * 删除购物车数据-1
     * @param shoppingCartadd
     */
    void removeOne(ShoppingCartadd shoppingCartadd);

    /**
     * 清空当前用户的购物车数据
     */
    void Delete();
}
