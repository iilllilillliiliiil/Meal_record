package com.map.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="food")
@Getter
@Setter
@ToString
public class Food {

    @Id
    @Column(name="food_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(nullable = false)
    private String food_Name;

    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(nullable = false)
    private String reg_date;
    private String del_date;

    public Food() {
    }
}