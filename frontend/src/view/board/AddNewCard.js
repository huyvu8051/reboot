import {Button, CardActions, IconButton, TextField} from '@mui/material'
import {Add, AddCard, Clear} from '@mui/icons-material'
import {useRef, useState} from 'react'
import api from "../../service/api";
import {$success} from "../../util/snackbar-utils";
import {useSelector} from "react-redux";

const AddNewCard = ({lId}) => {

    const [addCardOpen, setAddCardOpen] = useState(false);
    const inputRef = useRef()

    const bId = useSelector(sts => sts.dashboard.board?.id || null)
    const handleSubmit = () => {
        if (bId && inputRef.current.value.trim()) {
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

    const handleBlur = ({currentTarget, relatedTarget}) => {
        if (currentTarget.contains(relatedTarget)) return

        setAddCardOpen(false)
    }


    return (
        <div
            style={{
                marginRight: 0.5,
                marginLeft: 0.5
            }}

        >
            {
                addCardOpen ? (
                    <div
                        onBlur={handleBlur}
                    >
                        <TextField
                            inputRef={inputRef}
                            onKeyDown={handleKeyDown}

                            sx={{
                                m: 1,
                            }}

                            autoFocus
                            size='small'/>
                        <CardActions>
                            <Button size='small'
                                    onClick={handleSubmit}
                                    sx={{textTransform: 'none'}}>
                                Add
                            </Button>
                            <IconButton size='small'
                                        onClick={() => setAddCardOpen(false)}
                            >
                                <Clear fontSize='small'/>
                            </IconButton>

                        </CardActions>
                    </div>
                ) : (
                    <CardActions sx={{display: 'flex', flex: 0, px: 0}}>
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
        </div>)
}
export default AddNewCard