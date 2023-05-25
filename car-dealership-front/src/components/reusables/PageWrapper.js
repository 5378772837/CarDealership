import React from 'react';
import Header from './Header';

function PageWrapper(props) {
  return (
    <div >
      <Header user={props.user} setUser={props.setUser}/>
      <div>{props.children}</div>
    </div>
  )
}

export default PageWrapper;