
import './App.css';
import React from "react";
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
import { Button, TextField } from '@material-ui/core';


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

export default class Passengers extends React.Component {

  constructor(props){
    super(props)

    this.state={
    data:[],
    columns:[
      {title:"Passenger ID",field:"passengerID", editable:"never"},
      {title:"Class",field:"pClass"},
      {title:"Name",field:"name"},
      {title:"Sex",field:"sex"},
      {title:"Age",field:"age"},
      {title:"Siblings/Spouse",field:"sibSp"},
      {title:"Parch",field:"parch"},
      {title:"Ticket",field:"ticket"},
      {title:"Fare",field:"fare"},
      {title:"Cabin",field:"cabin"},
      {title:"Embarked",field:"embarked"},
      {title:"Survived",field:"survived"}
    ],
    searchedId:"",
  }
  this.setFilterId=this.setFilterId.bind(this);
  this.filterData=this.filterData.bind(this);
}




componentDidMount() {
  axios.get(`http://localhost:8080/passengers/all`)
    .then(res => {
      console.log("here");
      const data = res.data;
      this.setState({ data });
    })
}

loadData = (resolve) =>{
  axios.get(`http://localhost:8080/passengers/all`)
  .then(res => {
    console.log("here");
    const data = res.data;
    this.setState({ data });
    this.setState({searchedId:""})
    resolve();
  })
}
 

loadAll = () =>{
  axios.get(`http://localhost:8080/passengers/all`)
  .then(res => {
    console.log("here");
    const data = res.data;
    this.setState({ data });
    this.setState({searchedId:""})
  })
}


updateRow = (newData, oldData, resolve) => {
    axios.post("http://localhost:8080/passengers/", newData)
      .then(res => {
        new Promise((resolve) => {
          this.loadData(resolve);    
        })
        resolve();
      })
      .catch(error => {
        console.log(error)
        resolve()
      })
  }

  addRow=(newData, resolve) =>{
    axios.put("http://localhost:8080/passengers/", newData)
    .then(res => {
      this.setState(prevState => ({
        data: [...prevState.data, newData]
      }))
      resolve()
    })
    .catch(error => {
      console.log(error)
      resolve()
    })
  }


  deleteRow = (oldData, resolve) => {
    axios.delete("http://localhost:8080/passengers/"+oldData.passengerID)
    .then(res=>{
      console.log(oldData)
      new Promise((resolve) => {
        this.loadData(resolve);    
      })
      resolve();
    })
    .catch(error => {
      console.log(error)
      resolve()
    })
  }

  setFilterId = (input) =>{
    console.log(this.state.searchedId);
    this.setState({searchedId:input});
    console.log(input);
  }


  filterData() {
    console.log("hereeeee")
    console.log(this.state.searchedId+"too");
    if(this.state.searchedId!==null && this.state.searchedId!==""){
    axios.get(`http://localhost:8080/passengers/`+this.state.searchedId)
      .then(res => {
        console.log("here");
        let newData=[];
        newData.push(res.data);
        const data = newData;
        this.setState({ data });
      })
    }else{
      new Promise((resolve) => {
        this.loadData(resolve);    
      })
    }
  }





render(){
  return (
    <div className="App">
      <Grid container spacing={1}>
          <Grid item>
            <div>
              <TextField type="text"
              placeholder="Id.."
              value={this.state.searchedId}
              onChange={(input) =>  this.setFilterId(input.target.value)}
              />
              <Button onClick={this.filterData}>Filter</Button>
              <Button onClick={this.loadAll}>Show all data</Button>
            </div>
          </Grid>
          <Grid item>
            <MaterialTable
              title="Titanic passengers"
              columns={this.state.columns}
              data={this.state.data}
              icons={icons}
              editable={{
                onRowUpdate: (newData, oldData) =>
                  new Promise((resolve) => {
                      this.updateRow(newData, oldData, resolve);    
                  }),
                onRowAdd: (newData) =>
                  new Promise((resolve) => {
                    this.addRow(newData, resolve)
                  }),
                onRowDelete: (oldData) =>
                  new Promise((resolve) => {
                    this.deleteRow(oldData, resolve)
                  }),
              }}
            />
          </Grid>
        </Grid>
    </div>
  );
}
}


