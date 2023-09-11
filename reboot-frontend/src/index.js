import '@fontsource/roboto/300.css'
import '@fontsource/roboto/400.css'
import '@fontsource/roboto/500.css'
import '@fontsource/roboto/700.css'
import {CircularProgress} from '@mui/material'
import {createTheme, ThemeProvider} from '@mui/material/styles'
import {GoogleOAuthProvider} from '@react-oauth/google'
import {SnackbarProvider} from 'notistack'
import React from 'react'
import ReactDOM from 'react-dom'
import {Provider} from 'react-redux'
import {RouterProvider} from 'react-router-dom'
import {PersistGate} from 'redux-persist/integration/react'

import './index.css'
import RouteConfig from './route/RouteConfig'

import {persistor, store} from './store'
import {SnackbarUtilsConfiguration} from './util/snackbar-utils'

const theme = createTheme({
    palette: {
        text: {
            primary: '#172b4d' // Replace with your desired color value
        }
    }
})


ReactDOM.render(
    <ThemeProvider theme={theme}>
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
    </ThemeProvider>
    ,
    document.getElementById('root')
)

