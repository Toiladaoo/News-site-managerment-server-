package com.example.deliveryecommercebackend.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDelivery {

    private Order order;
    private Branch branch;
    private String nameShipper;
    private LocalDateTime data_time;
    private String input_by;
    private String state;
}
