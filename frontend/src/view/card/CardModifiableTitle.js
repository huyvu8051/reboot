import * as React from 'react'
import {useRef} from 'react'
import {useDispatch, useSelector} from 'react-redux'
import {updateCard} from '../workspace/dashboard-slice'
import api from '../../service/api'
import {DialogContentText, TextField} from '@mui/material'

const CardModifiableTitle = () => {
    const inputRef = useRef()
    const dispatch = useDispatch()

    const item = useSelector(sts => sts.dashboard.card)

    const handleSubmit = () => {
        const inputVal = inputRef.current.value.trim()
        if (inputVal !== item.title) {
            dispatch(updateCard({
                ...item,
                title: inputVal
            }))
            api.put('/api/v1/user/card/details', {
                id: item.id,
                title: inputVal
            }).then()
        } else {
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

        <DialogContentText
            onBlur={handleBlur}
        >
            <TextField inputRef={inputRef}
                       fullWidth
                       multiline
                       onBlur={handleBlur}

                       sx={{
                           '& fieldset': {
                               borderWidth: 0,
                           },
                           '& .MuiInputBase-root': {
                               padding: 1,
                           },
                       }}
                       inputProps={{
                           style: {
                               fontSize: 'large',
                               fontWeight: 'bold',

                           },
                           onKeyDown: handleKeyDown
                       }}
                       defaultValue={item.title}/>
        </DialogContentText>
    )
}
export default CardModifiableTitle