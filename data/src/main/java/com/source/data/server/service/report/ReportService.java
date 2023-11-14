package com.source.data.server.service.report;

import com.pojo.report.*;

public interface ReportService {
    /**
     * 查询指定日期之间的营业额
     * @param dataTime
     * @return
     */
    ReportTurnoverStatistics selectTurnoverStatistics(ReportData dataTime);

    /**
     * 查询指定日期之间的用户数据
     * @param dataTime
     * @return
     */
    ReportUserStatistics selectUserStatistics(ReportData dataTime);

    /**
     * 查询指定日期之间的订单数据
     * @param dataTime
     * @return
     */
    ReportOrdersStatistics selectOrdersStatistics(ReportData dataTime);

    /**
     * 查询指定日期之间销量排名前10的商品
     * @param dataTime
     * @return
     */
    ReportTop10 selectTop10(ReportData dataTime);
}
