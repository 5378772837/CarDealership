package com.carDealerProject.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carDealerProject.entity.Photo;

public interface PhotoRepo extends JpaRepository<Photo, Integer>{
	
    @Query(value="select * from photo where car_ID = ?1", nativeQuery = true) //make sure field matches database exactly
    public List<Photo> findByCarID(Integer carID);

}
