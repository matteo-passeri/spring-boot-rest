package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}