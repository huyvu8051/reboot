import {ArrowBack, Close} from '@mui/icons-material'
import {Button, IconButton, TextField} from '@mui/material'
import * as React from 'react'
import {useCallback, useState} from 'react'
import {useSelector} from 'react-redux'
import api from '../../../service/api'

const actionButton = {
    style: {
        backgroundColor: 'rgba(9, 30, 66, 0.04)',
        textTransform: 'none',
        fontWeight: '400',
        justifyContent: 'center',
        width: '100%'
    },
    size: 'small',
    disableElevation: true,
    variant: 'contained'
}

const colors = []
const pushColor = (backgroundColor, color) => {
    colors.push({backgroundColor, color})
}
pushColor('#164b35', '#b6c2cf')
pushColor('#533f04', '#b6c2cf')
pushColor('#5f3811', '#b6c2cf')
pushColor('#601e16', '#b6c2cf')
pushColor('#352c63', '#b6c2cf')

pushColor('#216e4e', '#b6c2cf')
pushColor('#7f5f01', '#b6c2cf')
pushColor('#974f0c', '#b6c2cf')
pushColor('#ae2a19', '#b6c2cf')
pushColor('#5e4db2', '#b6c2cf')

pushColor('#4bce97', '#1d2125')
pushColor('#e2b203', '#1d2125')
pushColor('#faa53d', '#1d2125')
pushColor('#f87462', '#1d2125')
pushColor('#9f8fef', '#1d2125')

pushColor('#09326c', '#b6c2cf')
pushColor('#1d474c', '#b6c2cf')
pushColor('#37471f', '#b6c2cf')
pushColor('#50253f', '#b6c2cf')
pushColor('#454f59', '#b6c2cf')

pushColor('#0055cc', '#b6c2cf')
pushColor('#206b74', '#b6c2cf')
pushColor('#4c6b1f', '#b6c2cf')
pushColor('#943d73', '#b6c2cf')
pushColor('#596773', '#b6c2cf')

pushColor('#579dff', '#1d2125')
pushColor('#60c6d2', '#1d2125')
pushColor('#94c748', '#1d2125')
pushColor('#e774bb', '#1d2125')
pushColor('#8c9bab', '#1d2125')

const EditLabel = ({handleClosePopup, handleCloseEdit, editLabel}) => {

    const [currLabel, setCurrLabel] = useState(editLabel || {
        id: null,
        color: null,
        title: null,
        bId: null
    })

    const bId = useSelector(sts => sts.dashboard.board?.id)
    console.log(bId)

    const [color, setColor] = useState(JSON.parse(currLabel.color) || colors[0])

    const [title, setTitle] = useState(currLabel.title || '')

    const handleSave = useCallback((isDeleted) => {

        api.put('/api/v1/user/dashboard/card/label', {
            id: currLabel.id,
            bId: bId,
            title,
            color: JSON.stringify(color),
            isDeleted
        }).then()
    }, [currLabel.id, bId, title, color])


    return <>
        <IconButton sx={{position: 'absolute', right: 0, top: 0, borderRadius: 1, padding: 0, margin: .5}}
                    size="small" onClick={handleClosePopup}>
            <Close fontSize="small"/>
        </IconButton>
        <IconButton sx={{position: 'absolute', left: 0, top: 0, borderRadius: 1, padding: 0, margin: .5}}
                    size="small" onClick={handleCloseEdit}>
            <ArrowBack fontSize="small"/>
        </IconButton>
        <p style={{fontSize: 13, fontWeight: '400', margin: 0}} align="center">Edit labels</p>
        <div style={{
            width: '100%',
            display: 'flex',
            justifyContent: 'center'
        }}>
            <Button
                style={{
                    height: 30,
                    backgroundColor: 'rgba(9, 30, 66, 0.04)',
                    textTransform: 'none',
                    fontWeight: '400',
                    justifyContent: 'center',
                    ...color
                }}
                size="small"
                disableElevation
                variant="contained"
            >
                {title}
            </Button>
        </div>
        <p>Title</p>
        <TextField fullWidth inputProps={{style: {padding: 5}}} value={title}
                   onChange={e => setTitle(e.target.value)}/>
        <p>Select color</p>
        <div style={{
            display: 'flex',
            flexWrap: 'wrap'
        }}>
            {
                colors.map((e, i) => {
                    return (
                        <Button key={i} sx={{...e, height: 30}} onClick={() => setColor(colors[i])}/>
                    )
                })
            }
        </div>
        <Button
            {...actionButton}
            startIcon={<Close fontSize="small"/>}
        >
            Remove color
        </Button>

        <Button onClick={() => {
            handleSave()
            handleCloseEdit()
        }}>Save</Button>
        <Button onClick={() => {
            handleSave(true)
            handleCloseEdit()
        }
        }>Delete</Button>
    </>
}
export default EditLabel