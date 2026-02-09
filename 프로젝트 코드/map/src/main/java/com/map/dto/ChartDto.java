package com.map.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartDto {

    private String label1;

    private String label2;

    private double data1;

    public ChartDto(String label1, String label2, double data1) {
        this.label1 = label1;
        this.label2 = label2;
        this.data1 = data1;
    }
}