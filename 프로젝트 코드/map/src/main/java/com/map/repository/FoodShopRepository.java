package com.map.repository;

import com.map.entity.Food;
import com.map.entity.Shop;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface FoodShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT d FROM Shop d")
    List<Shop> findByPointWithin(@Param("point") Point point);

    @Query("SELECT f FROM Food f WHERE f.id = :id")
    Optional<Food> findFoodById(@Param("id") Long id);

    @Query("SELECT f FROM Food f WHERE f.shop.id = :shopId and f.del_date is null")
    List<Food> findFoodsByShopId(@Param("shopId") Long shopId);

    @Query("SELECT COUNT(f) FROM Food f")
    long countFoods();
}