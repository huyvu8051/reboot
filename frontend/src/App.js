import * as React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Provider} from "react-redux";
import store from "./store";
import PrivateRoute from "./component/PrivateRoute";
import SignIn from "./view/SignIn";
import {Dashboard} from "./component/Dashboard";
import {SnackbarProvider} from "notistack";
import {SnackbarUtilsConfiguration} from "./component/snackbar/SnackbarUtils";
import {Home} from "./view/Home";


export default function StickyHeadTable() {

    return (
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path={'/signin'}
                           element={<SignIn/>}

                    />
                    <PrivateRoute path="/home" element={<Home/>}></PrivateRoute>

                </Routes>
                <SnackbarProvider maxSnack={7} anchorOrigin={{horizontal: 'right', vertical: 'top'}}>
                    <SnackbarUtilsConfiguration/>
                </SnackbarProvider>
            </BrowserRouter>
        </Provider>
    );
}