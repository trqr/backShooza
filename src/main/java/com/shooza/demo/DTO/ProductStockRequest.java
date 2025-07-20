package com.shooza.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductStockRequest {
    private List<Integer> ids;
    private int addingStockValue;
}
