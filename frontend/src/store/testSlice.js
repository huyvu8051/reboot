import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    totalCount: 0,
    data: [],
    loading: false,
};

export const testSlice = createSlice({
    name: "testSlice",
    initialState,
    reducers: {
        setLoadingState: (state, action) => {
            state.loading = true;
        },

    },
});

export const {setLoadingState} = testSlice.actions;


export default testSlice.reducer;
