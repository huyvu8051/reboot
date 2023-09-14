import {createSlice} from '@reduxjs/toolkit'

const initialState = {
    convs: [],
    msgs: []

}

export const messageSlice = createSlice({
    name: 'messageSlice',
    initialState,
    reducers: {
        save: (s, a) => {
            Object.assign(s, a.payload)
        },

        saveMsg: (s, a) => {
            const msg = a.payload
            console.log(msg)
            const foundMsg = s.msgs.find(e => e.id === msg.id)
            if (foundMsg) {
                Object.assign(foundMsg, msg)
            } else {
                s.msgs.push(msg)
            }

            const foundConv = s.convs.find(e => e.id == msg.cId)
            if (foundConv) {
                Object.assign(foundConv, {
                    lastMsgContent: msg.content,
                    lastMsgTime: msg.sendAt,
                    uId: msg.uId
                })
            } else {
                s.convs.push({
                    id: msg.cId,
                    nm: 'loading...',
                    lastMsgTime: msg.sendAt,
                    lastMsgContent: msg.content,
                    uId: msg.uId
                })
            }

            s.convs = [...s.convs].sort((a, b) => b.lastMsgTime - a.lastMsgTime)
        }


    }
})


export const {save, saveMsg} = messageSlice.actions


export default messageSlice.reducer
