package com.source.data.server.dao.order;

import com.pojo.Query.OrderClientQuery;
import com.pojo.Query.OrderServerQuery;
import com.pojo.order.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {

    void insert(Order order);

    Integer count(Long userId);

    List<Order> PageClient(OrderClientQuery query);

    List<Order> PageServer(OrderServerQuery query);

    @Select("select id, number, status, user_id, address_book_id, order_time, checkout_time, " +
            "pay_method, pay_status, amount, remark, phone, address, user_name, consignee, " +
            "cancel_reason, rejection_reason, cancel_time, estimated_delivery_time, delivery_status, " +
            "delivery_time, pack_amount, tableware_number, tableware_status " +
            "from orders where id = #{id}")
    Order selectId(Long id);

    void update(Order order);

    @Select("select count(*) from orders where status = #{statistics}")
    Integer statistics(Integer statistics);

    Integer getDeclaredValidOrderCount(LocalDateTime bight, LocalDateTime end, Integer status);

    @Select("select sum(amount) from orders where checkout_time between #{bight} and #{end} and status = #{status}")
    Double getDeclaredTurnover(LocalDateTime bight, LocalDateTime end, Integer status);

    Integer selectStatus(Integer status);
}
