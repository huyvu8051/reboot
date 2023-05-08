import * as React from 'react'
import {useCallback, useState} from 'react'
import {AvatarGroup, Button, Chip, Dialog, DialogContentText, List, TextField} from '@mui/material'
import {TagFaces, Visibility, VisibilityOff} from '@mui/icons-material'
import {useNavigate} from 'react-router-dom'
import {useSelector} from 'react-redux'
import IconButton from "@mui/material/IconButton";
import DialogContent from "@mui/material/DialogContent";
import Avatar from "@mui/material/Avatar";
import ListItem from "@mui/material/ListItem";
import CardModifiableTitle from "./CardModifiableTitle";
import CardHeaderCover from "./CardHeaderCover";


const CardDetails = () => {
    const [open, setOpen] = useState(true)
    const handleClickOpen = () => {
        setOpen(true)
    }
    const navigate = useNavigate()
    const bId = useSelector(sts => sts.dashboard.board?.id || null)


    const card = useSelector(sts => sts.dashboard.card)
    const cardMems = useSelector(sts => sts.dashboard.cardMems)
    const cardLabels = useSelector(sts => sts.dashboard.cardLabels)

    const handleClose = useCallback(() => {
        setOpen(false)
        navigate(`/b/${bId}`)
    }, [bId, navigate])


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

                <CardHeaderCover setOpen={setOpen} handleClose={handleClose}/>

                <DialogContent dividers>

                    <CardModifiableTitle/>
                    <DialogContentText>
                        in list {card.liztTitle}
                    </DialogContentText>

                    members
                    <AvatarGroup>
                        {
                            cardMems.map(e => (
                                <Avatar alt={e.fullName} src={e.pictureUrl}/>
                            ))
                        }
                    </AvatarGroup>


                    labels

                    {cardLabels.map((data) => {
                        let icon;

                        if (data.label === 'React') {
                            icon = <TagFaces/>;
                        }

                        return (
                            <ListItem key={data.id}>
                                <Chip
                                    icon={icon}
                                    label={data.title}

                                />
                            </ListItem>
                        );
                    })}

                    Notifications

                    <IconButton>
                        {
                            card.notification ? <>
                                watching
                                <Visibility/>
                            </> : <>
                                watch
                                <VisibilityOff/>
                            </>
                        }
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