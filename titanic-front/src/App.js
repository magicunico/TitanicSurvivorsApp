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
   </Tabs> 
  );
}

export default App;