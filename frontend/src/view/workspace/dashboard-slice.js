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
            }else {
                s.lizts.push(a.payload)
            }
        },
        updateCard: (s, a) => {
            const find = s.cards.find(e => e.id === a.payload.id);
            if (find) {
                Object.assign(find, a.payload)
            }else {
                s.cards.push(a.payload)
            }
        }
    },
})


export const {save, updateLizt, updateCard} = dashboardSlice.actions


export default dashboardSlice.reducer
