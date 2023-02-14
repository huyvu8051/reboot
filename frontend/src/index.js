import React from 'react'
import ReactDOM from 'react-dom'

import {persistor, store} from './store'
import {CircularProgress} from '@mui/material'
import {BrowserRouter, RouterProvider} from 'react-router-dom'
import {SnackbarProvider} from 'notistack'
import {SnackbarUtilsConfiguration} from './util/snackbar-utils'
import {Provider} from 'react-redux'
import {PersistGate} from 'redux-persist/integration/react'
import RouteConfig from './route/RouteConfig'

import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import {GoogleOAuthProvider} from '@react-oauth/google'

ReactDOM.render(
    <GoogleOAuthProvider clientId={process.env.REACT_APP_GOOGLE_OAUTH_CLIENT_ID}>
        <React.StrictMode>
            <Provider store={store}>
                <PersistGate loading={<CircularProgress/>} persistor={persistor}>
                    <RouterProvider
                        router={RouteConfig}
                    />
                </PersistGate>
            </Provider>
            <SnackbarProvider maxSnack={7} anchorOrigin={{horizontal: 'right', vertical: 'top'}}>
                <SnackbarUtilsConfiguration/>
            </SnackbarProvider>
        </React.StrictMode>
    </GoogleOAuthProvider>
    ,
    document.getElementById('root')
)

