import {setPath} from '../rstore/actions/pathAction';

export default function fetchCoordinates (startCity, endCity) {
    const axios = require('axios');

    return dispatch => {
      axios.post('/map/points',  {
          start: startCity,
          end: endCity
        })
      .then(function(response) {
        dispatch(setPath(response.data))
      })
      .catch(function(error) {
        console.log(error);
      })
    }
  }