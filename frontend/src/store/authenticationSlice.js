import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    totalCount: 0,
    data: [],
    loading: false,
    email: ''
};

export const authenticationSlice = createSlice({
    name: "authenticationSlice",
    initialState,
    reducers: {
        setLoadingState: (state, action) => {
            state.loading = true;
        },
        setEmail: (state, action) => {
            state.email = action.payload
            console.log(action)
        }

    },
});

export const {setLoadingState, setEmail} = authenticationSlice.actions;


export default authenticationSlice.reducer;
