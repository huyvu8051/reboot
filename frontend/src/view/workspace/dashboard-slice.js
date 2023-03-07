import {createSlice} from '@reduxjs/toolkit'
import api from "../../service/api";

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: [],
    lizts: [],
    cards: []

}

export const dashboardSlice = createSlice({
    name: 'dashboardSlice',
    initialState,
    reducers: {
        save: (s, a) => {
            Object.assign(s, a.payload)
        },

        updateLizt: (s, a) => {
            const find = s.lizts.find(e => e.id === a.payload.id);

            if (find) {
                Object.assign(find, a.payload)
                //s.lizts.sort((e1, e2) => e1.ordinal - e2.ordinal)
            }
        }
    },
})


export const {save, updateLizt} = dashboardSlice.actions


export default dashboardSlice.reducer
