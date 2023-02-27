import {useEffect, useState} from 'react'
import api from '../../../service/api'
import {useNavigate} from 'react-router-dom'

import ExpandMoreIcon from '@mui/icons-material/ExpandMore'
import {Avatar, Menu, MenuItem} from '@mui/material'
import {$on} from "../../../util/eventbus-utils";
import Button from "@mui/material/Button";


const ChangeWpBtn = () => {
    const navigate = useNavigate()

    const [lazyFetch, setLazyFetch] = useState(true);
    const [anchorEl, setAnchorEl] = useState(null)


    const handleWpSelect = (item) => {
        navigate(`/w/${item.id}`)
        setAnchorEl(null)
    }

    const handleWpClose = () => {
        setAnchorEl(null)
    }

    const [wps, setWps] = useState([])

    const handleWpSelectMenu = (event) => {
        if (lazyFetch) {
            api.post('/api/v1/user/workspace')
                .then(setWps)
                .then(() => setLazyFetch(false))
        }
        setAnchorEl(event.currentTarget)
    }

    useEffect(() => {
        const turnOnLF = () => setLazyFetch(true)
        const off = $on('ChangeWpBtn.lswp.refresh', turnOnLF);
        return () => off()
    }, [])


    return (
        <>
            <Button size='small'
                    sx={{color: 'black', right: 0, position: 'absolute'}}
                    aria-controls='menu-appbar'
                    aria-haspopup='true'
                    onClick={handleWpSelectMenu}
                    endIcon={<ExpandMoreIcon fontSize='small'/>}>
                {'Change wp'}

            </Button>
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