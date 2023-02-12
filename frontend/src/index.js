import React from 'react';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';

import {persistor, store} from "./store";
import {CircularProgress} from "@mui/material";
import {BrowserRouter} from "react-router-dom";
import {SnackbarProvider} from "notistack";
import {SnackbarUtilsConfiguration} from "./util/snackbarUtils";
import {Provider} from "react-redux";
import {PersistGate} from "redux-persist/integration/react";
import MyRoute from "./route/MyRoute";

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

ReactDOM.render(
    <React.StrictMode>
        <Provider store={store}>
            <PersistGate loading={<CircularProgress/>} persistor={persistor}>

                <BrowserRouter>
                    <MyRoute/>

                </BrowserRouter>
                <SnackbarProvider maxSnack={7} anchorOrigin={{horizontal: 'right', vertical: 'top'}}>
                    <SnackbarUtilsConfiguration/>
                </SnackbarProvider>
            </PersistGate>
        </Provider>
    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
