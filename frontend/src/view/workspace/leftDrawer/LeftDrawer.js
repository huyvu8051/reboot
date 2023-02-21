import {Avatar, Link} from '@mui/material'
import IconButton from '@mui/material/IconButton'
import Divider from '@mui/material/Divider'
import List from '@mui/material/List'
import ListItem from '@mui/material/ListItem'
import ListItemButton from '@mui/material/ListItemButton'
import ListItemIcon from '@mui/material/ListItemIcon'
import DashboardIcon from '@mui/icons-material/Dashboard'
import ListItemText from '@mui/material/ListItemText'
import PermIdentityIcon from '@mui/icons-material/PermIdentity'
import AddIcon from '@mui/icons-material/Add'
import SettingsIcon from '@mui/icons-material/Settings'
import TableRowsIcon from '@mui/icons-material/TableRows'
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth'
import Drawer from '@mui/material/Drawer'
import * as React from 'react'
import {useNavigate, useParams} from 'react-router-dom'
import DrawerHeader from '../DrawerHeader'
import ChangeWpIcon from './ChangeWpBtn'
import SettingWpIcon from './SettingWpBtn'
import ListBoard from './ListBoard'
import NewBoardBtn from './CreateBoardBtn'

const drawerWidth = 240

let LeftDrawer = (props) => {
    const navigate = useNavigate()
    const {wpId} = useParams()

    return (
        <Drawer
            sx={{
                width: drawerWidth,
                flexShrink: 0,
                '& .MuiDrawer-paper': {
                    width: drawerWidth,
                    boxSizing: 'border-box',
                },
            }}
            variant='persistent'
            anchor='left'
            open={props.open}
        >
            <DrawerHeader>
                <Avatar variant='rounded' alt='Remy Sharp' src='https://mui.com/static/images/avatar/1.jpg'/>
                <Link sx={{ml: 2, textDecoration: 'none', color: 'black'}}>viva_odc</Link>
                <ChangeWpIcon/>
            </DrawerHeader>
            <Divider/>
            <List dense>
                <ListItem key='board' disablePadding>
                    <ListItemButton onClick={() => navigate(`/w/${wpId}`)}>
                        <ListItemIcon>
                            <DashboardIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Board'/>
                    </ListItemButton>
                </ListItem>
                <ListItem key='members' disablePadding>
                    <ListItemButton onClick={() => navigate(`/w/${wpId}/members`)}>
                        <ListItemIcon>
                            <PermIdentityIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Members'/>
                    </ListItemButton>
                    <IconButton size='small' sx={{color: 'black'}}>
                        <AddIcon fontSize='small'/>
                    </IconButton>
                </ListItem>
                <ListItem key='settings' disablePadding>
                    <ListItemButton>
                        <ListItemIcon>
                            <SettingsIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Settings'/>

                    </ListItemButton>
                    <SettingWpIcon/>
                </ListItem>

            </List>

            <Divider/>
            <List dense>
                <ListItem key='wp-views' disablePadding>
                    <ListItemButton>
                        <ListItemText primary='Workspace views'/>

                    </ListItemButton>
                    <IconButton size='small' sx={{color: 'black'}}>
                        <AddIcon fontSize='small'/>
                    </IconButton> </ListItem>
                <ListItem key='table' disablePadding>
                    <ListItemButton>
                        <ListItemIcon>
                            <TableRowsIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Table'/>
                    </ListItemButton>
                </ListItem>
                <ListItem key='calendar' disablePadding>
                    <ListItemButton>
                        <ListItemIcon>
                            <CalendarMonthIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Calendar'/>
                    </ListItemButton>
                </ListItem>
            </List>
            <Divider/>
            <List dense>
                <ListItem key='your-boards' disablePadding>
                    <ListItemButton>
                        <ListItemText primary='Your boards'/>

                    </ListItemButton>
                    <NewBoardBtn/>
                </ListItem>
                <ListBoard/>
            </List>

        </Drawer>)
}
export default LeftDrawer