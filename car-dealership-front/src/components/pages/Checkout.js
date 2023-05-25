import '../../css/pages/buy.css';
import { useLocation } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import React from 'react';

function Checkout(props) {
  
  const location = useLocation();
  const { car } = location.state;
  
  const navigate = useNavigate();

  const handlePurchaseClick = (event) => {
    event.preventDefault();
    axios
      .post(`http://localhost:8080/user/buyCar/${props.user.id}/${car.id}`)
      .then((response) => {
        localStorage.setItem("userId", response.data.id)
        props.setUser(response.data);
        navigate("/")
      })
      .catch((error) => {
        console.log(error);
      });
  };


  return (
    <div className='buy-content'>
      <div>CHECKOUT!</div>
      <div className='checkout-box'>
        <img src={car.carPhotos[0].photoUrl} alt={car.model} />
        DESCRIPTION: {car.description}
        MAKE: {car.make}
        MODEL: {car.model}
        YEAR: {car.year}
        MILES: {car.miles}
        DATE ADDED:{car.dateAdded}
        PRICE: {car.price}
        <button onClick={handlePurchaseClick}>PURCHASE</button>
      </div>
    </div>


  )
}

export default Checkout;