import {createSlice} from '@reduxjs/toolkit'

const saveMsgsItem = (s, msg) => {
    const foundMsg = s.msgs.find(e => e.id === msg.id)
    if (foundMsg) {
        Object.assign(foundMsg, msg)
    } else {
        s.msgs.push(msg)
    }
}



const saveConvsItem = (s, msg) => {
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
}
const sortConvs = (s) => {
    s.convs = [...s.convs].sort((a, b) => b.lastMsgTime - a.lastMsgTime)
}

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
            // console.log(msg)
            saveMsgsItem(s, msg)
            saveConvsItem(s, msg)
            sortConvs(s)
        }


    }
})


export const {save, saveMsg} = messageSlice.actions


export default messageSlice.reducer
