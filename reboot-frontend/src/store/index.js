import {configureStore} from '@reduxjs/toolkit'
import authenticationReducer from './authenticationSlice'
import {persistReducer, persistStore} from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import thunk from 'redux-thunk'
import dashboardReducer from "../view/workspace/dashboard-slice";
import messageReducer from "../view/message/message-slice";

const persistConfig = {
    key: 'root',
    storage: storage,
}

const persistedReducer = persistReducer(persistConfig, authenticationReducer)
const store = configureStore({
    reducer: {
        authentication: persistedReducer,
        dashboard: dashboardReducer,
        message: messageReducer,
    },
    middleware: [thunk]
})

const persistor = persistStore(store)


export {
    store,
    persistor
}
