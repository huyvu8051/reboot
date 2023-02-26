import {createSlice} from '@reduxjs/toolkit'

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
        save: (state, action) => {
            state.wp = action.wp
            state.wps = action.wps
            state.board = action.board
            state.boards = action.boards
            state.wpMems = action.wpMems
        },
    },
})

export const {save} = wpSlice.actions


export default wpSlice.reducer
