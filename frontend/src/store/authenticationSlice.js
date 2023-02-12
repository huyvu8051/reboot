import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    loading: false,
    username: '',
    fullName: '',
    avatar: ''

};

export const authenticationSlice = createSlice({
        name: "authenticationSlice",
        initialState,
        reducers: {
            setEmail: (state, action) => {
                state.email = action.payload
            }
            ,
            setAuthenticateSuccess: (state, action) => {
                console.log(action)
                state.username = action.payload.username
                state.fullName = action.payload.fullName
                state.avatar = action.payload.avatar

            }

        },
    })
;

export const {setAuthenticateSuccess, setEmail} = authenticationSlice.actions;


export default authenticationSlice.reducer;
