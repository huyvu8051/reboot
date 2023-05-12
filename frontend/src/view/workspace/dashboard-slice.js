import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    wp: null,
    wps: [],
    board: null,
    boards: [],
    wpMems: [],
    lizts: [],
    cards: [],
    card: null,
    cardMems: [],
    cardLabels: [],
    attachments: null // card attachments

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
            } else {
                s.lizts.push(a.payload)
            }
            if (s.card && s.card.list.id === a.payload.id) {
                Object.assign(s.card.list, a.payload)
            }
        },
        updateCard: (s, a) => {
            const find = s.cards.find(e => e.id === a.payload.id);
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.cards.push(a.payload)
            }

            if (s.card && s.card.id === a.payload.id) {
                Object.assign(s.card, a.payload)
            }

        },
        updateBoard: (s, a) => {
            const find = s.boards.find(e => e.id === a.payload.id);
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.boards.push(a.payload)
            }
        },

        updateAttachment: (s, a) => {
            const find = s.attachments.find(e => e.id === a.payload.id);
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.boards.push(a.payload)
            }
        }
    },
})


export const {save, updateLizt, updateCard, updateBoard} = dashboardSlice.actions


export default dashboardSlice.reducer
