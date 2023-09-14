import {Avatar, ListItemAvatar} from '@mui/material'
import ListItem from '@mui/material/ListItem'
import ListItemButton from '@mui/material/ListItemButton'
import ListItemText from '@mui/material/ListItemText'
import * as React from 'react'
import {useSelector} from 'react-redux'
import {useNavigate} from 'react-router-dom'


const ListBoard = () => {

    const navigate = useNavigate()
    const convs = useSelector(sts => sts.message.convs)
    console.log('render')
    return (
        <>
            {
                convs.map(item => (
                    <ListItem key={item.lastMsgTime} disablePadding onClick={() => navigate(`/b/${item.id}`)}>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar variant="rounded" alt="Remy Sharp"
                                        src="https://mui.com/static/images/avatar/1.jpg"
                                        sx={{height: '30px', width: '30px'}}/>
                            </ListItemAvatar>
                            <ListItemText primary={item.nm}/>
                        </ListItemButton>
                    </ListItem>
                ))
            }
        </>
    )
}
export default ListBoard