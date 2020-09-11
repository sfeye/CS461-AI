import React, { Component } from 'react';
import { Map, GoogleApiWrapper, Marker, Polyline } from 'google-maps-react';

const mapStyles = {
  width: '100%',
  height: '100%'
};

const triangleCoords = [
  {lat: 38.9220277, lng: -97.2666667},
  {lat: 37.0760844, lng: -97.8793212},
  {lat: 37.8098997, lng: -96.8943313},
  {lat: 38.0572062, lng: -97.9414547}
];

export class MapContainer extends Component {
  render() {
    return (
      <Map
        google={this.props.google}
        zoom={7}
        style={mapStyles}
        initialCenter={{
         lat: 38.5,
         lng: -98.0
        }}
      >
         <Polyline
          path={triangleCoords}
          strokeColor="#0000FF"
          strokeOpacity={0.8}
          strokeWeight={2} />

      </Map>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: 'AIzaSyC8w4tqP9aaAUmbBdHsqY3K5bfNhFOYXyw'
})(MapContainer);