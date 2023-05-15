import * as React from 'react'
import {useCallback, useState} from 'react'
import {Button, Dialog, List} from '@mui/material'
import {useNavigate} from 'react-router-dom'
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
import api from "../../service/api";
import {updateCard} from "../workspace/dashboard-slice";

import {useDispatch, useSelector} from "react-redux";
import CardDescription from "./CardDescription";
import CardActivity from "./CardActivity";

const CardDetails = () => {
    const dispatch = useDispatch();
    const [open, setOpen] = useState(true)
    const handleClickOpen = () => {
        setOpen(true)
    }
    const navigate = useNavigate()
    const bId = useSelector(sts => sts.dashboard.board?.id || null)
    const cId = useSelector(sts => sts.dashboard.card?.id || null)


    const card = useSelector(sts => sts.dashboard.card)


    const handleClose = useCallback(() => {
        setOpen(false)
        navigate(`/b/${bId}`)
    }, [bId, navigate])


    const handleDrop = (event) => {
        event.preventDefault();
        const files = event.dataTransfer.files;
        // Process the dropped files
        processFiles(files);
    };


    const processFiles = (files) => {
        // Process the files here, e.g., read file contents, upload to server, etc.
        console.log(files);
        for (let i = 0; i < files.length; i++) {
            const file = files[i]
            if (!file) return
            const formData = new FormData();
            formData.append("file", file);

            api.post("/api/v1/user/dashboard/card/attachment/" + cId, formData).then(r => {
                console.log(r)
                api.put('/api/v1/user/dashboard/card/details', {
                    id: card.id,
                    coverUrl: r
                }).then()

                dispatch(updateCard({
                    ...card,
                    coverUrl: r
                }))
            })
        }
    };

    const processFilesAttachments = (files) => {
        // Process the files here, e.g., read file contents, upload to server, etc.
        console.log(files);
        for (let i = 0; i < files.length; i++) {
            const file = files[i]
            if (!file) return
            const formData = new FormData();
            formData.append("file", file);

            api.post("/api/v1/user/dashboard/card/attachment/" + cId, formData).then(r => {
                console.log(r)

            })
        }
    };

    const handleDropAttachments = (event) => {
        event.preventDefault();
        const files = event.dataTransfer.files;
        // Process the dropped files
        processFilesAttachments(files);
    };


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
                sx={{
                    '& .MuiPaper-root': {
                        backgroundColor: '#F1F2F4'
                    }

                }}

            >
                <CardHeaderCover setOpen={setOpen} handleClose={handleClose}/>
                <DialogContent onDrop={handleDrop}
                               onDragOver={(event) => event.preventDefault()} // Ensure drop events are allowed
                >
                    <CardModifiableTitle/>
                    <CardListDialog/>
                </DialogContent>
                <DialogContent sx={{display: 'flex', gap: 1}} onDrop={handleDropAttachments}
                               onDragOver={(event) => event.preventDefault()}>
                    <div style={{flex: 3}}>

                        <CardMembers/>
                        <CardLabels/>
                        <CardNotifications/>
                        <CardDate/>
                        <CardDescription/>
                        <CardAttachments/>
                        <CardActivity/>

                    </div>
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