package com.map.repository;

import com.map.entity.Food;
import com.map.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query("SELECT f FROM Food f WHERE f.shop = :shop AND f.del_date IS NULL")
    List<Food> findByShopAndDel_dateIsNull(@Param("shop") Shop shop);
}