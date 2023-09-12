import {Add, GitHub} from '@mui/icons-material'
import {Button} from '@mui/material'
import * as React from 'react'


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
        <div>
            <h5 style={{padding: 0, margin: 0}}>Power-Ups</h5>
            <div style={{display: 'flex', flexDirection: 'column', gap: 5}}>
                <Button {...actionButton} startIcon={<GitHub/>}>Github</Button>
                <Button {...actionButton} startIcon={<Add/>}>Add Power-Ups</Button>
            </div>
        </div>
    )

}


export default CardAddToTemplate