import React from 'react';
import { StyleSheet, View, ScrollView  } from 'react-native';
import { SearchBar } from "react-native-elements";
import axios from 'axios';
import {
  Text,
} from "native-base";
import ExploreCard from '../components/common/Cards/ExploreCard';

export default class CardsContainer extends React.Component{
  componentDidMount() {
    //this.fetchData();
  }

  fetchData(){
    axios.get('http://100.80.21.98:8082/api/v1/getItinerary')
      .then(res => {
        console.log("res", res);
        const data = res.data;
        console.log("res.data", data);
        this.setState({
          isLoading: false,
          Events: data
        });
      })
      .catch(error => console.log(error));
  }

  constructor(props){
    super(props);
    this.state = {
      isLoading: false,
      Events: {},
      search: "",
    }
  }

  updateSearch = search => {
    this.setState({ search });
  };

  render(props){
    const { search } = this.state;
    return (
        <ScrollView
        style={StyleSheet.absoluteFill}
        contentContainerStyle={styles.scrollview}>
          <SearchBar
          placeholder="Type Here..."
          onChangeText={this.updateSearch}
          value={search}
          lightTheme={true}
          onCancel={this.state.search=""}/>
        //{ !this.state.isLoading ? this.state.Events.map(o => <ExploreCard common={o}/>):<Text>Loading</Text> }
        </ScrollView>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
