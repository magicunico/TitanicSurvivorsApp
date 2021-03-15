import logo from './logo.svg';
import './App.css';
import React, { useEffect, useState } from "react";
import axios from 'axios'
import MaterialTable from "material-table";
import Grid from '@material-ui/core/Grid'
import { forwardRef } from 'react';

import AddBox from '@material-ui/icons/AddBox';
import ArrowDownward from '@material-ui/icons/ArrowDownward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';

function App() {

  var columns=[
    {title:"passengerID",field:"passengerID"},
    {title:"pClass",field:"pClass"},
    {title:"name",field:"name"},
    {title:"sex",field:"sex"},
    {title:"age",field:"age"},
    {title:"sibSp",field:"sibSp"},
    {title:"parch",field:"parch"},
    {title:"ticket",field:"ticket"},
    {title:"fare",field:"fare"},
    {title:"cabin",field:"cabin"},
    {title:"embarked",field:"embarked"},
    {title:"survived",field:"survived"}
  ]

  const icons = {
    Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
    Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
    Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
    DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
    Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
    Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
    Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
    FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
    LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
    NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
    PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
    ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
    SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
    ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
    ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />)
  };

  const [data,setData] = useState([]);

  

  // const api = axios.create({
  //   baseURL: `https://reqres.in/api`
  // })

  const getData=()=>{
    fetch('data.json'
    ,{
      headers : { 
        'Content-Type': 'application/json',
        'Accept': 'application/json'
       }
    }
    )
      .then(function(response){
        console.log(response)
        return response.json();
      })
      .then(function(myJson) {
        console.log(myJson);
        setData(myJson)
      });
  }
  useEffect(()=>{
    getData()
  },[])

  // useEffect(() => {
  //   api.get("/users")
  //     .then(res => {
  //       setData(res.data.data)
  //     })
  //     .catch(error=>{
  //       console.log(error)
  //     })
  // }, [])

  // const updateRow = (newData, oldData, resolve) => {
    
  //   api.patch("/users/"+newData.id, newData)
  //     .then(res => {
  //       const dataUpdate = [...data];
  //       const index = oldData.tableData.id;
  //       dataUpdate[index] = newData;
  //       setData([...dataUpdate]);
  //       resolve()
  //     })
  //     .catch(error => {
  //       console.log(error)
  //       resolve()
  //     })
  // }

  // const addRow = (newData, resolve) => {
  //     api.post("/users", newData)
  //     .then(res => {
  //       let dataToAdd = [...data];
  //       dataToAdd.push(newData);
  //       setData(dataToAdd);
  //       resolve()
        
  //     })
  //     .catch(error => {
  //       console.log(error)
  //       resolve()
  //     })
  // }

  // const deleteRow = (oldData, resolve) => {

  //   api.delete("/users/"+oldData.id)
  //     .then(res => {
  //       const dataDelete = [...data];
  //       const index = oldData.tableData.id;
  //       dataDelete.splice(index, 1);
  //       setData([...dataDelete]);
  //       resolve()
  //     })
  //     .catch(error => {
  //     console.log(error)
  //     resolve()
  //     })
  // }


  return (
    <div className="App">
      <Grid container spacing={1}>
          <Grid item>
            <MaterialTable
              title="Titanic passengers"
              columns={columns}
              data={data}
              icons={icons}
              // editable={{
              //   onRowUpdate: (newData, oldData) =>
              //     new Promise((resolve) => {
              //         updateRow(newData, oldData, resolve);
                      
              //     }),
              //   onRowAdd: (newData) =>
              //     new Promise((resolve) => {
              //       addRow(newData, resolve)
              //     }),
              //   onRowDelete: (oldData) =>
              //     new Promise((resolve) => {
              //       deleteRow(oldData, resolve)
              //     }),
              // }}
            />
          </Grid>
        </Grid>
    </div>
  );
}

export default App;
