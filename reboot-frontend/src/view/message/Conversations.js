import {Avatar, Box, Link} from '@mui/material'
import Divider from '@mui/material/Divider'
import Drawer from '@mui/material/Drawer'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'
import ListItemButton from '@mui/material/ListItemButton'
import ListItemText from '@mui/material/ListItemText'
import Toolbar from '@mui/material/Toolbar'
import * as React from 'react'
import {useSelector} from 'react-redux'
import {useNavigate} from 'react-router-dom'
import ListBoard from '../workspace/leftDrawer/ListBoard'
import CreateConversationBtn from './CreateConversationBtn'

const Conversations = (props) => {
    const navigate = useNavigate()

    const conv = useSelector(sts => sts.message.conv)

    return (
        <Drawer
            variant="persistent"
            anchor="left"
            open={true}
        >
            <Toolbar/>
            <Box sx={{display: 'flex', alignItems: 'center', m: 1}}>
                <Avatar variant="rounded" alt="Remy Sharp" src={''}/>
                <Link sx={{ml: 2, textDecoration: 'none', color: 'black'}}>{'User name'}</Link>
            </Box>
            <Divider/>
            <List dense>
                <ListItem key="your-boards" disablePadding>
                    <ListItemButton>
                        <ListItemText primary="Conversations"/>
                    </ListItemButton>
                    <CreateConversationBtn/>
                </ListItem>
                <ListBoard/>
            </List>
        </Drawer>)
}
export default Conversations