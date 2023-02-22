import ListItemButton from '@mui/material/ListItemButton'
import {Avatar, ListItemAvatar} from '@mui/material'
import ListItemText from '@mui/material/ListItemText'
import ListItem from '@mui/material/ListItem'
import * as React from 'react'
import {useEffect, useState} from 'react'
import {useNavigate, useParams} from 'react-router-dom'
import api from '../../../service/api'
import {$off, $on} from "../../../util/eventbus-utils"


const ListBoard = () => {

    const [boards, setBoards] = useState([])
    const navigate = useNavigate()
    const {wpId} = useParams()


    useEffect(() => {
        const fetchBoardLs = () => {
            api.post('/api/v1/user/board', null, {
                params: {wpId}
            })
            .then(resp => {
                setBoards(resp.data)
            })
            .then(() => {
                console.log('then2')
            })
                // .catch((err)=>console.log('catch first',err))
        }
        fetchBoardLs()
        $on('boards.refresh', fetchBoardLs)
        return () => $off('boards.refresh', fetchBoardLs)


    }, [wpId])

    return (
        <>
            {
                boards.map(item => (
                    <ListItem key={item.id} disablePadding onClick={() => navigate(`/b/${item.id}`)}>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar variant='rounded' alt='Remy Sharp'
                                        src='https://mui.com/static/images/avatar/1.jpg'
                                        sx={{height: '30px', width: '30px'}}/>
                            </ListItemAvatar>
                            <ListItemText primary={item.name}/>
                        </ListItemButton>
                    </ListItem>
                ))
            }
        </>
    )
}
export default ListBoard