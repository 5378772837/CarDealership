package com.carDealerProject.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.carDealerProject.entity.Car;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {
    

    @Query(value="select * from car where is_sold = 0 AND make LIKE ?1", nativeQuery = true)
    public List<Car> findByMake(String make);
    
    @Query(value="select * from car where is_sold = 0 AND model LIKE ?1", nativeQuery = true)
    public List<Car> findByModel(String model);
     
    @Query(value="select * from car where is_sold = 0 AND price BETWEEN ?1 AND ?2", nativeQuery = true)
    public List<Car> findByPrice(double fromPrice, double toPrice);
    
    @Query(value="select * from car where is_sold = 0 AND DATE(date_added) > DATE(?1)", nativeQuery = true)
    public List<Car> findRegCars(LocalDate date);
    
    @Query(value="select * from car where is_sold = 0 AND DATE(date_added) < DATE(?1)", nativeQuery = true)
    public List<Car> findAuctionCars(LocalDate date);
    
    @Query(value="select * from car where is_sold = 0", nativeQuery = true)
    public List<Car> findCarsInInvetory();
    
    @Query(value="select * from car where date_sold BETWEEN DATE(?1) AND DATE(?2)",nativeQuery = true)
    public List<Car> findCarsSold(LocalDate dateFrom, LocalDate dateTo);
	
	
}
