import axios from "axios";
import {error} from "../util/snackbarUtils";
import {store} from "../store";


const instance = axios.create({
    baseURL: '/api/',
});
instance.interceptors.request.use((config) => {
    config.headers.Authorization = `Bearer ${store.getState().authentication.jwtToken}`
    return config;
}, (error) => {
    return Promise.reject(error);
});

instance.interceptors.response.use(response => {
    if (response.data.status > 400 && response.data.status < 600) {
        error(response.data.status + ':' + response.data.message)
        return Promise.reject(response.data);
    }
    return response;
}, err => {
    // console.log(err)
    error(err.message)
    return Promise.reject(err);
})

export default instance