package com.bridgelabz.bookstore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

	@Query(value="select * from user_model where user_model.email=:email", nativeQuery=true)
	UserModel findByEmail(String email);
	

}
