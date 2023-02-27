import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: []
}

export const dashboardSlice = createSlice({
    name: 'dashboardSlice',
    initialState,
    reducers: {
        save: (state, action) => {
            state.wp = action.payload.wp
            state.wps = action.payload.wps
            state.board = action.payload.board
            state.boards = action.payload.boards
            state.wpMems = action.payload.wpMems
        },
    },
})

export const {save} = dashboardSlice.actions


export default dashboardSlice.reducer
