package com.javatechie.jasper.report.dao;

import com.javatechie.jasper.report.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
