import * as React from 'react'
import {useCallback, useState} from 'react'
import {Button, Dialog, List, TextField} from '@mui/material'
import {AccountBox, Attachment, Checklist, Label, TextFields, Visibility} from '@mui/icons-material'
import {useNavigate} from 'react-router-dom'
import {useSelector} from 'react-redux'
import IconButton from "@mui/material/IconButton";
import DialogContent from "@mui/material/DialogContent";
import ListItem from "@mui/material/ListItem";
import CardModifiableTitle from "./CardModifiableTitle";
import CardHeaderCover from "./CardHeaderCover";
import CardListDialog from "./CardListDialog";
import CardMembers from "./CardMembers";
import CardLabels from "./CardLabels";
import CardNotifications from "./CardNotifications";
import CardDate from "./CardDate";

const actionButton = {
    style: {
        backgroundColor: 'rgba(9, 30, 66, 0.04)',
        color: 'inherit'
    },
    disableElevation: true,
    variant: 'contained'
}

const CardDetails = () => {
    const [open, setOpen] = useState(true)
    const handleClickOpen = () => {
        setOpen(true)
    }
    const navigate = useNavigate()
    const bId = useSelector(sts => sts.dashboard.board?.id || null)


    const card = useSelector(sts => sts.dashboard.card)


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
                <DialogContent sx={{display: 'flex'}}>
                    <div style={{flex: 0}}>
                        <CardModifiableTitle/>
                        <CardListDialog/>
                        <CardMembers/>
                        <CardLabels/>
                        <CardNotifications/>
                        <CardDate/>

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
                        </List></div>
                    <div style={{flex: 1}}>
                        <div>
                            <h5>Add to template</h5>
                            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                                <Button {...actionButton} startIcon={<AccountBox/>}>Members</Button>
                                <Button {...actionButton} variant='contained' startIcon={<Label/>}>Labels</Button>
                                <Button {...actionButton} startIcon={<Checklist/>}>Checklist</Button>
                                <Button {...actionButton}  startIcon={<Attachment/>}>Attachment</Button>
                                <Button {...actionButton} startIcon={<TextFields/>}>Custom Fields</Button>
                            </div>
                        </div>
                    </div>
                </DialogContent>
            </Dialog>
        </div>
    )
}

export default CardDetails