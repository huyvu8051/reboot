import {Box, IconButton} from '@mui/material'
import {useEffect} from 'react'
import {useDispatch} from 'react-redux'
import {io} from 'socket.io-client'
import Conversations from './Conversations'
import {sendMsg} from './message-slice'

function Message(props) {

    const dispatch = useDispatch()
    useEffect(() => {
        const socket = io('/chat', {
            // query: {
            //     wId: wId
            // },
            // transports: ['websocket']
        })
        socket.on('sendMsg', r => {
            console.log('sendMsg')
            dispatch(sendMsg(r))
        })


        return () => {
            socket.off()
            socket.disconnect()
        }

    }, [dispatch])

    return <>
        <Box height="100%">
            <IconButton size="small" sx={{
                position: 'fixed',
                left: 0,
                mt: 2,
                zIndex: (theme) => theme.zIndex.drawer + 1
            }}
            >
            </IconButton>
            <Conversations/>
            <Box sx={{
                ml: '226.078px',
                height: '100%',
                backgroundColor: 'transparent'
            }}>
                {props.children}
            </Box>
        </Box>
    </>
}

export default Message