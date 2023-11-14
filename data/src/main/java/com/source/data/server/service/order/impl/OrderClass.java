package com.source.data.server.service.order.impl;

import com.alibaba.fastjson.JSONObject;
import com.pojo.Page.Pages;
import com.pojo.Query.OrderClientQuery;
import com.pojo.Query.OrderServerQuery;
import com.pojo.address.AddressBook;
import com.pojo.dish.Dish;
import com.pojo.order.Order;
import com.pojo.order.OrderDetail;
import com.pojo.order.WEBorder.*;
import com.pojo.setmeal.setmeal;
import com.pojo.shopping.ShoppingCart;
import com.pojo.user.User;
import com.source.data.server.web_socket.WebSocket;
import com.source.data.server.dao.order.OrderMapper;
import com.source.data.server.service.addressBook.AddressBookService;
import com.source.data.server.service.dish.DishService;
import com.source.data.server.service.order.OrderDetailService;
import com.source.data.server.service.order.OrderService;
import com.source.data.server.service.setmeal.SetmealService;
import com.source.data.server.service.shoppingCart.ShoppingCartService;
import com.source.data.server.service.user.userService;
import com.utils.ErrorUtils.Message;
import com.utils.ExceptionUtils.OrderException;
import com.utils.OrderUtils.OrderCancel;
import com.utils.OrderUtils.orderPayStatus;
import com.utils.OrderUtils.orderStatus;
import com.utils.PageUtils.startPage;
import com.utils.ThreadUtils.BeanThread;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class OrderClass implements OrderService {
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private userService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private OrderDetailService detailService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private WebSocket webSocket;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    @Transactional
    public Order_message insert(Order_submit orderSubmit) {
        // 1. 获取下单用户id
        Long userId = BeanThread.getCurrentId();
        // 2. 生成唯一订单号
        String orderNumber = UUID.randomUUID().toString();
        // 3. 下单时间
        LocalDateTime time = LocalDateTime.now();
        // 4. 获取当前用户的地址信息
        Long addressBookId = orderSubmit.getAddressBookId();    // 当前用户地址id
        AddressBook addressBook = addressBookService.selectID(addressBookId);   // 当前用户的地址信息
        // 5. 获取当前用户的用户详情信息
        User user = userService.getUserId(userId);
        // 6. 获取当前用户的购物车信息
        List<ShoppingCart> shoppingCarts = shoppingCartService.selectUserIDShoppingCart();
        // 7. 填充订单
        Order order = new Order();
        order.setNumber(orderNumber);   // 添加订单号
        order.setStatus(orderStatus.ONE);   // 订单状态 未付款
        order.setUserId(userId);    // 下单用户id
        order.setAddressBookId(orderSubmit.getAddressBookId()); // 地址id
        order.setOrderTime(time);   // 下单时间
        order.setCheckoutTime(time);    // 结账时间 付款后生成
        order.setPayMethod(orderSubmit.getPayMethod()); // 支付方式
        order.setPayStatus(orderPayStatus.ZERO);    // 支付状态 当前属于未支付
        order.setAmount(orderSubmit.getAmount()); // 付款金额
        order.setRemark(orderSubmit.getRemark());   // 订单备注
        order.setPhone(addressBook.getPhone());    // 用户手机号
        order.setAddress(addressBook.getDetail());  // 详细地址
        order.setUserName(user.getName());  // 用户名称
        order.setConsignee(addressBook.getConsignee()); // 收货人
        order.setCancelReason(null);    // 订单取消原因
        order.setRejectionReason(null); // 订单拒绝原因
        order.setCancelTime(null);  // 订单取消时间
        order.setEstimatedDeliveryTime(orderSubmit.getEstimatedDeliveryTime()); // 预计送达时间
        order.setDeliveryStatus(orderSubmit.getDeliveryStatus());   // 配送状态
        order.setDeliveryTime(null);    // 送达时间
        order.setPackAmount(orderSubmit.getPackAmount());   // 打包费
        order.setTablewareNumber(orderSubmit.getTablewareNumber()); // 餐具数量
        order.setTablewareStatus(orderSubmit.getTablewareStatus());   // 餐具数量状态

        mapper.insert(order);
        // 8. 填充订单明细表
        List<OrderDetail> orderDetailList = shoppingCarts.stream().map(shoppingCart -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(shoppingCart.getName());   // 商品名称
            orderDetail.setImage(shoppingCart.getImage());   // 商品图片
            orderDetail.setOrderId(order.getId());   // 商品对应的订单id
            orderDetail.setDishId(shoppingCart.getDishId());   // 商品对应的菜品id
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());   // 商品对应的套餐id
            orderDetail.setSetmealId(shoppingCart.getSetmealId()); // 商品对应的菜品口味信息
            orderDetail.setNumber(shoppingCart.getNumber());   // 商品数量
            orderDetail.setAmount(shoppingCart.getAmount());   // 商品单价金额
            return orderDetail;
        }).toList();
        detailService.insert(orderDetailList);
        // 9. 清空当前用户购物测信息
        shoppingCartService.Delete();
        // 10. 响应给服务器有用户下单
        Map<String,Object> map = new HashMap<>(); // 构建返回给服务器的对象
        map.put("type", 1); // 表示有用户下单
        map.put("orderId", order.getId()); // 订单id
        map.put("content", "订单号: " + order.getNumber()); // 信息
        String str = JSONObject.toJSON(map).toString();
        webSocket.sendToAllClient(str);     // 响应给服务器
        // 11. 构建响应类 返回给Controller
        return new Order_message(order.getId(),order.getAmount(),orderNumber,time);
    }

    @Override
    public Pages<OrderClient_page> selectOrders(OrderClientQuery query) {
        query.setPage(startPage.getStartPage(query.getPage(), query.getPageSize()));    // 设置其实页码
        query.setUserId(BeanThread.getCurrentId());
        Integer total = mapper.count(query.getUserId());
        List<Order> orderList = mapper.PageClient(query);
        List<OrderClient_page> orderPages = orderList.stream().map(order -> {
            OrderClient_page orderPage = new OrderClient_page();
            BeanUtils.copyProperties(order, orderPage);
            List<OrderDetail> orderDetailList = detailService.getOrderId(order.getId());
            orderPage.setOrderDetailList(orderDetailList);
            return orderPage;
        }).toList();
        return new Pages<>(total, orderPages);
    }

    @Override
    public OrderClient_page selectOrderID(Long id) {
        Order order = mapper.selectId(id);
        List<OrderDetail> details = detailService.getOrderId(order.getId());
        OrderClient_page orderPage = new OrderClient_page();
        BeanUtils.copyProperties(order, orderPage);
        orderPage.setOrderDetailList(details);
        return orderPage;
    }

    @Override
    public void updateOrderId(Long id) {
        // 取消订单
        Order order = mapper.selectId(id);  // 查询到当前订单的id
        LocalDateTime dateTime = LocalDateTime.now();   // 当前时间
        // 如果当前订单下单时间超过一分钟 需要联系商家进行取消
        LocalDateTime time = order.getCheckoutTime().plusMinutes(1L);   //下单时间 添加一分钟
        Integer minutes = getMinutes(time.toString());  // 超时时间分钟
        Integer minutes1 = getMinutes(dateTime.toString()); // 当前时间的分钟
        if (minutes < minutes1 && order.getStatus() > orderStatus.ONE){ // 下单时间已经超过1分钟 并且已经付款
            throw new OrderException(Message.ORDER_DATETIME);
        }
        order.setStatus(orderStatus.SIX);   // 取消状态
        order.setPayStatus(orderPayStatus.TWO); // 退款
        order.setCancelTime(dateTime);  // 取消时间
        order.setCancelReason(OrderCancel.NULL);    // 一分钟无理由退款
        mapper.update(order);
    }

    @Override
    public void reminder(Long id) {
        Order order = mapper.selectId(id);  // 查询到当前订单的id
        if (order == null){
            throw new OrderException(Message.ORDER_NULL);
        }
        Map<String,Object> map = new HashMap<>(); // 构建返回给服务器的对象
        map.put("type", 2); // 表示有用户催单
        map.put("orderId", id); // 订单id
        map.put("content", "用户催促订单号为: " + order.getNumber() + " 的订单"); // 信息
        String str = JSONObject.toJSON(map).toString();
        webSocket.sendToAllClient(str);     // 响应给服务器
    }

    @Override
    @Transactional
    public void openOrder(Long id) {
        // 获取当前id的订单明细
        List<OrderDetail> details = detailService.getOrderId(id);
        // 当前登入用户的id
        Long userId = BeanThread.getCurrentId();
        // 获取到对应的菜品套餐信息
        List<ShoppingCart> shoppingCartList = details.stream().map(detail -> {
            // detail 每一笔订单中菜品与套餐的信息
            Long dishId = detail.getDishId();   // 菜品id
            String flavor = detail.getDishFlavor(); // 菜品的口味数
            Long setmealId = detail.getSetmealId(); // 口味id
            // 获取菜品的信息
            Dish dish = dishService.getDeclaredDishId(dishId);
            // 获取套餐的信息
            setmeal setmeal = setmealService.getSetmealID(setmealId);
            // 添加进当前用户的购物车中
            ShoppingCart shoppingCart = new ShoppingCart();
            if (dish != null) {   // 当前是菜品
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setDishId(dishId);
                shoppingCart.setDishFlavor(flavor);
                shoppingCart.setNumber(detail.getNumber());
                shoppingCart.setAmount(dish.getPrice());
            } else {  // 套餐
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setNumber(detail.getNumber());
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setSetmealId(setmealId);
            }
            shoppingCart.setUserId(userId);
            shoppingCart.setCreateTime(LocalDateTime.now());
            return shoppingCart;
        }).toList();

        shoppingCartService.inserts(shoppingCartList);
    }

    @Override
    public Pages<OrderServer_page> selectPage(OrderServerQuery page) {
        Integer startPages = startPage.getStartPage(page.getPage(), page.getPageSize()); // 起始页码
        page.setPage(startPages);
        Integer total = mapper.count(null);
        List<Order> list = mapper.PageServer(page);
        List<OrderServer_page> orderServerPages = list.stream().map(order -> {
            OrderServer_page orderServerPage = new OrderServer_page();
            BeanUtils.copyProperties(order, orderServerPage);
            List<OrderDetail> details = detailService.getOrderId(order.getId());    // 菜品明细
            AddressBook addressBook = addressBookService.selectID(order.getAddressBookId());
            StringBuilder sb = new StringBuilder();
            details.forEach(orderDetail -> sb.append(orderDetail.getName()).append(" "));
            orderServerPage.setOrderDishes(sb.toString());
            orderServerPage.setPhone(addressBook.getPhone());
            return orderServerPage;
        }).toList();
        return new Pages<>(total, orderServerPages);
    }

    @Override
    public Order_statistics selectStatistics() {
        // 获取待派送订单
        Integer confirmed = mapper.statistics(orderStatus.THREE);
        // 获取派送中订单
        Integer deliveryInProgress = mapper.statistics(orderStatus.FOUR);
        // 获取待接单数量
        Integer toBeConfirmed = mapper.statistics(orderStatus.TWO);
        return new Order_statistics(confirmed,deliveryInProgress,toBeConfirmed);

    }

    @Override
    public Order_Declared selectDeclaredOrder(Long id) {
        Order order = mapper.selectId(id);
        List<OrderDetail> details = detailService.getOrderId(order.getId());
        Order_Declared orderDeclared = new Order_Declared();
        BeanUtils.copyProperties(order, orderDeclared);
        orderDeclared.setOrderDetailList(details);
        return orderDeclared;
    }

    @Override
    public void updateConfirmStatus(Long id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(orderStatus.THREE); // 状态为已接单
        mapper.update(order);
    }

    @Override
    public void updateRejectionStatus(Order_updateRejection updateRejection) {
        // TODO: 2023/11/10 需要根据支付接口 进行退款回调
        Order order = new Order();
        order.setRejectionReason(updateRejection.getRejectionReason()); // 添加拒绝订单的原因
        order.setId(updateRejection.getId());
        order.setStatus(orderStatus.SEVEN); // 状态为已退款
        mapper.update(order);
    }

    @Override
    public void updateCancelStatus(Order_updateCancel orderUpdateCancel) {
        Order order = new Order();
        order.setCancelReason(orderUpdateCancel.getCancelReason()); // 添加取消订单的原因
        order.setCancelTime(LocalDateTime.now());   // 订单取消时间
        order.setId(orderUpdateCancel.getId());
        order.setStatus(orderStatus.SIX);  // 状态为已取消
        mapper.update(order);
    }

    @Override
    public void updateCompleteStatus(Long id) {
        Order order = new Order();
        order.setDeliveryTime(LocalDateTime.now()); // 添加订单完成时间
        order.setId(id);
        order.setStatus(orderStatus.FIVE);  // 状态为已完成
        mapper.update(order);
    }

    @Override
    public void updateDelivery(Long id) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(orderStatus.FOUR);  // 状态为派送中
        mapper.update(order);
    }

    @Override
    public Double getDeclaredOrderCompletionRate(LocalDateTime begin, LocalDateTime end) {
        // 当天的有效订单数量
        Integer declaredvalidOrderCount = getDeclaredValidOrderCount(begin, end);
        // 当天全部订单数量
        Integer orderCount = mapper.getDeclaredValidOrderCount(begin, end, null);
        if (orderCount == 0 || declaredvalidOrderCount == 0){
            return 0.0;     // 没有任何订单 设置为0.0
        }
        // 计算订单完成率
        double completionRate = (double) declaredvalidOrderCount / orderCount;
        Double result = Math.round((completionRate) * 100) / 100.0; // 保留俩位小数
        return result;
    }

    @Override
    public Integer getDeclaredValidOrderCount(LocalDateTime begin, LocalDateTime end) {
        return mapper.getDeclaredValidOrderCount(begin,end,orderStatus.FIVE);
    }

    @Override
    public Double getDeclaredTurnover(LocalDateTime begin, LocalDateTime end) {
        // 获取当日订单的全部有效订单金额累加
        Double turnover = mapper.getDeclaredTurnover(begin, end, orderStatus.FIVE);
        return turnover == null ? 0.0 : turnover;
    }


    @Override
    public Double getDeclaredUnitPrice(LocalDateTime begin, LocalDateTime end) {
        Integer declaredvalidOrderCount = getDeclaredValidOrderCount(begin, end);   // 当日的有效订单数量
        Double turnover = getDeclaredTurnover(begin, end);  // 当日的交易总金额
        if (turnover == 0.0){
            return 0.0;
        }
        double UnitPrice = turnover / declaredvalidOrderCount;  // 平均单价
        UnitPrice = Double.parseDouble(new DecimalFormat("#.##").format(UnitPrice));    // 保留两位小数展示
        return UnitPrice;
    }

    @Override
    public Integer selectStatus(Integer status) {
        return mapper.selectStatus(status);
    }

    @Override
    public Integer selectCount() {
        return mapper.selectCount();
    }

    @Override
    public Integer getDeclaredCount(LocalDateTime begin, LocalDateTime end) {
        return mapper.getDeclaredCount(begin,end);
    }

    @Override
    public Integer getDeclaredValidCount(LocalDateTime begin, LocalDateTime end) {
        return mapper.getDeclaredValidCount(begin, end, orderStatus.FIVE);
    }

    @Override
    public List<Order> selectOrder(LocalDateTime begins, LocalDateTime ends) {
        return mapper.selectOrderDataTime(begins,ends);
    }

    private static Integer getMinutes(String s){
        String str = s.split("T")[1].split(":")[1];
        return Integer.parseInt(str);
    }
}
