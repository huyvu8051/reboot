import {AvatarGroup, Button, Chip, Dialog, DialogContentText, Icon, List, TextField} from '@mui/material'
import * as React from 'react'
import {useCallback, useState} from 'react'
import {useNavigate} from 'react-router-dom'
import {useSelector} from 'react-redux'
import {
    CardMembership,
    CenterFocusStrong,
    Close,
    PanoramaFishEye,
    TagFaces, Visibility,
    Watch,
    WatchLater
} from '@mui/icons-material'
import IconButton from "@mui/material/IconButton";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import cardImg from '../../asset/image/paella.jpg'
import Avatar from "@mui/material/Avatar";
import ListItem from "@mui/material/ListItem";


const CardDetails = () => {
    const [open, setOpen] = useState(true)
    const navigate = useNavigate()
    const handleClickOpen = () => {
        setOpen(true)
    }

    const [chipData, setChipData] = useState([
        {key: 0, label: 'Angular'},
        {key: 1, label: 'jQuery'},
        {key: 2, label: 'Polymer'},
        {key: 3, label: 'React'},
        {key: 4, label: 'Vue.js'},
    ]);

    const bId = useSelector(sts => sts.dashboard.board?.id || null)
    const card = useSelector(sts => sts.dashboard.card)

    const handleClose = useCallback(() => {
        setOpen(false)
        navigate(`/b/${bId}`)
    }, [bId])

    return card && (
        <div>
            <Button variant='outlined' onClick={handleClickOpen}>
                Open alert dialog
            </Button>
            <Dialog
                open={open}
                scroll='body'
                onClose={handleClose}
                aria-labelledby='alert-dialog-title'
                aria-describedby='alert-dialog-description'

            >
                <DialogTitle>
                    <img src={cardImg}/>
                    <IconButton title='cover'>
                        <CardMembership/>
                    </IconButton>
                    <IconButton onClick={handleClose}>
                        <Close/>
                    </IconButton>
                </DialogTitle>
                <DialogContent dividers>

                    <DialogContentText>
                        Card title
                    </DialogContentText>

                    <DialogContentText>
                        In list todo
                    </DialogContentText>

                    <AvatarGroup max={4}>
                        <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg"/>
                        <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg"/>
                        <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg"/>
                        <Avatar alt="Agnes Walker" src="/static/images/avatar/4.jpg"/>
                        <Avatar alt="Trevor Henderson" src="/static/images/avatar/5.jpg"/>
                    </AvatarGroup>


                    members
                    {chipData.map((data) => {
                        let icon;

                        if (data.label === 'React') {
                            icon = <TagFaces/>;
                        }

                        return (
                            <ListItem key={data.key}>
                                <Chip
                                    icon={icon}
                                    label={data.label}

                                />
                            </ListItem>
                        );
                    })}

                    labels

                    {chipData.map((data) => {
                        let icon;

                        if (data.label === 'React') {
                            icon = <TagFaces/>;
                        }

                        return (
                            <ListItem key={data.key}>
                                <Chip
                                    icon={icon}
                                    label={data.label}

                                />
                            </ListItem>
                        );
                    })}

                    Notifications

                    <IconButton>
                        watch
                        <Visibility/>
                    </IconButton>
                    <IconButton>
                        Start date
                        <Visibility/>
                    </IconButton>

                    <TextField label="Description" multiline rows={4} fullWidth/>


                    Attachments
                    <List>
                        <ListItem>
                            att
                        </ListItem>
                        <ListItem>
                            att
                        </ListItem>
                        <ListItem>
                            att
                        </ListItem>
                        <ListItem>
                            att
                        </ListItem>
                        <ListItem>
                            att
                        </ListItem>
                    </List>

                    Activity
                    <List>
                        <ListItem>
                            act
                        </ListItem>
                        <ListItem>
                            act
                        </ListItem>
                        <ListItem>
                            act
                        </ListItem>
                        <ListItem>
                            act
                        </ListItem>
                        <ListItem>
                            act
                        </ListItem>
                    </List>

                </DialogContent>
            </Dialog>
        </div>
    )
}

export default CardDetails