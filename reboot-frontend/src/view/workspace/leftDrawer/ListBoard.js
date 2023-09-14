import {Avatar, ListItemAvatar} from '@mui/material'
import ListItem from '@mui/material/ListItem'
import ListItemText from '@mui/material/ListItemText'
import Typography from '@mui/material/Typography'
import * as React from 'react'
import {useSelector} from 'react-redux'
import {useNavigate} from 'react-router-dom'


const ListBoard = () => {

    const navigate = useNavigate()
    const convs = useSelector(sts => sts.message.convs)
    console.log('render', convs)
    return (
        <>
        {
            convs.map(item => (
                    <ListItem key={item.id} alignItems="flex-start" disablePadding
                              onClick={() => navigate(`/message/${item.id}`)} sx={{
                        cursor: 'pointer', ':hover': {
                            backgroundColor: '#efefef'
                        }
                    }}>
                        <ListItemAvatar>
                            <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg"
                                    src="https://mui.com/static/images/avatar/1.jpg"
                                    sx={{height: '30px', width: '30px'}}/>
                        </ListItemAvatar>
                        <ListItemText
                            primary={item.nm}
                            secondary={
                                item.uId && <>
                                    <Typography
                                        sx={{display: 'inline'}}
                                        component="span"
                                        variant="body2"
                                        color="text.primary"
                                    >
                                        {`${item.uId}: `}
                                    </Typography>
                                    {item.lastMsgContent}
                                </>
                            }
                        />
                    </ListItem>
                )
            )
        }


        </>
    )
}
export default ListBoard