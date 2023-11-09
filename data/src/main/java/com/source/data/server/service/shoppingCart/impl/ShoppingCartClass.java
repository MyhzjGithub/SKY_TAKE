package com.source.data.server.service.shoppingCart.impl;

import com.pojo.dish.Dish;
import com.pojo.setmeal.setmeal;
import com.pojo.shopping.Client.ShoppingCartadd;
import com.pojo.shopping.ShoppingCart;
import com.source.data.server.dao.shoppingCart.ShoppingCartMapper;
import com.source.data.server.service.dish.DishService;
import com.source.data.server.service.setmeal.SetmealService;
import com.source.data.server.service.shoppingCart.ShoppingCartService;
import com.utils.ErrorUtils.Message;
import com.utils.ExceptionUtils.ShoppingCartNullException;
import com.utils.ThreadUtils.BeanThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartClass implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper mapper;
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;

    @Override
    public List<ShoppingCart> selectUserIDShoppingCart() {
        Long userId = BeanThread.getCurrentId();// 获取当前登入用户的id
        return mapper.getUseridShoppingCart(userId);
    }

    @Override
    @Transactional
    public void insert(ShoppingCartadd shoppingCartadd) {
        Long userId = BeanThread.getCurrentId();// 获取当前登入用户的id
        Long dishId = shoppingCartadd.getDishId();  // 菜品id
        Long setmealId = shoppingCartadd.getSetmealId();// 套餐id
        ShoppingCart shoppingCart;
        // 如果菜品id为空 则表示是套餐id 否者是菜品id
        if (dishId != null){    // 表示当前添加的是菜品
            // 添加进购物车   如果当前购物车中存在 则份数加一 否者新增
            shoppingCart = mapper.getDishIDShoppingCart(dishId,userId);
            if (shoppingCart == null){  // 当前购物车中不存在 新增菜品信息 insert
                // 获取菜品信息
                Dish dish = dishService.getDeclaredDishId(dishId);

                shoppingCart = new ShoppingCart();
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCart.setDishFlavor(shoppingCartadd.getDishFlavor());
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setUserId(userId);
                shoppingCart.setDishId(dishId);
                shoppingCart.setNumber(1);
                mapper.insert(shoppingCart);
                return;
            }
        }else { // 表示当前添加的是套餐
            // 如果当前购物车中存在 则份数加一 否者新增
             shoppingCart = mapper.getSetmealIDShoppingCart(setmealId,userId);
            if (shoppingCart == null){ // 当前购物车中不存在 新增套餐信息 insert
                // 获取套餐的信息
                setmeal setmeal = setmealService.getSetmealID(setmealId);

                shoppingCart = new ShoppingCart();
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setSetmealId(setmealId);
                shoppingCart.setUserId(userId);
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCart.setNumber(1);
                mapper.insert(shoppingCart);
                return;
            }
        }
        // 当前购物车中已经存在该商品 直接份数加一即可
        shoppingCart.setNumber(shoppingCart.getNumber() + 1);   // 份数加一
        mapper.update(shoppingCart);
    }

    @Override
    public void removeOne(ShoppingCartadd shoppingCartadd) {
        Long userId = BeanThread.getCurrentId();// 获取当前登入用户的id
        Long dishId = shoppingCartadd.getDishId();  // 菜品id
        Long setmealId = shoppingCartadd.getSetmealId();    // 套餐id
        ShoppingCart shoppingCart;
        if (dishId != null){
            shoppingCart = mapper.getDishIDShoppingCart(dishId, userId); // 当前商品的购物车信息
            if (isnull(shoppingCart)){
                return;
            }
        }else {
            shoppingCart = mapper.getSetmealIDShoppingCart(setmealId, userId);// 当前商品的购物车信息
            if (isnull(shoppingCart)){
                return;
            }
        }
        // 如果当前商品份数 > 1 则份数减一
        shoppingCart.setNumber(shoppingCart.getNumber() - 1);
        mapper.update(shoppingCart);
    }

    @Override
    public void Delete() {
        mapper.DeleteShoppingCarts(BeanThread.getCurrentId());
    }

    @Override
    public void inserts(List<ShoppingCart> shoppingCartList) {
        mapper.inserts(shoppingCartList);
    }

    /**
     * 判断当前购物车中份数是否小于等于1 减一之后为0直接清空即可
     * @param shoppingCart
     * @return
     */
    private boolean isnull(ShoppingCart shoppingCart) {
        if(shoppingCart == null){   // 抛出异常
            throw new ShoppingCartNullException(Message.SHOPPING_CART_WARES_ERROR);
        }
        if (shoppingCart.getNumber() <= 1){   // 否者 清空
            mapper.DeleteShoppingCart(shoppingCart);
            return true;
        }
        return false;
    }
}
