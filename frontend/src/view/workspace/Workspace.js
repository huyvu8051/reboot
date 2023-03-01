import LeftDrawer from "./leftDrawer/LeftDrawer";
import {Box, IconButton} from "@mui/material";
import {useEffect, useState} from "react";
import {ArrowLeft, ArrowRight} from "@mui/icons-material";
import RightDrawer from "./leftDrawer/RightDrawer";
import {io} from "socket.io-client";
import {useDispatch, useSelector} from "react-redux";
import api from "../../service/api";
import {save} from "./dashboard-slice";
import {useParams} from "react-router-dom";

function Workspace(props) {

    const [openLeft, setOpenLeft] = useState(true)
    const [openRight, setOpenRight] = useState(false)

    const wId = useSelector(sts => sts.dashboard.wp?.id || null);
    const dispatch = useDispatch();
    const {bId, cId} = useParams();
    useEffect(() => {
        if (wId) {
            const socket = io('/dashboard', {
                query: {
                    wId: wId
                }
            })
            socket.on('update.dashboard', d => {
                api.get('/api/v1/user/dashboard', {
                    params: {bId, wId, cId}
                }).then(r => dispatch(save(r)))
                console.log('update.dashboard', d)
            })

            return ()=>{
                socket.off()
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
                height: '100%'
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