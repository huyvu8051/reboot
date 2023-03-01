import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: [],
    lists: [],

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
            state.lists = action.payload.lists || []
        },
    },
})

export const {save} = dashboardSlice.actions


export default dashboardSlice.reducer
