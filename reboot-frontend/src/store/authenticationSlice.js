import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    username: '',
    fullName: '',
    pictureUrl: '',
    roles: [],
    jwtToken: '',
    uId: ''

}

export const authenticationSlice = createSlice({
    name: 'authenticationSlice',
    initialState,
    reducers: {
        setEmail: (state, action) => {
            state.email = action.payload
        }
        ,
        setAuthenticateSuccess: (state, action) => {
            //console.log(action)
            state.username = action.payload.username
            state.fullName = action.payload.fullName
            state.pictureUrl = action.payload.pictureUrl
            state.jwtToken = action.payload.token
            state.uId = action.payload.uId

        },
        refreshToken: (state, action) => {
            state.jwtToken = action.payload
        },


    },
})


export const {setAuthenticateSuccess, setEmail, refreshToken} = authenticationSlice.actions


export default authenticationSlice.reducer
