import {createSlice} from '@reduxjs/toolkit'

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
        save: (state, action) => {
            state.wp = action.payload.wp
            state.wps = action.payload.wps || []
            state.board = action.payload.board
            state.boards = action.payload.boards || []
            state.wpMems = action.payload.wpMems || []
            state.lizts = action.payload.lizts || []

            state.cards = action.payload.cards || []
            state.lizts.forEach(e=>{
                e.cards = state.cards.filter(e2=>e2.liztId === e.id)
            })
        },
        updateLiztOrdinal: (s, a) => {
            const result = a.payload
            const srcEle = s.lizts[result.source.index]
            s.lizts.splice(result.source.index, 1)
            s.lizts.splice(result.destination.index, 0, srcEle)
        },
        updateCardOrdinal: (s, a)=>{
            const srcLs = s.lizts.find(e => e.id == a.payload.source.droppableId);
            const srcCard = srcLs.cards[a.payload.source.index];
            srcLs.cards.splice(a.payload.source.index, 1)
            const desLs = s.lizts.find(e => e.id == a.payload.destination.droppableId);
            // console.log('desLs', desLs)
            desLs.cards.splice(a.payload.destination.index, 0, srcCard)
        }
    },
})

export const {save, updateLiztOrdinal, updateCardOrdinal} = dashboardSlice.actions


export default dashboardSlice.reducer
