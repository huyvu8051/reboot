import LeftDrawer from "./leftDrawer/LeftDrawer";
import {Box, IconButton} from "@mui/material";
import {useState} from "react";
import {ArrowLeft, ArrowRight} from "@mui/icons-material";
import RightDrawer from "./leftDrawer/RightDrawer";

function Workspace(props) {

    const [openLeft, setOpenLeft] = useState(true)
    const [openRight, setOpenRight] = useState(true)

    return <>
        <Box height='100%'>
            {
                !openLeft && <>
                    <IconButton size='small' sx={{
                        position: 'fixed',
                        left: 0,
                        mt:2,
                        zIndex: (theme) => theme.zIndex.drawer + 1
                    }}
                                onClick={() => setOpenLeft(true)}>
                        <ArrowRight fontSize='small'/>
                    </IconButton>
                </>
            }
            <LeftDrawer open={openLeft} setOpen={setOpenLeft}/>
            <Box sx={{ml: openLeft ? '226.078px' : 4,mr: openRight ? '500px' : 0, backgroundColor:'blue', height:'100%'}}>
                {props.children}
            </Box>

            <RightDrawer open={openRight} setOpen={setOpenRight}/>
            {
                !openRight && <>
                    <IconButton size='small' sx={{
                        position: 'fixed',
                        right: 0,
                        top:50,
                        mt:2,
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