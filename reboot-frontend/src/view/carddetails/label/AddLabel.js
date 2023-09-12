import {Close, Edit} from '@mui/icons-material'
import {Button, Checkbox, IconButton, Popover, TextField, Typography} from '@mui/material'
import * as React from 'react'
import {useState} from 'react'
import {useDispatch, useSelector} from 'react-redux'
import api from '../../../service/api'
import {updateLabeled} from '../../workspace/dashboard-slice'
import EditLabel from './EditLabel'


const AddLabel = ({activator}) => {
    const dispatch = useDispatch()
    const [anchorEl, setAnchorEl] = useState()
    const boardLabels = useSelector(sts => sts.dashboard.boardLabels)
    const cardLabels = useSelector(sts => sts.dashboard.cardLabels)
    const cId = useSelector(sts => sts.dashboard.card?.id || null)
    const [edit, setEdit] = useState(false)

    // console.log(boardLabels, cardLabels)

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget)
    }

    const handleClose = () => {
        setAnchorEl(null)
    }
    const handleCloseEdit = () => {
        setEdit(false)
    }

    const open = Boolean(anchorEl)
    const id = open ? 'simple-popover' : undefined


    function handleChecked(data) {
        dispatch(updateLabeled(data))
        api.put('/api/v1/user/dashboard/card/labeled', data).then()

    }

    const [editLabel, setEditLabel] = useState()

    return <>
        {activator({'aria-describedby': id, onClick: handleClick})}
        <Popover
            id={id}
            open={open}
            anchorEl={anchorEl}
            onClose={handleClose}
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left'
            }}
            sx={{
                '& .MuiPaper-root': {
                    pt: .5,
                    pb: 1,
                    px: 1
                }
            }}
        >
            {
                edit ? <EditLabel editLabel={editLabel} handleCloseEdit={handleCloseEdit}
                                  handleClosePopup={handleClose}/> : <>
                    <IconButton sx={{position: 'absolute', right: 0, top: 0, borderRadius: 1, padding: 0, margin: .5}}
                                size="small" onClick={handleClose}>
                        <Close fontSize="small"/>
                    </IconButton>
                    <p style={{fontSize: 13, fontWeight: '400', margin: 0}} align="center">Labels</p>
                    <TextField placeholder="Search labels..." fullWidth inputProps={{style: {padding: 5}}}/>
                    <p>Labels</p>
                    {
                        boardLabels.map(e => {
                            if (e.isDeleted) return null
                            const find = cardLabels.find(item => item.labelId === e.id)

                            const checked = !(find?.isDeleted ?? true)
                            return (
                                <div key={e.id}
                                     style={{
                                         display: 'flex',
                                         alignItems: 'center',
                                         justifyItems: 'center',
                                         gap: 2,
                                         padding: .5
                                     }}>
                                    <Checkbox
                                        checked={checked}
                                        onClick={(event) => {
                                            handleChecked({
                                                id: find?.id ?? null,
                                                isDeleted: !event.target.checked,
                                                labelId: e.id,
                                                cardId: cId
                                            })
                                        }} size="medium" sx={{padding: 0, margin: .5}}/>
                                    <Typography bgcolor="green" color="white" borderRadius={1} px={1.5} py={.5}
                                                width="100%"
                                                sx={{
                                                    fontWeight: 500,
                                                    fontSize: 12,
                                                    cursor: 'pointer',
                                                    ...JSON.parse(e.color)
                                                }}>{e.title}</Typography>
                                    <IconButton size="small" style={{borderRadius: 5, padding: 0, margin: 1}}
                                                onClick={() => {
                                                    setEditLabel(e)
                                                    setEdit(true)
                                                }}>
                                        <Edit fontSize="small"/>
                                    </IconButton>
                                </div>)
                        })
                    }
                    <Button
                        disableElevation
                        size="small"
                        variant="contained"
                        style={{borderRadius: 3, margin: 2, textTransform: 'none', width: '100%'}}
                        onClick={() => {
                            setEditLabel(null)
                            setEdit(true)
                        }}
                    >
                        Create a new label
                    </Button>
                </>
            }
        </Popover>
    </>
}

export default AddLabel