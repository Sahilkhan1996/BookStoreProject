package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {

}
