package com.shooza.demo.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class StatusUpdateRequest {

    private List<Integer> ids;
    @NotNull(message = "ne peut pas être null")
    private String newStatus;
}
