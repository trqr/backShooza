package com.shooza.demo.DTO;


import lombok.Data;

import java.util.List;

@Data
public class StatusUpdateRequest {
    private List<Integer> ids;
    private String newStatus;
}
