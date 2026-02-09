package com.map.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="lunch_log")
@Getter
@Setter
@ToString
public class Lunch_log {

    @Id
    @Column(name="lunch_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private String use_date;

    @Column(nullable = false)
    private String use_category;

    @Column(nullable = false)
    private String use_age;
}