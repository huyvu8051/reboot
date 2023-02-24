import {createSlice} from '@reduxjs/toolkit'
import api from "../../service/api";
import {$error} from "../../util/snackbar-utils";
import {useParams} from "react-router-dom";

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: []
}

export const wpSlice = createSlice({
    name: 'wpSlice',
    initialState,
    reducers: {
        wpInitState: (state, action) => {
            state.loading = true
        },

    },
})

export const {setLoadingState} = wpSlice.actions


export default wpSlice.reducer
