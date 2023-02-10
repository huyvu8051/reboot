import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    totalCount: 0,
    data: [],
    loading: false,
};

export const authenticationSlice = createSlice({
    name: "authenticationSlice",
    initialState,
    reducers: {
        setLoadingState: (state, action) => {
            state.loading = true;
        },

    },
});

export const {setLoadingState} = authenticationSlice.actions;


export default authenticationSlice.reducer;
