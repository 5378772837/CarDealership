import React from 'react'
import '../../css/pages/home.css'
import '../../css/reusables/positions.css'
function Home(props) {
  console.log("USER ID", props.user.id);
  return (
    
    <div className = 'flex-column justify-content-center container background'>
    <div className='flex-column welcome-box'>Welcome to O.K. Used Cars!
        <p className='flex-row justify-content-center'>Our name might be O.K. but we only sell the best! Whether you need a car, truck or SUV, we won't rest till you have the best! We're not O.K. unless you're O.K.</p>
    </div>
    </div>

  )
}

export default Home