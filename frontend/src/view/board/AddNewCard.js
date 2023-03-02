import {Button, Card, CardActions, IconButton, TextField} from '@mui/material'
import {Add, Clear} from '@mui/icons-material'
import Box from '@mui/material/Box'
import {useRef, useState} from 'react'
import api from '../../service/api'
import {$success} from '../../util/snackbar-utils'
import {useSelector} from 'react-redux'

const AddNewList = () => {
    const [open, setOpen] = useState(true)

    const inputRef = useRef()

    const bId = useSelector(sts => sts.dashboard.board?.id || null)

    const handleSubmit = () => {
        if (bId) {
            api.post('/api/v1/user/list', null, {
                params: {
                    bId,
                    title: inputRef.current.value
                }
            }).then(() => {
                inputRef.current.value = ''
                $success('create success ✔')
            })
        }
    }

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault()
            handleSubmit()
        }
    }


    return (
        <Box
            sx={{
                boxSizing: 'border-box',
                margin: '0 4px',
            }}
            elevation={0}
            // onBlur={()=>setOpen(false)}
        >
            <Card

                sx={{
                    backgroundColor: 'rgba(235,236,240,0.3)',
                    borderRadius: '3px',
                    boxSizing: 'border-box',
                    display: 'flex',
                    flexDirection: 'column',
                    maxHeight: '100%',
                    minWidth: '270px',
                }}

                elevation={0}>

                {
                    open && (
                        <TextField
                            inputRef={inputRef}
                            onKeyDown={handleKeyDown}
                            // onBlur={()=>setOpen(false)}
                            sx={{
                                m: 1,
                            }}

                            autoFocus
                            size='small'/>
                    )
                }



            </Card>


        </Box>
    )
}
export default AddNewList