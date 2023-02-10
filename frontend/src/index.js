import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';

import {persistor, store} from "./store";
import {CircularProgress} from "@mui/material";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignIn from "./view/SignIn";
import PrivateRoute from "./component/PrivateRoute";
import {Home} from "./view/Home";
import {SnackbarProvider} from "notistack";
import {SnackbarUtilsConfiguration} from "./util/snackbarUtils";
import {Provider} from "react-redux";
import {PersistGate} from "redux-persist/integration/react";

import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <PersistGate loading={<CircularProgress/>} persistor={persistor}>
                <BrowserRouter>
                    <Routes>
                        <Route path={'/signin'}
                               element={<SignIn/>}

                        />
                        <Route path="/home" element={<PrivateRoute><Home/></PrivateRoute>}></Route>

                    </Routes>

                </BrowserRouter>
                <SnackbarProvider maxSnack={7} anchorOrigin={{horizontal: 'right', vertical: 'top'}}>
                    <SnackbarUtilsConfiguration/>
                </SnackbarProvider>
            </PersistGate>
        </Provider>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
