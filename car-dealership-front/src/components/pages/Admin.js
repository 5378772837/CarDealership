import React, { useState } from 'react'
import '../../css/pages/signupsignin.css'
import '../../css/reusables/positions.css'
import axios from 'axios';
import { useNavigate } from 'react-router';

import report from '../reusables/reportGenerator';
import '../../css/pages/admin.css'
import EditCar from './EditCar';

function Admin()  {

  const [reportDates, setReportDates] = useState({startDate: '', endDate: '' });
  const [carSales, setCarSales] = useState([]);
  const [cars, setCars]=useState([]);
  const [search, setSearch]=useState([]);
  const [editCar, setEditCar] = useState(null);

  const navigator = useNavigate()

    const changeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    const tempReportDates = { ...reportDates};
    tempReportDates[name] = value;

    setReportDates(tempReportDates)
    }
    
    const handleSearchChange = (event) => {
      setSearch(event.target.value);
    };

  const reportSubmitHandler = () => {

    axios.get(`http://localhost:8080/car/findCarsSold/${reportDates.startDate}/${reportDates.endDate}`)
       .then((response) => {
        console.log("response data", response.data)
        report(response.data);
       })
    

       .catch ((e) => {
        console.log('error');
       })
    };


const addCarsSubmitHandler=() =>{
  navigator('/AddCar');
}


    const findAllSubmitHandler=() => {

      try {
        axios.get('http://localhost:8080/car/findCarsInInventory')
        .then((response) => {
          console.log("response data", response.data)
          setCars(response.data);}
        )}
       catch(err) {
          console.log('error');
       }
  
    };
    const handleSearchSubmit = (event) => {
      event.preventDefault();
    
      axios
        .get(`http://localhost:8080/car/findCarByModel/${search}`)
        .then((response) => {
          setCars(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const handleCarClick = (car) => {
      
      navigator('/EditCar', { state: {car} });
    };
  


    const showCars = () => {
      return cars.map((car) => {
        const carPhoto = car.carPhotos && car.carPhotos.length > 0 ? car.carPhotos[0].photoUrl : '';
        return (
          <div
            className='car-box'
            key={car.id}
            onClick={() => handleCarClick(car)}
          >
            Click To View Details
            <img src={carPhoto} alt={car.model} />
          </div>
        );
      });
    };
    
  

    return (
            <div className= 'fill'>
            <div className='flex-column admin-sidebar justify-content-center'>
                    <h2>Hello Dealer</h2>
                    <h1>Run Sales Report</h1>
                    Report Start Date
                    <input className='sidebar-input-container'  value={reportDates.startDate} name='startDate' type='startDate' onChange={changeHandler} required></input>
                    Report End Date
                    <input className='sidebar-input-container' value={reportDates.endDate} name='endDate' type='endDate' onChange={changeHandler} required></input>
                    <button onClick={reportSubmitHandler}>GET REPORT</button>
                    <h1>EDIT CARS</h1>
                    <h2>Find All</h2>
                    <button onClick={findAllSubmitHandler}>FIND ALL CARS</button>
                    <h1>ADD CARS</h1>
                    <button onClick={addCarsSubmitHandler}>ADD NEW CAR</button>
                    <h2>Find By Model</h2>
                    <input className='sidebar-input-container'  value={search} name='model' type='model' onChange={handleSearchChange} required></input>
                    <button onClick={handleSearchSubmit}>FIND BY MODEL</button>
            </div>
            <div className = 'flex-column fill'>
                {showCars()}
                
                      </div>
            </div>         
 
  
   )
}



export default Admin