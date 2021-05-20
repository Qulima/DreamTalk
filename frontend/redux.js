import AuthReducer from "./reducers/AuthReducer";
import {applyMiddleware, combineReducers, createStore} from "redux";
import thunkMiddleWare from 'redux-thunk'

let reducersBatch = combineReducers({
  auth: AuthReducer
})

let store = createStore(reducersBatch, applyMiddleware(thunkMiddleWare))

export default store