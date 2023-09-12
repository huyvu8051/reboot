import {AccountBox, Attachment, Checklist, Label, TextFields} from '@mui/icons-material'
import {Button} from '@mui/material'
import * as React from 'react'
import AddLabel from './label/AddLabel'


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


const CardAddToTemplate = () => {


    return (
        <>
            <h5 style={{padding: 0, margin: 0}}>Add to template</h5>
            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                <Button {...actionButton} startIcon={<AccountBox/>}>Members</Button>
                <AddLabel
                    activator={e => (
                        <Button {...actionButton} {...e} variant="contained" startIcon={<Label/>}>Labels</Button>
                    )}
                />

                <Button {...actionButton} startIcon={<Checklist/>}>Checklist</Button>
                <Button {...actionButton} startIcon={<Attachment/>}>Attachment</Button>
                <Button {...actionButton} startIcon={<TextFields/>}>Custom Fields</Button>
            </div>
        </>
    )

}


export default CardAddToTemplate