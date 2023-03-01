import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: [],
    lizts: [],

}

export const dashboardSlice = createSlice({
    name: 'dashboardSlice',
    initialState,
    reducers: {
        save: (state, action) => {
            state.wp = action.payload.wp
            state.wps = action.payload.wps || []
            state.board = action.payload.board
            state.boards = action.payload.boards || []
            state.wpMems = action.payload.wpMems || []
            state.lizts = action.payload.lizts || []
        },
        updateOrdinal: (s, a) => {
            const result = a.payload
            const srcEle = s.lizts[result.source.index]
            const desEle = s.lizts[result.destination.index]
            s.lizts.splice(result.source.index, 1)
            s.lizts.splice(result.destination.index, 0, srcEle)
        }
    },
})

export const {save, updateOrdinal} = dashboardSlice.actions


export default dashboardSlice.reducer
