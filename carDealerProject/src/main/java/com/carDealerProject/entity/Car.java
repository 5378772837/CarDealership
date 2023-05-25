package com.carDealerProject.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Car")
public class Car {
    
 
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "miles")
    private Integer miles;

    @Column (name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "description")
    private String description;
    
    @Column(name = "price", scale = 2)
    private Double price;

    @Column(name = "is_sold")
    private Boolean isSold = false;
    

	@Column(name = "sale_price")
    private Double salePrice;

    @Column(name = "date_sold", nullable = true)
    private LocalDate dateSold;
    
	@OneToMany	    
	@JoinColumn(name="car_Id",referencedColumnName="id")
	private List<Photo> carPhotos;
	

    public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public List<Photo> getCarPhotos() {
		return carPhotos;
	}
	public void addCarPhoto(Photo photo) {
		this.carPhotos.add(photo);
	}
	public void deleteCarPhoto(Photo photo) {
		this.carPhotos.remove(photo);
	}

	public void setCarPhotos(List<Photo> carPhotos) {
		this.carPhotos = carPhotos;
	}

	public Car() {
    }

    //------Getters and Setters------//
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getIsSold() {
        return isSold;
    }

    public void setIsSold(Boolean isSold) {
        this.isSold = isSold;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }
    @Override
	public String toString() {
	return "Car [id=" + id + ", make=" + make + ", model=" + model + ", year=" + year + ", miles=" + miles
			+ ", dateAdded=" + dateAdded + ", description=" + description + ", price=" + price + ", isSold="
			+ isSold + ", salePrice=" + salePrice + ", dateSold=" + dateSold + ", carPhotos=" + carPhotos + "]";
	}

    
}