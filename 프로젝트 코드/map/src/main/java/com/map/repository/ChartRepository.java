package com.map.repository;

import com.map.dto.ChartDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ChartRepository{

    @Query("select ''  lable1, lunch.use_date lable2, count(*) data1 " +
            "from foodshop.lunch_log lunch " +
            "left join foodshop.food food on food.food_id = lunch.food_id " +
            "left join foodshop.shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "group by lunch.use_date")
    List<ChartDto> getsaleschart1(@Param("shop_id") String shop_id);

    @Query("select ''  lable1, food.food_name lable2, count(*)*food.price data1 " +
            "from foodshop.lunch_log lunch " +
            "left join foodshop.food food on food.food_id = lunch.food_id " +
            "left join foodshop.shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "group by lunch.food_id")
    List<ChartDto> getsaleschart2(@Param("shop_id") String shop_id);
}