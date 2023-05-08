import {CardHeader, IconButton, TextField} from "@mui/material";
import {MoreHoriz} from "@mui/icons-material";
import {useRef, useState} from "react";
import Typography from "@mui/material/Typography";
import {useDispatch} from "react-redux";
import {updateLizt} from "../workspace/dashboard-slice";
import api from "../../service/api";

const TaskListHeader = ({item}) => {
    const [open, setOpen] = useState(false);
    const inputRef = useRef();
    const dispatch = useDispatch();

    const handleSubmit = () => {
        const inputVal = inputRef.current.value.trim();
        if (inputVal) {
            dispatch(updateLizt({
                ...item,
                title: inputVal
            }))
            setOpen(false)
            api.put('/api/v1/user/list', null, {
                data: {
                    lId: item.id,
                    ordinal: item.ordinal,
                    title : inputVal
                }
            }).then()
        }
    }

    const handleBlur = ({currentTarget, relatedTarget}) => {
        if (currentTarget.contains(relatedTarget)) return
        handleSubmit()

    }

    const handleKeyDown = (event) => {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault()
            handleSubmit()
        }
    }

    return (
        <CardHeader
            onBlur={handleBlur}

            sx={{
                p: 1
            }}
            title={open ? (
                <TextField inputRef={inputRef}

                           fullWidth
                           multiline
                           autoFocus

                           sx={{
                               '& .MuiInputBase-root': {
                                   paddingY: 0,
                                   paddingX: 1,
                               },
                           }}

                           inputProps={{
                               style: {
                                   fontSize: 'small',
                                   fontWeight: 'bold'
                               },
                               onKeyDown: handleKeyDown
                           }}
                           defaultValue={item.title}/>
            ) : (
                <Typography
                    onClick={() => setOpen(true)}
                    sx={{
                        fontSize: 'small',
                        fontWeight: 'bold',
                        paddingY: 0,
                        paddingX: 0,
                        border: '4px solid transparent'
                    }}>
                    {item.title}
                </Typography>
            )}

            action={(
                <IconButton size='small' sx={{borderRadius: 1, mr: .5}}>
                    <MoreHoriz fontSize='small'/>
                </IconButton>
            )}
        />

    )
}
export default TaskListHeader