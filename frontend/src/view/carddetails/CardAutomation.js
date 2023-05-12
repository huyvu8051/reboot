import {Button} from "@mui/material";
import {AccountBox, Attachment, Checklist, Label, TextFields} from "@mui/icons-material";
import * as React from "react";


const actionButton = {
    style: {
        backgroundColor: 'rgba(9, 30, 66, 0.04)',
        color: 'inherit',
        textTransform: 'none',
        fontWeight: '400',
        justifyContent: 'flex-start'
    },
    size: 'small',
    disableElevation: true,
    variant: 'contained'
}


const CardAddToTemplate = ()=>{


    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>Automation</h5>
            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                <Button {...actionButton} startIcon={<TextFields/>}>Add button</Button>
            </div>
        </div>
    )

}


export default CardAddToTemplate