package com.shooza.demo.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteDTO {
    private int productId;
    private int userId;

    public FavoriteDTO(int productId, int userId){
        this.productId = productId;
        this.userId = userId;
    }
}
