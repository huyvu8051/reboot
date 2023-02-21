import IconButton from '@mui/material/IconButton'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore'
import {Menu, MenuItem} from '@mui/material'
import * as React from 'react'

let SettingWpBtn = () => {
    const [settingAnchorEl, setSettingAnchorEl] = React.useState(null)


    const handleSettingMenu = (event) => {
        setSettingAnchorEl(event.currentTarget)
    }

    const handleSettingClose = () => {
        setSettingAnchorEl(null)
    }
    return (
        <>
            <IconButton size='small' sx={{color: 'black'}}
                        aria-controls='menu-setting'
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
        </>
    )
};
export default SettingWpBtn