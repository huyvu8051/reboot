import * as React from 'react'
import {useEffect} from 'react'
import Box from '@mui/material/Box'
import CssBaseline from '@mui/material/CssBaseline'
import MuiAppBar from '@mui/material/AppBar'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import NewWorkspace from './NewWorkspace'
import {Outlet, useParams} from 'react-router-dom'
import api from '../../service/api'
import {useDispatch} from 'react-redux'
import {save} from './dashboard-slice'
import {useMediaQuery} from '@mui/material'
import ChangeWpBtn from './leftDrawer/ChangeWpBtn'
import bgImg from '../../asset/background/photo-1674413146454-41e62f015153.jpg'


export default function PersistentDrawerLeft(props) {
    const {bId, wId, cId} = useParams()
    const dispatch = useDispatch()

    useEffect(() => {
        api.get('/api/v1/user/dashboard', {
            params: {bId, wId, cId}
        }).then(r => dispatch(save(r)))
    }, [bId, wId, cId, dispatch])

    const isScreen600pxOrAbove = useMediaQuery('(min-width:600px)')
    const marginTop = isScreen600pxOrAbove ? '64px' : '56px'
    return (
        <Box >
            <CssBaseline/>
            <MuiAppBar
                elevation={0}
                position='fixed'
                sx={{
                    backgroundColor: 'white',
                    zIndex: (theme) => theme.zIndex.drawer + 1
                }}>
                <Toolbar>
                    <Typography
                        variant='h6'
                        component='div'
                        color='black'>
                        Reboot
                    </Typography>
                    <NewWorkspace/>
                    <ChangeWpBtn/>
                </Toolbar>
            </MuiAppBar>
            <img style={{
                position: 'fixed',
                width: '100vw',
                zIndex: -99
            }} src={bgImg}
            alt='background reboot'/>
            <Box component='main' sx={{
                marginTop: marginTop,
                width: '100%',
                height: `calc(100vh - ${marginTop} - 25px)`,


            }}>
                <Outlet/>
            </Box>
        </Box>
    )
}