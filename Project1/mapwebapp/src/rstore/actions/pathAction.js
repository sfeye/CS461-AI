import types from "../constants/action-types";

export const setPath = path => ({
  type: types.SET_PATH,
  payload: {path}
});