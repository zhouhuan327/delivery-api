package com.zh.deliveryroute.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoNode {
    private String name;
    private float x;
    private float y;
    private float qt;
    private String timeWindow;
}
