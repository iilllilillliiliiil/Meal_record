package com.map.repository;

import com.map.entity.Lunch_log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LunchLogRepository extends JpaRepository<Lunch_log, Long> {

    @Query(value = "select '' use_date, lunch.use_age use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "group by lunch.use_age", nativeQuery = true)
    List<Object[]> getAgeStats(@Param("shop_id") String shop_id);

    @Query(value = "select '' use_date, lunch.use_age use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "group by lunch.use_age", nativeQuery = true)
    List<Object[]> getOverallAgeStats();

    @Query(value = "select '' use_date, shop.shop_name use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "group by shop.shop_id, shop.shop_name", nativeQuery = true)
    List<Object[]> getOverallShopStats();

    @Query(value = "select '' use_date, food.food_name use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "group by food.food_id, food.food_name", nativeQuery = true)
    List<Object[]> getOverallMenuStats();

    @Query(value = "select ''  use_date, lunch.use_date use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "AND lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_date " +
            "order by lunch.use_date", nativeQuery = true)
    List<Object[]> getdatechart(@Param("shop_id") String shop_id);

    @Query(value = "select ''  use_date, food.food_name use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "AND lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.food_id "  +
            "order by food.food_name", nativeQuery = true)
    List<Object[]> getfoodchart(@Param("shop_id") String shop_id);

    @Query(value = "select ''  use_date, lunch.use_category use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "AND lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_category " +
            "order by lunch.use_category", nativeQuery = true)
    List<Object[]> getcategorychart(@Param("shop_id") String shop_id);

    @Query(value = "select ''  use_date, lunch.use_age use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where shop.shop_id like :shop_id " +
            "AND lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_age " +
            "order by lunch.use_age", nativeQuery = true)
    List<Object[]> getagechart(@Param("shop_id") String shop_id);

    @Query(value = "select ''  use_date, lunch.use_date use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_date " +
            "order by lunch.use_date", nativeQuery = true)
    List<Object[]> gettotaldatechart();

    @Query(value = "select ''  use_date, shop.shop_name use_category, count(*) use_age " +
            "from (select lunch.food_id, lunch.use_date, food.shop_id from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id ) food " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where food.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by food.shop_id "  +
            "order by shop.shop_name", nativeQuery = true)
    List<Object[]> gettotalfoodchart();

    @Query(value = "select ''  use_date, lunch.use_category use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_category " +
            "order by lunch.use_category", nativeQuery = true)
    List<Object[]> gettotalcategorychart();

    @Query(value = "select ''  use_date, lunch.use_age use_category, count(*) use_age " +
            "from lunch_log lunch " +
            "left join food food on food.food_id = lunch.food_id " +
            "left join shop shop on shop.shop_id = food.shop_id " +
            "where lunch.use_date between DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 2 WEEK), '%Y-%m-%d') " +
            "AND DATE_FORMAT(CURDATE(), '%Y-%m-%d') " +
            "group by lunch.use_age " +
            "order by lunch.use_age", nativeQuery = true)
    List<Object[]> gettotalagechart();
}