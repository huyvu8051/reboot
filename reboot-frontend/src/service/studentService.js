import api from './api'

export const getDataList = () => api.get('home',{
    responseType: 'stream'
})