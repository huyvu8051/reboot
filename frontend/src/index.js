import React from 'react';
import ReactDOM from 'react-dom';

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
import {GoogleOAuthProvider} from "@react-oauth/google";

ReactDOM.render(
    <GoogleOAuthProvider clientId="653266560923-h4op46mflhjpa28879oanln3382qlkt5.apps.googleusercontent.com">
        <React.StrictMode>
            <Provider store={store}>
                <PersistGate loading={<CircularProgress/>} persistor={persistor}>
                    <BrowserRouter>
                        <MyRoute/>
                    </BrowserRouter>
                </PersistGate>
            </Provider>
            <SnackbarProvider maxSnack={7} anchorOrigin={{horizontal: 'right', vertical: 'top'}}>
                <SnackbarUtilsConfiguration/>
            </SnackbarProvider>
        </React.StrictMode>
    </GoogleOAuthProvider>
    ,
    document.getElementById('root')
);

