import React from 'react';
import './App.css';
import Tabs from "./components/Tabs"
import Passengers from './Passengers'

function App() {
  return (
   <Tabs> 
     <div label="Passengers data"> 
       <Passengers/>
     </div> 
     <div label="Stats"> 
       After 'while, <em>Crocodile</em>! 
     </div> 
     
   </Tabs> 
  );
}

export default App;