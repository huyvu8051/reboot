import {CardHeader, IconButton, TextField} from '@mui/material'
import {MoreHoriz} from '@mui/icons-material'
import {useRef} from 'react'
import {useDispatch} from 'react-redux'
import {updateLizt} from '../workspace/dashboard-slice'
import api from '../../service/api'

const TaskListHeader = ({item}) => {
    const inputRef = useRef()
    const dispatch = useDispatch()

    const handleSubmit = () => {
        const inputVal = inputRef.current.value.trim()
        if (inputVal && inputVal !== item.title) {
            dispatch(updateLizt({
                ...item,
                title: inputVal
            }))
            api.put('/api/v1/user/list', null, {
                params: {
                    lId: item.id,
                    ordinal: item.ordinal,
                    title: inputVal
                }
            }).then()
        }else {
            inputRef.current.value = item.title
        }
    }

    const handleBlur = ({currentTarget, relatedTarget}) => {
        if (currentTarget.contains(relatedTarget)) return
        handleSubmit()

    }

    const handleKeyDown = (event) => {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault()
            inputRef.current.blur()
        }
    }

    return (
        <CardHeader

            sx={{
                p: 1
            }}
            title={(
                <TextField inputRef={inputRef}
                           fullWidth
                           multiline
                           onBlur={handleBlur}

                           sx={{
                               '& fieldset': {
                                   borderWidth: 0,
                               },
                               '& .MuiInputBase-root': {
                                   padding: 0.5
                               },
                           }}

                           inputProps={{
                               style: {
                                   fontSize: 'small',
                                   fontWeight: 'bold'
                               },
                               onKeyDown: handleKeyDown
                           }}
                           defaultValue={item.title}/>)}

            action={(
                <IconButton size='small' sx={{borderRadius: 1, mr: .5}}>
                    <MoreHoriz fontSize='small'/>
                </IconButton>
            )}
        />

    )
}
export default TaskListHeader