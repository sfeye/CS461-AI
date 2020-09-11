import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useGoogleMaps } from "react-hook-google-maps";
import fetchCoordinates from './fetchCoordinates'
import '../App.css';

const mapStyles = {
  width: '100%',
  height: '80%',
};

export const Map = React.memo(function Map() {

  const path = useSelector((state) => state.pathReducer.path);
  const loaded = useSelector((state) => state.pathReducer.loaded);

  const dispatch = useDispatch();

  const [cities, setCities] = useState();

  const handleInputChange = (event) => {
    setCities(event.target.value);
  }

  const handleSubmit = (event) => {

    var startEnd = cities.split(",");
  
    dispatch(fetchCoordinates(startEnd[0], startEnd[1]));
    event.preventDefault();
  }

  const { ref, map, google } = useGoogleMaps(
    "AIzaSyC4Z5Qz97EWcoCczNn2IcYvaYG0L9pe6Rk",
    {
      zoom: 6,
      center: {
        lat: 38.5,
        lng: -98.0
      },
    },
  );

  if (map && loaded) {
    // execute when map object is ready
    var line = []

    if(path) {
      for (var i = 0; i < path.length; i++) {
      line[i] = new google.maps.LatLng(parseFloat(path[i].lat), parseFloat(path[i].lng));
      console.log(path[i].lat)
      }
    }
    console.log(line)
    new google.maps.Polyline({ path: line, map });
  }

  return (
    <div className="App-header">
        <form onSubmit={handleSubmit}>
          <label>
            Please input the start and end cities separated by a comma:
            <br/>
            <input type="text" name="cities" value={cities} onChange={handleInputChange}/>
          </label>
          <input type="submit" value="Submit" />
        </form>
        <br/>
        <br/>
      <div ref={ref} style={{ width: 800, height: 500 }} />
    </div>
  );
});

export default Map;