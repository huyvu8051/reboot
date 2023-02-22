import axios from 'axios'
import {$error, $success} from '../util/snackbar-utils'
import {store} from '../store'
import {refreshToken} from '../store/authenticationSlice'

class MyPromise extends Promise {
    constructor(executor) {
        super(executor)
        this.greatGrandparent = null
        this.catched = false
    }

    catch(onRejected) {
        const promise = super.catch(onRejected);

        promise.greatGrandparent = this.greatGrandparent ? this.greatGrandparent : this
        promise.greatGrandparent.catched = true

        return promise
    }

    then(onfulfilled, onrejected) {
        const promise = super.then(onfulfilled, onrejected);
        promise.greatGrandparent = this.greatGrandparent ? this.greatGrandparent : this

        return promise
    }


    finally(onfinally) {
        const promise = super.finally(() => {
            if(!this.catched){
                $error("Not catched error")
            }
            return onfinally ? onfinally() : null
        })
        promise.greatGrandparent = this.greatGrandparent ? this.greatGrandparent : this
        return promise
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
    return Promise.reject(err)

})
const methods = ['request', 'get', 'delete', 'head', 'options', 'post', 'put', 'patch', 'postForm', 'putForm', 'patchForm']

methods.forEach((method) => {
    const oldMethod = instance[method]

    instance[method] = (url, data, config) => {
        let promise = new MyPromise((resolve, reject) => {
            oldMethod(url, data, config)
                .then((response) => {
                    resolve(response.data)
                })
                .catch((error) => {
                    reject(error)
                })
        }).finally()


        return promise
    }
})

export default instance