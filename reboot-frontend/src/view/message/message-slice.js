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
    boardLabels: [],
    attachments: {
        items: [],
        totalCount: 0
    },
    convs: [],
    conv: null,
    msgs: []

}

export const messageSlice = createSlice({
    name: 'messageSlice',
    initialState,
    reducers: {
        save: (s, a) => {
            Object.assign(s, a.payload)
        },

        sendMsg: (s, a) => {
            const find = s.msgs.find(e => e.id === a.payload.id)

            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.msgs.push(a.payload)
            }
        },
        updateLizt: (s, a) => {
            const find = s.lizts.find(e => e.id === a.payload.id)

            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.lizts.push(a.payload)
            }
            if (s.card && s.card.list.id === a.payload.id) {
                Object.assign(s.card.list, a.payload)
            }
        },
        updateConvs: (s, a) => {
            const find = s.lizts.find(e => e.id === a.payload.id)

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
            const find = s.cards.find(e => e.id === a.payload.id)
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
            const find = s.boards.find(e => e.id === a.payload.id)
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.boards.push(a.payload)
            }
        },

        updateAttachment: (s, a) => {
            const find = s.attachments.items.find(e => e.id === a.payload.id)
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.attachments.items.unshift(a.payload)
                s.attachments.totalCount++
            }
        },
        updateLabeled: (s, a) => {
            const find = s.cardLabels.find(e => e.id && (e.id === a.payload.id || e.labelId === a.payload.labelId))
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.cardLabels.push(a.payload)
            }
        },
        updateLabel: (s, a) => {
            const find = s.boardLabels.find(e => e.id && e.id === a.payload.id)
            if (find) {
                Object.assign(find, a.payload)
            } else {
                s.boardLabels.push(a.payload)
            }
        }
    }
})


export const {save, sendMsg, updateConvs} = messageSlice.actions


export default messageSlice.reducer
