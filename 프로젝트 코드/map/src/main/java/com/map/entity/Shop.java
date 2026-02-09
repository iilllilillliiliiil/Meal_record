package com.map.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="shop")
@Getter
@Setter
@ToString
public class Shop {

    @Id
    @Column(name="shop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String shop_name;

    private String shop_detail;

    private double latitude;

    private double longitude;

    @Column(nullable = false)
    private String reg_date;

    private String del_date;

    public Shop() {
    }

    public Shop(String shop_name, String shop_detail, double latitude, double longitude) {
        this.shop_name = shop_name;
        this.shop_detail = shop_detail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reg_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}