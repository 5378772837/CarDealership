import '../../css/pages/buy.css';
import { Navigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import React, { useEffect, useState } from 'react';

function EditCar(props) {
  
  const location = useLocation();
  const { car } = location.state;
  const[photo, setPhoto]=useState({id:0,photoUrl:"https://th.bing.com/th/id/R.73a2ed0609b61b4f432b0df303d9aaae?rik=J7xCXORgk%2fgiYg&riu=http%3a%2f%2fauctions.morphyauctions.com%2fItemImages%2f000278%2f18020361_1_lg.jpeg&ehk=dpbY5ovF0zXGJtlNbJQ%2fHkOFUwsKLSAqmKvCxIUyzv8%3d&risl=&pid=ImgRaw&r=0"});
  const [editCar, setEditCar] = useState(0);
  let [photoNum, setPhotoNum] = useState(0);
  const [newPhoto, setNewPhoto]=useState();
  let [photoCount,setPhotoCount] = useState(0);
  const[responseCar,setResponseCar]=useState(0)
  const navigate = useNavigate();

  useEffect(() => {
  if(editCar===responseCar){
  setEditCar(car);
  setPhotoCount(Object.keys(car.carPhotos).length)

  }else{
    setEditCar(responseCar);
    setPhotoCount(Object.keys(responseCar.carPhotos).length)

  }
  displayPhoto()
  }, [responseCar])

  useEffect(()=>{
  displayPhoto()
  },[photoCount])

  const displayPhoto = () => {
    try{
    setPhoto(editCar.carPhotos[photoNum])
    }catch(e){setPhoto({photoUrl:"https://th.bing.com/th/id/R.73a2ed0609b61b4f432b0df303d9aaae?rik=J7xCXORgk%2fgiYg&riu=http%3a%2f%2fauctions.morphyauctions.com%2fItemImages%2f000278%2f18020361_1_lg.jpeg&ehk=dpbY5ovF0zXGJtlNbJQ%2fHkOFUwsKLSAqmKvCxIUyzv8%3d&risl=&pid=ImgRaw&r=0"})}

  }

  const changeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    const tempCar = { ...car};
    tempCar[name] = value;
    setEditCar(tempCar)
    }
    const photoChangeHandler = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        const tempPhoto = { ...newPhoto};
        tempPhoto[name] = value;
        setNewPhoto(tempPhoto)
        }

    const nextPhotoClick = () =>{
        if(photoCount-1 !== photoNum){
          setPhotoNum(photoNum+1)
          setPhoto(editCar.carPhotos[photoNum])
        }
        else{
          setPhotoNum(0)
          setPhoto(editCar.carPhotos[photoNum])
          }
    }
    const prevPhotoClick = () =>{
       if(photoNum===0){
        setPhotoNum(photoCount-1)
        setPhoto(editCar.carPhotos[photoNum])
      }else{
        setPhotoNum(photoNum-1)
        setPhoto(editCar.carPhotos[photoNum])
      
      }

    }

    
    const deletePhotoClick = () =>{
        axios.delete(`http://localhost:8080/car/deletePhoto/${editCar.id}/${photo.id}`)
        .then((response)=>{
          setResponseCar(response.data)
          setPhotoNum(0)

        }).catch((e)=>{
          console.log(e)
        })
    }

    const addPhotoClick = (event) =>{
        event.preventDefault();
        axios.post(`http://localhost:8080/car/addPhoto/${editCar.id}`,newPhoto)
        .then((response)=>{
          setResponseCar(response.data)
          setPhotoNum(0)

        }).catch((e)=>{
          console.log(e)
        })

    }


  const handleUpdateClick = (event) => {
    event.preventDefault();
    axios
      .post("http://localhost:8080/car/updateCar", editCar)
      .then((response) => {

        setResponseCar(response.data);
        navigate("/Admin")
      })
      .catch((error) => {
        console.log(error);
      });
  };



  return (
    <div className="car-edit-box">
      <div className='flex-row'><h1 className = 'center'>EditCar</h1></div>
   
        <img className = 'picture-box' src={photo.photoUrl} alt={editCar.description}/>
        <div className = 'flex-row center'>
        <button onClick={deletePhotoClick}>DELETE</button>
        <button onClick={nextPhotoClick}>NEXT</button>
        <button onClick={prevPhotoClick}>PREVIOUS</button>
        </div>

        <div className='flex-row center'>
        IMAGE URL:
        <input className = 'input-container' value={photo.photoUrl} name='photoUrl' type='photoUrl' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        DESCRIPTION:
        <input className = 'input-container' value={editCar.description} alt  name='description' type='description' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        MAKE:
        <input className = 'input-container' value={editCar.make} name='make' type='make' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        MODEL:
        <input className = 'input-container' value={editCar.model} name='model' type='model' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        YEAR:
        <input className = 'input-container' value={editCar.year} name='year' type='year' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        MILES:
        <input className = 'input-container' value={editCar.miles} name='miles' type='miles' onChange={changeHandler} required></input>
        </div>
        <div className='flex-row center'>
        PRICE:
        <input className = 'input-container' value={editCar.price} name='price' type='price' onChange={changeHandler} required></input>
        </div>
        <div>
        <button onClick={handleUpdateClick}>Update</button>
        </div>
        <div className='flex-row center'>
        ADD PHOTO URL:
        <input className = 'input-container' name='photoUrl' type='photoUrl' onChange={photoChangeHandler}></input>
        </div>
        <div>
        <button onClick={addPhotoClick}>Add Photo</button>
        </div>
    </div>
      



  )
}

export default EditCar;