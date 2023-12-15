import {Box, IconButton} from '@mui/material'
import {useEffect} from 'react'
import {useDispatch} from 'react-redux'
import {useParams} from 'react-router-dom'
import api from '../../service/api'
import {save} from './message-slice'

function Message(props) {
    const dispatch = useDispatch()
    const {bId, wId, cId, convId} = useParams()
    useEffect(() => {
        api.post('/api/v1/message/init', {bId, wId, cId, convId}).then(r => dispatch(save(r)))
    }, [bId, wId, cId, convId, dispatch])

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