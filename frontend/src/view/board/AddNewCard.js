import {Button, CardActions, IconButton, TextField} from '@mui/material'
import {Add, AddCard, Clear} from '@mui/icons-material'
import {useRef, useState} from 'react'
import api from "../../service/api";
import {$success} from "../../util/snackbar-utils";
import {useSelector} from "react-redux";

const AddNewList = ({lId}) => {

    const [addCardOpen, setAddCardOpen] = useState(false);
    const inputRef = useRef()

    const bId = useSelector(sts => sts.dashboard.board?.id || null)
    const handleSubmit = () => {
        if (bId) {
            api.post('/api/v1/user/card', null, {
                params: {
                    lId,
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
        <>
            {
                addCardOpen ? (
                    <>
                        <TextField
                            inputRef={inputRef}
                            onKeyDown={handleKeyDown}
                            // onBlur={()=>setOpen(false)}
                            sx={{
                                m: 1,
                            }}

                            autoFocus
                            size='small'/>
                        <CardActions>
                            <Button size='small' onClick={handleSubmit}
                                    sx={{textTransform: 'none'}}>
                                Add
                            </Button>
                            <IconButton size='small' onClick={() => setAddCardOpen(false)}>
                                <Clear fontSize='small'/>
                            </IconButton>

                        </CardActions>
                    </>
                ) : (
                    <CardActions sx={{display: 'flex', flex: 0}}>
                        <Button
                            onClick={() => setAddCardOpen(true)}
                            sx={{
                                flex: 1,
                                justifyContent: 'flex-start',
                                color: 'rgba(0, 0, 0, 0.54)',
                                textTransform: 'none'
                            }}
                            size='small'
                            startIcon={<Add/>}>
                            {'Add a card'}
                        </Button>
                        <IconButton size='small'>
                            <AddCard fontSize='small'/>
                        </IconButton>
                    </CardActions>
                )
            }
        </>)
}
export default AddNewList