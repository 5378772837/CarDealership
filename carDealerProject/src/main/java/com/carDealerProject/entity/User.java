package com.carDealerProject.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Annotate Objects with Entity, lets project know its a Object from the DB
@Entity
// Tells what table the object is coming from
@Table(name="user")
public class User {
    // ID lets you know its an id
    @Id
    // Column maps to the same name as the column name in the database, it is case sensitive
    @Column(name = "id")
    // This will configure your id to be auto generated, now you don't need a setter for your id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userName", unique = true, nullable = false)
    private String userName;
    
    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "isAdmin", nullable = false)
    private Boolean isAdmin;
  
	@OneToMany 
	@JoinColumn(name="user_Id",referencedColumnName="id")
    private List<Car> userCars;

    //----------------------------///
    public User() {}
    
    public Boolean getIsAdmin() {
 		return isAdmin;
 	}

 	public void setIsAdmin(Boolean isAdmin) {
 		this.isAdmin = isAdmin;
 	}

    public List<Car> getUserCars() {
        return userCars;
    }



    public void setUserCars(List<Car> userCars) {
        this.userCars = userCars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
    }
}
