import {configureStore} from '@reduxjs/toolkit'
import authenticationReducer from './authenticationSlice'
import {persistReducer, persistStore} from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import thunk from 'redux-thunk'

const persistConfig = {
    key: 'root',
    storage: storage,
}

const persistedReducer = persistReducer(persistConfig, authenticationReducer)
const store = configureStore({
    reducer: {
        authentication: persistedReducer
    },
    middleware: [thunk]
})

const persistor = persistStore(store)



export {
    store,
    persistor
}
