package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderModel, Integer> {
}
