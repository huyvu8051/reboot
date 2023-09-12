import {TextField} from '@mui/material'
import * as React from 'react'
import {useEffect, useRef, useState} from 'react'
import {useDispatch, useSelector} from 'react-redux'
import api from '../../service/api'
import {updateCard} from '../workspace/dashboard-slice'

const CardModifiableTitle = () => {
    const inputRef = useRef()
    const dispatch = useDispatch()

    const item = useSelector(sts => sts.dashboard.card)
    const [inputValue, setInputValue] = useState(item.title)
    const [isFocused, setIsFocused] = useState(false)

    const handleFocus = () => {
        setIsFocused(true)
    }

    useEffect(() => {
        if (!isFocused) {
            setInputValue(item.title)
        }
    }, [item.title])

    const handleSubmit = () => {
        const inputVal = inputRef.current.value.trim()
        if (inputVal !== item.title) {
            dispatch(updateCard({
                ...item,
                title: inputVal
            }))
            api.put('/api/v1/user/dashboard/card/details', {
                id: item.id,
                title: inputVal
            }).then()
        } else {
            inputRef.current.value = item.title
        }
    }
    const handleBlur = ({currentTarget, relatedTarget}) => {
        if (currentTarget.contains(relatedTarget)) return
        setIsFocused(false)
        handleSubmit()

    }

    const handleKeyDown = (event) => {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault()
            inputRef.current.blur()
        }

        /* if(event.key === 'Escape'){
            event.preventDefault();
            setInputValue(item.title);
            inputRef.current.blur();
        }*/
    }
    const handleChange = (event) => {
        setInputValue(event.target.value)
    }

    return (

        <TextField inputRef={inputRef}
                   fullWidth
                   multiline
                   onBlur={handleBlur}

                   sx={{
                       '& fieldset': {
                           borderWidth: 0
                       },
                       '& .MuiInputBase-root': {
                           padding: 1,
                           left: -8
                       },
                       paddingRight: 3
                   }}
                   inputProps={{
                       style: {
                           fontSize: 20,
                           fontWeight: 600,
                           lineHeight: '24px'
                       },
                       onKeyDown: handleKeyDown
                   }}
                   value={inputValue}
                   onChange={handleChange}/>
    )
}
export default CardModifiableTitle