package com.carDealerProject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carDealerProject.entity.Car;
import com.carDealerProject.entity.Photo;
import com.carDealerProject.service.CarService;
import com.carDealerProject.service.PhotoService;

@RestController
@RequestMapping(value="/car")
@CrossOrigin("*")



public class CarController {
    
	@Autowired
	CarService carService;
	
	@Autowired
	PhotoService photoService;
	
	 @RequestMapping(
		  		value = "/save",
		  		consumes = MediaType.APPLICATION_JSON_VALUE,
		  		produces = MediaType.APPLICATION_JSON_VALUE,
		  		method = RequestMethod.POST
		  )
	 public ResponseEntity<Object> save(@RequestBody Car car) {

	      try {
	          Car savedCar = carService.save(car);
	          return new ResponseEntity<Object>(savedCar, HttpStatus.CREATED);
	      } catch (Exception e) {

	          return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
	      } catch (Error e) {

	          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
	      }

	  }
	 
	  @RequestMapping(
		      value="/findCarById/{id}",
		      produces = MediaType.APPLICATION_JSON_VALUE,
		      method = RequestMethod.GET
		  )
		  public ResponseEntity<Object> findCarById(@PathVariable Integer id) {

		      try {
		          Car foundCar = carService.findById(id);
		          return new ResponseEntity<Object>(foundCar, HttpStatus.OK);
		      } catch (Exception e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      } catch (Error e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		      }

		  }
	  @RequestMapping(
		      value="/addPhoto/{id}",
			  consumes = MediaType.APPLICATION_JSON_VALUE,
		      produces = MediaType.APPLICATION_JSON_VALUE,
		      method = RequestMethod.POST
		  )
		  public ResponseEntity<Object> addPhoto (@RequestBody Photo photo, @PathVariable Integer id) {

		      try {
		    	  photoService.save(photo);
		          Car foundCar = carService.findById(id);
		          foundCar.addCarPhoto(photo);
		          carService.update(foundCar);
		          
		          return new ResponseEntity<Object>(foundCar, HttpStatus.OK);
		      } catch (Exception e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      } catch (Error e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		      }

		  }
	  @RequestMapping(
		      value="/deletePhoto/{id}/{photoId}",
			 
		      method = RequestMethod.DELETE
		  )
		  public ResponseEntity<Object> deletePhoto(@PathVariable Integer photoId, @PathVariable Integer id) {
		      try {
		    	  
		          Car foundCar = carService.findById(id);
		          Photo photo = photoService.findById(photoId);
		          foundCar.deleteCarPhoto(photo);
		         
		          carService.update(foundCar);
		          
		          photoService.deleteById(photo.getId());
		         
		         System.out.println(foundCar.getCarPhotos().size());
		          return new ResponseEntity<Object>(foundCar, HttpStatus.OK);
		      } catch (Exception e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      } catch (Error e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		      }

		  }
	  
	  @RequestMapping(
		      value="/findCarByMake/{make}",

		      produces = MediaType.APPLICATION_JSON_VALUE,
		      method = RequestMethod.GET
		  )
		  public ResponseEntity<Object> findCarByMake(@PathVariable String make) {

		      try {
		    	  String makeWC = "*"+make+"*";
		          List <Car> foundCar = carService.findByMake(makeWC);
		          return new ResponseEntity<Object>(foundCar, HttpStatus.OK);
		      } catch (Exception e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		      } catch (Error e) {
		          System.out.println(e);
		          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		      }

		  }
	  
		@RequestMapping(
			      value="/findCarByModel/{model}",
	
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
		  		)
			  public ResponseEntity<Object> findModelByModel(@PathVariable String model) {
					try{ 
					  String modelWC = "%"+model+"%";
			          List <Car> foundCar = carService.findByModel(modelWC);
			          return new ResponseEntity<Object>(foundCar, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }  
		@RequestMapping(
			      value="/findAuctionCars",
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
			  )
			  public ResponseEntity<Object> findAuctionCars() {

			      try {
			    	  LocalDate date = LocalDate.now().minusDays(119);
			          List<Car> auctionCars = carService.findAuctionCars(date);
			          return new ResponseEntity<Object>(auctionCars, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }
		@RequestMapping(
			      value="/findRegCars",
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
			  )
			  public ResponseEntity<Object> findRegCars() {

			      try {
			    	  LocalDate date = LocalDate.now().minusDays(120);
			          List<Car> regCars = carService.findRegCars(date);
			          return new ResponseEntity<Object>(regCars, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }
		@RequestMapping(
			      value="/findCarsSold/{startDate}/{endDate}",
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
			  )
			  public ResponseEntity<Object> findCarsSold(@PathVariable String startDate, @PathVariable String endDate) {
					
			      try {
			    	  
			    	  LocalDate dateFrom = LocalDate.parse(startDate);
			    	  LocalDate dateTo = LocalDate.parse(endDate);
			          List<Car> carsSold = carService.findCarsSold(dateFrom, dateTo);
			          	for(Car c: carsSold) {
			          		System.out.println(c);
			          		}
			          return new ResponseEntity<Object>(carsSold, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }
		@RequestMapping(
			      value="/findByPrice/{fromPrice}/{toPrice}",
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
			  )
			  public ResponseEntity<Object> findCarsByPrice(@PathVariable Double fromPrice, @PathVariable Double toPrice) {
					
			      try {

			          List<Car> carsInRange = carService.findByPrice(fromPrice, toPrice);
			          return new ResponseEntity<Object>(carsInRange, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }
		@RequestMapping(
			      value="/findCarsInInventory",
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.GET
			  )
			  public ResponseEntity<Object> findCarsInInventory() {

			      try {
			          List<Car> inventoryCars = carService.findCarsInInventory();
			          return new ResponseEntity<Object>(inventoryCars, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }

			  @RequestMapping(
			      value="/updateCar",
			      consumes = MediaType.APPLICATION_JSON_VALUE,
			      produces = MediaType.APPLICATION_JSON_VALUE,
			      method = RequestMethod.POST
			  )
			  public ResponseEntity<Object> updateCar(@RequestBody Car car) {

			      try {
			          Car updatedCar = carService.update(car);
			          return new ResponseEntity<Object>(updatedCar, HttpStatus.OK);
			      } catch (Exception e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          System.out.println(e);
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }

			  @RequestMapping(
			      value="/deleteCar/{id}",
			      method = RequestMethod.DELETE
			  )
			  public ResponseEntity<Object> deleteCar(@PathVariable Integer id) {

			      try {
			          // 
			          carService.deleteById(id);
			          return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			      } catch (Exception e) {
			          return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
			      } catch (Error e) {
			          return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			      }

			  }

}
