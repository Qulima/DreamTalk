import React from "react";
import ReactDom from 'react-dom'
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import store from "./redux";
import MainPage from "./MainPage/MainPage";

ReactDom.render(
  <BrowserRouter>
    <Provider store={store}>
      <MainPage />
    </Provider>
  </BrowserRouter>,
  document.getElementById('app')
)

window.store = store