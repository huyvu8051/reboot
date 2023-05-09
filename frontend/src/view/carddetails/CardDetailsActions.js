import {Button} from "@mui/material";
import {ArrowForward, Category, Check, CheckBox, ContentCopy, Delete, HideSource, Share} from "@mui/icons-material";
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


const CardAddToTemplate = () => {


    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>Actions</h5>
            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                <Button {...actionButton} startIcon={<ArrowForward/>}>Move</Button>
                <Button {...actionButton} startIcon={<ContentCopy/>}>Copy</Button>
                <Button {...actionButton} startIcon={<Category/>} endIcon={<CheckBox color='success'/>}>Template</Button>
                <Button {...actionButton} startIcon={<HideSource/>}>Hide from list</Button>
                <Button
                    style={{
                        textTransform: 'none',
                        justifyContent: 'flex-start'
                    }}
                    color='error'
                    size='small'
                    disableElevation
                    variant='contained' startIcon={<Delete/>}>Delete</Button>
                <Button {...actionButton} startIcon={<Share/>}>Share</Button>
            </div>
        </div>
    )

}


export default CardAddToTemplate