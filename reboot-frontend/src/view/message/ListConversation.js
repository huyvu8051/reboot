import ListItemButton from '@mui/material/ListItemButton'
import {Avatar, ListItemAvatar} from '@mui/material'
import ListItemText from '@mui/material/ListItemText'
import ListItem from '@mui/material/ListItem'
import * as React from 'react'
import {useNavigate} from 'react-router-dom'
import {useSelector} from "react-redux";


const ListBoard = () => {

    const navigate = useNavigate()
    const convs = useSelector(sts => sts.message.convs);
    return (
        <>
            {
                convs.map(item => (
                    <ListItem key={item.id} disablePadding onClick={() => navigate(`/b/${item.id}`)}>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar variant='rounded' alt='Remy Sharp'
                                        src='https://mui.com/static/images/avatar/1.jpg'
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