import {Avatar, Link, ListItemAvatar, Menu, MenuItem} from '@mui/material'
import IconButton from '@mui/material/IconButton'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore'
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
import DrawerHeader from './DrawerHeader'

const drawerWidth = 240

export default (props) => {
    const navigate = useNavigate()
    const {wpCd} = useParams()
    const bdCd = 'chung ta'
    const [anchorEl, setAnchorEl] = React.useState(null)
    const [settingAnchorEl, setSettingAnchorEl] = React.useState(null)
    const handleWpSelectMenu = (event) => {
        setAnchorEl(event.currentTarget)
    }

    const handleSettingMenu = (event) => {
        setSettingAnchorEl(event.currentTarget)
    }

    const handleWpSelectClose = () => {
        setAnchorEl(null)
    }
    const handleSettingClose = () => {
        setSettingAnchorEl(null)
    }
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
                <Link sx={{ml: 2, textDecoration: 'none', color: 'black'}} href='/'>viva_odc</Link>
                <IconButton size='small'
                            sx={{color: 'black', right: 0, position: 'absolute'}}
                            aria-controls='menu-appbar'
                            aria-haspopup='true'
                            onClick={handleWpSelectMenu}>
                    <ExpandMoreIcon fontSize='small'/>
                </IconButton>
                <Menu
                    id='menu-appbar'
                    anchorEl={anchorEl}
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'right',
                    }}
                    keepMounted
                    transformOrigin={{
                        vertical: 'top',
                        horizontal: 'left',
                    }}
                    open={Boolean(anchorEl)}
                    onClose={handleWpSelectClose}
                >
                    <MenuItem onClick={handleWpSelectClose}><Avatar sx={{width: 24, height: 24, mr: 1}}
                                                                    variant='rounded'
                                                                    alt='Remy Sharp'
                                                                    src='https://mui.com/static/images/avatar/1.jpg'/> viva_odc</MenuItem>
                    <MenuItem onClick={handleWpSelectClose}><Avatar sx={{width: 24, height: 24, mr: 1}}
                                                                    variant='rounded'
                                                                    alt='Remy Sharp'
                                                                    src='https://mui.com/static/images/avatar/1.jpg'/>chung_ta</MenuItem>
                    <MenuItem onClick={handleWpSelectClose}><Avatar sx={{width: 24, height: 24, mr: 1}}
                                                                    variant='rounded'
                                                                    alt='Remy Sharp'
                                                                    src='https://mui.com/static/images/avatar/1.jpg'/>odc_mlc</MenuItem>
                </Menu>

            </DrawerHeader>
            <Divider/>
            <List dense>
                <ListItem key='board' disablePadding>
                    <ListItemButton onClick={() => navigate(`/w/${wpCd}`)}>
                        <ListItemIcon>
                            <DashboardIcon/>
                        </ListItemIcon>
                        <ListItemText primary='Board'/>
                    </ListItemButton>
                </ListItem>
                <ListItem key='members' disablePadding>
                    <ListItemButton onClick={() => navigate(`/w/${wpCd}/members`)}>
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
                    <IconButton size='small' sx={{color: 'black'}} aria-controls='menu-setting'
                                aria-haspopup='true'
                                onClick={handleSettingMenu}>
                        <ExpandMoreIcon fontSize='small'/>
                    </IconButton>
                    <Menu
                        id='menu-setting'
                        anchorEl={settingAnchorEl}
                        anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'right',
                        }}
                        keepMounted
                        transformOrigin={{
                            vertical: 'top',
                            horizontal: 'left',
                        }}
                        open={Boolean(settingAnchorEl)}
                        onClose={handleSettingClose}
                    >
                        <MenuItem onClick={handleSettingClose}>Workspace settings</MenuItem>
                        <MenuItem onClick={handleSettingClose}>Export</MenuItem>
                        <MenuItem onClick={handleSettingClose}>Billing</MenuItem>
                        <MenuItem onClick={handleSettingClose}>Power-Ups</MenuItem>
                    </Menu>
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
                    <IconButton size='small' sx={{color: 'black'}}>
                        <AddIcon fontSize='small'/>
                    </IconButton>
                </ListItem>
                <ListItem key='board' disablePadding onClick={() => navigate(`/b/${bdCd}`)}>
                    <ListItemButton>
                        <ListItemAvatar>
                            <Avatar variant='rounded' alt='Remy Sharp'
                                    src='https://mui.com/static/images/avatar/1.jpg'/>
                        </ListItemAvatar>
                        <ListItemText primary='Board'/>
                    </ListItemButton>
                </ListItem>
            </List>

        </Drawer>)
}