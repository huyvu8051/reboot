import {Button} from "@mui/material";
import {AccountBox, Attachment, Checklist, Label, TextFields} from "@mui/icons-material";
import * as React from "react";


const actionButton = {
    style: {
        backgroundColor: 'rgba(9, 30, 66, 0.04)',
        color: 'inherit',
        textTransform: 'none',
        justifyContent: 'flex-start'
    },
    size: 'small',
    disableElevation: true,
    variant: 'contained'
}


const CardAddToTemplate = ()=>{


    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>Add to template</h5>
            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                <Button {...actionButton} startIcon={<AccountBox/>}>Members</Button>
                <Button {...actionButton} variant='contained' startIcon={<Label/>}>Labels</Button>
                <Button {...actionButton} startIcon={<Checklist/>}>Checklist</Button>
                <Button {...actionButton} startIcon={<Attachment/>}>Attachment</Button>
                <Button {...actionButton} startIcon={<TextFields/>}>Custom Fields</Button>
            </div>
        </div>
    )

}


export default CardAddToTemplate