import '../../css/pages/addcar.css';
import React, { useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddCar() {


  const [newcar, setNewCar] = useState({description:"",make:"",model:"",miles: 0,price: 0, year:0, dateAdded: 0}) 
  const navigate = useNavigate();

  const addCarChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    const tempNewCar = { ...newcar};
    tempNewCar[name] = value;
    setNewCar(tempNewCar);
};

  const handleAddCarSubmit = (event) => {
    event.preventDefault();
  
    axios
      .post("http://localhost:8080/car/save/", newcar)
      .then((response) => {
      setNewCar(response.data);
      navigate("/Admin")
      })
      .catch((error) => {
        console.log(error);
      });
  };



  return(<div className='add-car-content'>
    <div>
      Description
      <input  value={newcar.description} name='description' type='description' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Make
      <input value={newcar.make} name='make' type='make' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Model
      <input value={newcar.model} name='model' type='model' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Miles
      <input value={newcar.miles} name='miles' type='miles' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Price
      <input value={newcar.price} name='price' type='price' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Year
      <input value={newcar.year} name='year' type='year' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      Date Added: ex:2023-12-03
      <input value={newcar.dateAdded} name='dateAdded' type='dateAdded' onChange={addCarChangeHandler} ></input>
    </div>
    <div>
      <button onClick={handleAddCarSubmit}>SUBMIT</button>
    </div>
  </div>
  )



}

export default AddCar;