import {useMediaQuery} from '@mui/material'
import MuiAppBar from '@mui/material/AppBar'
import Box from '@mui/material/Box'
import CssBaseline from '@mui/material/CssBaseline'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import * as React from 'react'
import {useEffect} from 'react'
import {useDispatch} from 'react-redux'
import {Outlet} from 'react-router-dom'
import {io} from 'socket.io-client'
import LeftDrawer from '../message/LeftDrawer'
import {saveMsg} from '../message/message-slice'


export default function PersistentDrawerLeft(props) {
    const dispatch = useDispatch()

    useEffect(() => {
        const socket = io('/chat', {
            /*   query: {
                   wId: wId
               },
               transports: ['websocket']*/
        })
        socket.on('sendMsg', r => {
            dispatch(saveMsg(r))
        })


        return () => {
            socket.off()
            socket.disconnect()
        }

    }, [dispatch])
    const isScreen600pxOrAbove = useMediaQuery('(min-width:600px)')
    const marginTop = isScreen600pxOrAbove ? '64px' : '56px'
    return (
        <Box>
            <CssBaseline/>
            <MuiAppBar
                elevation={0}
                position="fixed"
                sx={{
                    backgroundColor: 'white',
                    zIndex: (theme) => theme.zIndex.drawer + 1
                }}>
                <Toolbar>
                    <Typography
                        variant="h6"
                        component="div"
                        color="black">
                        Reboot
                    </Typography>
                </Toolbar>
            </MuiAppBar>
            <LeftDrawer/>

            <Box component="main" sx={{
                marginTop: marginTop,
                width: '100%',
                height: `calc(100vh - ${marginTop} - 25px)`


            }}>
                <Outlet/>
            </Box>
        </Box>
    )
}