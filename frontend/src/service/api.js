import axios from 'axios'
import {$error, $success} from '../util/snackbar-utils'
import {store} from '../store'
import {refreshToken} from '../store/authenticationSlice'

class MyPromise extends Promise {
    constructor(executor) {
        super(executor);
        this.catched = false
    }

    catch(onRejected) {
        this.catched = true
        return super.catch(onRejected);
    }

    finally(onfinally) {

        return super.finally(()=>{
            console.log('finally check', this.catched)
            onfinally()
        })
    }
}


const instance = axios.create()
instance.interceptors.request.use((config) => {
    config.headers.Authorization = `Bearer ${store.getState().authentication.jwtToken}`
    return config
}, (error) => {
    return Promise.reject(error)
})

instance.interceptors.response.use(response => {
    // refresh token
    const token = response.headers.get('refreshToken')
    if (token) {
        store.dispatch(refreshToken(token))
        $success('Jwt token refreshed')
    }

    // check http status
    if (response.data.status > 400 && response.data.status < 600) {
        $error(response.data.status + ':' + response.data.message)
        return Promise.reject(response.data)
    }
    return response
}, err => {

    const promise =  MyPromise.reject('test')

    console.log('promise', promise)
    promise.finally(()=>console.log('finally'))

    // promise.finally(()=>console.log('finally'))
    // promise.catch(()=>console.log('promise catch exe'))

    return promise

})

export default instance