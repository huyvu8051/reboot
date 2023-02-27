import {Box, IconButton, Typography} from '@mui/material'
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
import {CalendarMonth, Close, Image, Info, TableRows, TextFields} from "@mui/icons-material";
import {useSelector} from "react-redux";
import Toolbar from "@mui/material/Toolbar";

const RightDrawer = (props) => {
    const navigate = useNavigate()

    const wp = useSelector(sts => sts.dashboard.wp)

    return (
        <Drawer
            variant='persistent'
            anchor='right'
            PaperProps={{
                sx: {
                    width: '500px'
                }
            }}
            open={props.open}
        >
            <Toolbar/>
            <Box sx={{display: 'flex', alignItems: 'center', m: 1}}>
                {
                    wp && <>
                        <Typography>
                            Setting
                        </Typography>
                        <IconButton size='small' sx={{marginLeft: 'auto'}} onClick={() => props.setOpen(false)}>
                            <Close fontSize='small'/>
                        </IconButton>
                    </>
                }
            </Box>
            <Divider/>
            {
                wp && <>
                    <List dense>
                        <ListItem key='board' disablePadding>
                            <ListItemButton>
                                <ListItemIcon>
                                    <Info/>
                                </ListItemIcon>
                                <ListItemText primary='About this board'/>
                            </ListItemButton>
                        </ListItem>
                        <ListItem key='members' disablePadding>
                            <ListItemButton>
                                <ListItemIcon>
                                    <Image/>
                                </ListItemIcon>
                                <ListItemText primary='Change background'/>
                            </ListItemButton>
                            <IconButton size='small' sx={{color: 'black'}}>
                                <AddIcon fontSize='small'/>
                            </IconButton>
                        </ListItem>
                        <ListItem key='settings' disablePadding>
                            <ListItemButton>
                                <ListItemIcon>
                                    <TextFields/>
                                </ListItemIcon>
                                <ListItemText primary='Custom field'/>
                            </ListItemButton>
                            <SettingWpIcon/>
                        </ListItem>
                    </List>
                    <Divider/>
                    <List dense>
                        <ListItem key='activity' disablePadding>
                            <ListItemButton>
                                <ListItemText primary='Activity'/>
                            </ListItemButton>
                            <CreateBoardBtn/>
                        </ListItem>
                        <ListBoard/>
                    </List>
                </>
            }
        </Drawer>)
}
export default RightDrawer