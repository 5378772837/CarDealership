package com.carDealerProject.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

import com.carDealerProject.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    
    @Query(value="select * from user where user_name = ?1 and password = ?2", nativeQuery = true)
    public User signIn(String userName, String password);

    @Query(value="select * from user where user_name = ?1", nativeQuery = true) //make sure field matches database exactly
    public User findByUserName(String userName);

}