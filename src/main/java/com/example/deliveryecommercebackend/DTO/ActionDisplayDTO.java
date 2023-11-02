package com.example.deliveryecommercebackend.DTO;

import com.example.deliveryecommercebackend.model.Action;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionDisplayDTO {

    private String code;
    private String name;

    public ActionDisplayDTO(Action action) {
        this.code = action.getCode();
        this.name = action.getName();
    }
}
