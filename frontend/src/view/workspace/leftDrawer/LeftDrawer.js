import {Avatar, Box, IconButton, Link} from '@mui/material'
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
import Drawer from '@mui/material/Drawer'
import * as React from 'react'
import {useNavigate} from 'react-router-dom'
import SettingWpIcon from './SettingWpBtn'
import ListBoard from "./ListBoard";
import CreateBoardBtn from "./CreateBoardBtn";
import {
    ArrowBack,
    ArrowBackIos,
    ArrowBackIosNew,
    ArrowLeft,
    CalendarMonth,
    ChevronLeft,
    TableRows
} from "@mui/icons-material";
import {useSelector} from "react-redux";
import Toolbar from "@mui/material/Toolbar";

let LeftDrawer = (props) => {
    const navigate = useNavigate()

    const wp = useSelector(sts => sts.dashboard.wp)

    return (
        <Drawer
            variant='persistent'
            anchor='left'
            open={props.open}
        >
            <Toolbar/>
            <Box sx={{display: 'flex', alignItems: 'center', m: 1}}>
                {
                    wp && <>
                        <Avatar variant='rounded' alt='Remy Sharp' src={wp.pictureUrl}/>
                        <Link sx={{ml: 2, textDecoration: 'none', color: 'black'}}>{wp.title}</Link>
                        <IconButton size='small' sx={{marginLeft: 'auto'}} onClick={() => props.setOpen(false)}>
                            <ChevronLeft fontSize='small'/>
                        </IconButton>
                    </>
                }
            </Box>
            <Divider/>
            {
                wp && <>
                    <List dense>
                        <ListItem key='board' disablePadding>
                            <ListItemButton onClick={() => navigate(`/w/${wp.id}`)}>
                                <ListItemIcon>
                                    <DashboardIcon/>
                                </ListItemIcon>
                                <ListItemText primary='Board'/>
                            </ListItemButton>
                        </ListItem>
                        <ListItem key='members' disablePadding>
                            <ListItemButton onClick={() => navigate(`/w/${wp.id}/members`)}>
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
                                    <TableRows/>
                                </ListItemIcon>
                                <ListItemText primary='Table'/>
                            </ListItemButton>
                        </ListItem>
                        <ListItem key='calendar' disablePadding>
                            <ListItemButton>
                                <ListItemIcon>
                                    <CalendarMonth/>
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
                            <CreateBoardBtn/>
                        </ListItem>
                        <ListBoard/>
                    </List>
                </>
            }
        </Drawer>)
}
export default LeftDrawer