import types from "../constants/action-types";

const initialState = {
  path: [],
  loaded: false
};

function pathReducer(state = initialState, action) {
  switch (action.type) {
    case types.SET_PATH:
        return{
          ...state,
            path: action.payload.path,
            loaded: true
        };
        default:
            return state;
    }
  }
  
  export default pathReducer
  