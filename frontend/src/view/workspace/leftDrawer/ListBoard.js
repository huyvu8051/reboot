import ListItemButton from "@mui/material/ListItemButton";
import {Avatar, ListItemAvatar} from "@mui/material";
import ListItemText from "@mui/material/ListItemText";
import ListItem from "@mui/material/ListItem";
import * as React from "react";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import api from "../../../service/api";

export default () => {

    const [boards, setBoards] = useState([]);
    const navigate = useNavigate();
    useEffect(()=>{
        api.post('v1/user/board')
            .then(resp=>setBoards(resp.data))
    }, [])


    return (
        <>
            {
                boards.map(item=>(
                    <ListItem key='board' disablePadding onClick={() => navigate(`/b/${item.id}`)}>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar variant='rounded' alt='Remy Sharp'
                                        src='https://mui.com/static/images/avatar/1.jpg'/>
                            </ListItemAvatar>
                            <ListItemText primary='Board'/>
                        </ListItemButton>
                    </ListItem>
                ))
            }
        </>
    )
}