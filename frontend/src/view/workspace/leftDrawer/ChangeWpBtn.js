import {useEffect, useState} from 'react'
import api from '../../../service/api'
import {useNavigate} from 'react-router-dom'

import ExpandMoreIcon from '@mui/icons-material/ExpandMore'
import {Avatar, IconButton, Menu, MenuItem} from '@mui/material'


const ChangeWpBtn = () => {
    const [anchorEl, setAnchorEl] = useState(null)
    const navigate = useNavigate()
    const handleWpSelectMenu = (event) => {
        setAnchorEl(event.currentTarget)
    }
    const handleWpSelect = (item) => {
        navigate(`/w/${item.id}`)
        setAnchorEl(null)
    }

    const handleWpClose = () => {
        setAnchorEl(null)
    }

    const [wps, setWps] = useState([])

    useEffect(() => {

        // api.post('/api/v1/user/workspace')
        //     .then(resp => setWps(resp.data))

    }, [])
    return (
        <>
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
                onClose={handleWpClose}
            >
                {
                    wps.length === 0 && (
                        <MenuItem>
                            No workspace exist
                        </MenuItem>)
                }
                {
                    wps.map(item => (
                        <MenuItem onClick={() => handleWpSelect(item)} key={item.id}>
                            <Avatar sx={{width: 24, height: 24, mr: 1}}
                                    variant='rounded'
                                    alt={item.title}
                                    src='https://mui.com/static/images/avatar/1.jpg'/>
                            {item.title}
                        </MenuItem>
                    ))
                }

            </Menu>
        </>
    )
}
export default ChangeWpBtn