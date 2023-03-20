package com.bridgelabz.bookstore.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.bridgelabz.bookstore.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {
	
	@Query(value="select * from cart_model where user_id=:userId", nativeQuery=true)
	List<CartModel> findByUserId(int userId);
	
	@Query(value = "select * from cart_model where user_id =:user_id",nativeQuery = true)
	List<CartModel> findCartItemsByUserId(@Param("user_id")int id);
	
	@Transactional
    @Modifying
    @Query(value = "delete from cart_model c where user_id =:user_id" ,nativeQuery = true)
    void deleteCartItemByUserId(@Param("user_id") int user_id);
	
	@Query(value="select * from cart_model where user_id=:userId", nativeQuery=true)
	List<CartModel> byUserId(int userId);
}
