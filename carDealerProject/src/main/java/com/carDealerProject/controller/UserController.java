package com.carDealerProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carDealerProject.entity.Car;

import com.carDealerProject.entity.User;
import com.carDealerProject.service.CarService;
import com.carDealerProject.service.UserService;

// Denotes that this will be a RESTFul
@RestController
@RequestMapping(value="/user")
@CrossOrigin("*")

public class UserController {

    // You can autowire any service you need to get the data from
    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    // Configures my endpoint, /signup in the end url, accepts JSON data, Produces JSON data, accessed with a post
    @RequestMapping(
    		value = "/signUp",
    		consumes = MediaType.APPLICATION_JSON_VALUE,
    		produces = MediaType.APPLICATION_JSON_VALUE,
    		method = RequestMethod.POST
    )
    // We return a ResponseEntity<Object> because the object returned may vary, could be user, could be an error
    // The RequestBody is the information sent to us to process, post and put has request body, get and delete do not
    // Request body is encrypted, always send password through a post request
    public ResponseEntity<Object> signUp(@RequestBody User user) {
        try {

            

            User savedUser = userService.save(user);

            
            return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
        
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //-------------------------------------------------------------------------///
    @RequestMapping(
        value="/signIn",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.POST
    )
    public ResponseEntity<Object> signIn(@RequestBody User user) {

        try {
            User loggedInUser = userService.signIn(user);
            return new ResponseEntity<Object>(loggedInUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("not found");
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //--------------------------------------------------------//
    @RequestMapping(
        value="/findUserById/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    
    public ResponseEntity<Object> findUserById(@PathVariable Integer id) {

        try {
            User foundUser = userService.findById(id);
            return new ResponseEntity<Object>(foundUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(
        value="/findUserByUserName/{userName}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    public ResponseEntity<Object> findUserByEmail(@PathVariable String userName) {

        try {
            User foundUser = userService.findByUserName(userName);
            return new ResponseEntity<Object>(foundUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //----------------------------------------------------------///
    @RequestMapping(
        value="/findAll",
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.GET
    )
    public ResponseEntity<Object> findAll() {

        try {
            List<User> allUsers = userService.findAll();
            return new ResponseEntity<Object>(allUsers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(
        value="/updateUser",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = RequestMethod.POST
    )
    public ResponseEntity<Object> updateUser(@RequestBody User user) {

        try {
            User updatdUser = userService.update(user);
            return new ResponseEntity<Object>(updatdUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(
        value="/deleteUser/{id}",
        method = RequestMethod.DELETE
    )
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {

        try {
            userService.deleteById(id);
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/buyCar/{id}/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> buyCar(@PathVariable Integer id, @PathVariable Integer carId) {

        try {
            Car boughtCar = carService.setCarSold(carId);
            User updatedUser = userService.addCarToUser(id, boughtCar);
            return new ResponseEntity<Object>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Error e) {
            System.out.println(e);
            return new ResponseEntity<Object>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}