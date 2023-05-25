import React from 'react'
import '../../css/pages/signupsignin.css'
import '../../css/reusables/positions.css'
import axios from 'axios';
import { useNavigate } from 'react-router';

function SignUp(props) {


    const navigator = useNavigate()

    const signUpChangeHandler = (event) => {
        const name = event.target.name;
        let value;
        if (event.target.type === "checkbox") {
            value = event.target.checked;
        } else {
            value = event.target.value;
        }
        const tempUser = { ...props.user };
        tempUser[name] = value;
        props.setUser(tempUser);
    };

  const signUpSubmitHandler = () => {
    axios.post("http://localhost:8080/user/signUp", props.user)
      .then((response) => {
        localStorage.setItem("userId", response.data.id)
        props.setUser(response.data)
        navigator("/")
      })
      .catch((e) => {
        console.log(e)
      })
    }

    return (
        <div className='signup-signin-content'>
            <div className='sign-up-box'>
                <div>
                    New User? Sign-up Today!
                </div>
                <h1>Sign-Up</h1>
                <div className='flex-row justify-content-left'>
                    USERNAME
                    <input className='input-container'  value={props.user.userName} name='userName' type='userName' onChange={signUpChangeHandler} ></input>
                </div>
                <div className='flex-row justify-content-left'>
                    PASSWORD
                    <input className='input-container' value={props.user.password} name='password' type='password' onChange={signUpChangeHandler} ></input>
                </div>
                <div className='flex-row justify-content-center'>
                    <div>Check box if you are an Admin</div>
                    <input type="checkbox" name="isAdmin" checked={props.user.isAdmin} onChange={signUpChangeHandler}/>
                </div>
                <div className='flex-row justify-content-center'>
                    <button onClick={signUpSubmitHandler}>SUBMIT</button>
                </div>
            </div>
        </div>
    )
}
export default SignUp