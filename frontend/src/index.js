import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

import {persistor, store} from "./store";
import {CircularProgress} from "@mui/material";
import {BrowserRouter} from "react-router-dom";
import {SnackbarProvider} from "notistack";
import {SnackbarUtilsConfiguration} from "./util/snackbarUtils";
import {Provider} from "react-redux";
import {PersistGate} from "redux-persist/integration/react";
import {GoogleOAuthProvider} from '@react-oauth/google';

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import MyRoute from "./route/MyRoute";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <GoogleOAuthProvider clientId="653266560923-h4op46mflhjpa28879oanln3382qlkt5.apps.googleusercontent.com">
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
        </GoogleOAuthProvider>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
