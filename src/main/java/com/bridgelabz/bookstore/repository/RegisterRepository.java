package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.RegisterModel;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterModel, Integer>{
	
	@Query(value="select * from user_model where email=:email", nativeQuery=true)
	RegisterModel findByEmail(String email);

}
