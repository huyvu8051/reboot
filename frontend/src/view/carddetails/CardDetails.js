import * as React from 'react'
import {useCallback, useState} from 'react'
import {Button, Dialog, List, TextField} from '@mui/material'
import {useNavigate} from 'react-router-dom'
import {useSelector} from 'react-redux'
import DialogContent from '@mui/material/DialogContent'
import ListItem from '@mui/material/ListItem'
import CardModifiableTitle from './CardModifiableTitle'
import CardHeaderCover from './CardHeaderCover'
import CardListDialog from './CardListDialog'
import CardMembers from './CardMembers'
import CardLabels from './CardLabels'
import CardNotifications from './CardNotifications'
import CardDate from './CardDate'
import CardAddToTemplate from "./CardAddToTemplate";
import CardPowerUps from "./CardPowerUps";
import CardAutomation from "./CardAutomation";
import CardDetailsActions from "./CardDetailsActions";
import CardAttachments from "./CardAttachments";



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
                <DialogContent>
                    <CardModifiableTitle/>
                    <CardListDialog/>
                </DialogContent>
                <DialogContent sx={{display: 'flex'}}>
                    <div style={{flex: 3}}>

                        <CardMembers/>
                        <CardLabels/>
                        <CardNotifications/>
                        <CardDate/>

                        <TextField label='Description' multiline rows={4} fullWidth/>


                        <CardAttachments/>

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
                        <CardAddToTemplate/>
                        <CardPowerUps/>
                        <CardAutomation/>
                        <CardDetailsActions/>
                    </div>
                </DialogContent>
            </Dialog>
        </div>
    )
}

export default CardDetails