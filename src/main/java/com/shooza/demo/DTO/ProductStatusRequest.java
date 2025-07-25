package com.shooza.demo.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductStatusRequest {

    private List<Integer> ids;
    @NotNull(message = "ne peut pas être null")
    private String newStatus;
}
