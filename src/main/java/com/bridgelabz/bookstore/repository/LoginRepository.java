package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Integer> {

}
