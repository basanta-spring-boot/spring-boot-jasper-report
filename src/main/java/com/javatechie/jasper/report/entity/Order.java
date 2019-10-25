package com.javatechie.jasper.report.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ORDER_TBL")
@Data
public class Order {
    @Id
    private int orderId;
    private String orderDate;
    private String shipName;
    private String region;
    private String postalCode;

}
