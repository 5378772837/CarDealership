import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../../css/pages/buy.css';
import { useNavigate } from 'react-router-dom';

function Buy(props) {
  console.log("USER ID", props.user.id);
  const [cars, setCars] = useState([]);
  const [selectedCar, setSelectedCar] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isAuction, setIsAuction] = useState(false);
  const navigate = useNavigate();
  const [search, setSearch] = useState('');

  useEffect(() => {
    if (isAuction) {
      axios
        .get('http://localhost:8080/car/findAuctionCars')
        .then((response) => {
          setCars(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    } else {
      axios
        .get('http://localhost:8080/car/findRegCars')
        .then((response) => {
          setCars(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  }, [isAuction]);

  const handleSearchChange = (event) => {
    setSearch(event.target.value);
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
    setSelectedCar(car);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const goToCheckout = (car) => {
    navigate('/checkout', { state: { car, user: props.user } });
  };
  const goToAuction = (car) => {
    navigate('/auction', { state: { car, user: props.user } });
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

  const showCarPhotos = (car) => {
    return (
      <div>
        {car.carPhotos.map((photo) => (
          <img src={photo.photoUrl} alt={car.model} key={photo.id} />
        ))}
      </div>
    );
  };

  return (
    <div className='buy-content'>
      <div>
        <input
          type='text'
          value={search}
          onChange={handleSearchChange}
          placeholder='Search by model'
        />
        <button onClick={handleSearchSubmit}>Search</button>
      </div>
      <button className='button' onClick={() => setIsAuction(!isAuction)}>
        {isAuction ? 'Show Regular Cars' : 'Show Auctionable Cars'}
      </button>
      {showCars()}
      {isModalOpen && selectedCar && !isAuction &&(
        <div>
          <div>
            <button onClick={closeModal}>Close</button>
          </div>
          <div>
            <div>DESCRIPTION: {selectedCar.description}</div>
            <div>MAKE: {selectedCar.make}</div>
            <div>MODEL: {selectedCar.model}</div>
            <div>YEAR: {selectedCar.year}</div>
            <div>MILES: {selectedCar.miles}</div>
            <div>DATE ADDED:{selectedCar.dateAdded}</div>
            <div>PRICE: {selectedCar.price}</div>
            <div className='car-box'>
              {showCarPhotos(selectedCar)}
            </div>
          </div>
          <div>
            <button onClick={() => goToCheckout(selectedCar)}>Checkout</button>
          </div>
        </div>
      )}
      {isModalOpen && selectedCar && isAuction &&(
        <div>
          <div>
            <button onClick={closeModal}>Close</button>
          </div>
          <div>
            <div>DESCRIPTION: {selectedCar.description}</div>
            <div>MAKE: {selectedCar.make}</div>
            <div>MODEL: {selectedCar.model}</div>
            <div>YEAR: {selectedCar.year}</div>
            <div>MILES: {selectedCar.miles}</div>
            <div>DATE ADDED:{selectedCar.dateAdded}</div>
            <div>PRICE: {selectedCar.price}</div>
            <div className='car-box'>
              {showCarPhotos(selectedCar)}
            </div>
          </div>
          <div>
            <button onClick={() => goToAuction(selectedCar)}>Bid</button>
          </div>
        </div>
      )}
    </div>
  )
}

export default Buy