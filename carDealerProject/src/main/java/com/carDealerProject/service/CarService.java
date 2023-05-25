package com.carDealerProject.service;

import java.time.LocalDate;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carDealerProject.entity.Car;
import com.carDealerProject.repo.CarRepo;

@Service
public class CarService {
	
	@Autowired
	CarRepo carRepo;
	
	public Car save(Car car) {

	    return carRepo.save(car);
	}
	
	public Car update(Car car) throws Exception {

        if(car.getId() != null) {
	        return carRepo.save(car);
        }

        throw new AccountNotFoundException("Car doesnt exist, nothing updated");
	}
    
    public Car findById(Integer carId) throws Error {

        if(carRepo.findById(carId).isPresent()) {
            return carRepo.findById(carId).get();
        }

        throw new Error("No car id present, Car not found");
        
    }

    public List<Car> findByMake(String make) throws Error {
        
        if(!carRepo.findByMake(make).isEmpty()) {
            return carRepo.findByMake(make);
        }
        

        throw new Error("No cars by that make, Car not found");
        
    }
    
    public List<Car> findByModel(String model) throws Error {
            
        if(!carRepo.findByModel(model).isEmpty()) {
            return carRepo.findByModel(model);
        }
        
        throw new Error("No cars with that model, Car not found");
        
    	}
    
    public List<Car> findByPrice(double fromPrice, double toPrice) throws Error {
      
   		if(!carRepo.findByPrice(fromPrice,toPrice).isEmpty()) {
   			return carRepo.findByPrice(fromPrice,toPrice);
   		}
        
   		throw new Error("No cars listed in that price range");
        
    	}
    

	public List<Car> findAll() {
		
		List<Car> cars = carRepo.findAll();

		return cars;
	}
	
	public List<Car> findRegCars(LocalDate date) {
		List<Car> cars = carRepo.findRegCars(date);
		return cars;

	}
	
	public List<Car> findAuctionCars(LocalDate date) {
		List<Car> cars = carRepo.findAuctionCars(date);
		return cars;

	}
	
	public List<Car> findCarsInInventory() {
		List<Car> cars = carRepo.findCarsInInvetory();
		return cars;

	}
	public List<Car> findCarsSold(LocalDate dateFrom,LocalDate dateTo) {
		List<Car> cars = carRepo.findCarsSold(dateFrom,dateTo);
		return cars;

	}
	
    public void deleteById(Integer id) {
	    carRepo.deleteById(id);
	}

	public Car setCarSold(Integer carId) {

		Car car = findById(carId);
        car.setIsSold(true);
		car.setDateSold(LocalDate.now());
		return carRepo.save(car);
	}
	
    
}
