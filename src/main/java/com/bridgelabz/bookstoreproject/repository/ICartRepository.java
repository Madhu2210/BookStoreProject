package com.bridgelabz.bookstoreproject.repository;

import com.bridgelabz.bookstoreproject.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartModel, Integer> {
}
