import AuthReducer from "./reducers/AuthReducer";
import {applyMiddleware, combineReducers, createStore} from "redux";
import thunkMiddleWare from 'redux-thunk'
import MessengerReducer from "./reducers/MessengerReducer";

let reducersBatch = combineReducers({
  auth: AuthReducer,
  dialogs: MessengerReducer
})

let store = createStore(reducersBatch, applyMiddleware(thunkMiddleWare))

export default store