import LeftDrawer from './leftDrawer/LeftDrawer'
import {Box, IconButton} from '@mui/material'
import {useEffect, useState} from 'react'
import {ArrowLeft, ArrowRight} from '@mui/icons-material'
import RightDrawer from './leftDrawer/RightDrawer'
import {io} from 'socket.io-client'
import {useDispatch, useSelector} from 'react-redux'
import {updateAttachment, updateBoard, updateCard, updateLizt} from './dashboard-slice'
import {useParams} from 'react-router-dom'
import api from '../../service/api';
import Cookies from 'js-cookie'

function Workspace(props) {

    const [openLeft, setOpenLeft] = useState(false)
    const [openRight, setOpenRight] = useState(false)

    const wId = useSelector(sts => sts.dashboard.wp?.id || null)
    const dispatch = useDispatch()
    const {bId, cId} = useParams()
    useEffect(() => {
        if (wId) {
            const socket = io('/dashboard', {
                query: {
                    wId: wId
                },
                transports: ['websocket']
            })
            socket.on('update.dashboard.list', r => {
                dispatch(updateLizt(r))
            })
            socket.on('update.dashboard.card', r => {
                dispatch(updateCard(r))
            })
            socket.on('update.dashboard.attachment', r => {
                dispatch(updateAttachment(r))
            })

            socket.on('update.dashboard.board', r => {
                dispatch(updateBoard(r))
                api('/api/resource/token').then(res => {
                    Cookies.set('resToken', res.body, {expires: 7, path: '/'})
                })
            })

            return () => {
                socket.off()
                socket.disconnect()
            }
        }

    }, [wId, bId, cId, dispatch])

    return <>
        <Box height='100%'>
            {
                !openLeft && <>
                    <IconButton size='small' sx={{
                        position: 'fixed',
                        left: 0,
                        mt: 2,
                        zIndex: (theme) => theme.zIndex.drawer + 1
                    }}
                                onClick={() => setOpenLeft(true)}>
                        <ArrowRight fontSize='small'/>
                    </IconButton>
                </>
            }
            <LeftDrawer open={openLeft} setOpen={setOpenLeft}/>
            <Box sx={{
                ml: openLeft ? '226.078px' : 4,
                mr: openRight ? '500px' : 0,
                height: '100%',
                backgroundColor: 'transparent'
            }}>
                {props.children}
            </Box>

            <RightDrawer open={openRight} setOpen={setOpenRight}/>
            {
                !openRight && <>
                    <IconButton size='small' sx={{
                        position: 'fixed',
                        right: 0,
                        top: 50,
                        mt: 2,
                        zIndex: (theme) => theme.zIndex.drawer + 1
                    }}
                                onClick={() => setOpenRight(true)}>
                        <ArrowLeft fontSize='small'/>
                    </IconButton>
                </>
            }
        </Box>
    </>
}

export default Workspace