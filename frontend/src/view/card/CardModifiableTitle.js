import * as React from "react";
import {useRef, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {updateCard} from "../workspace/dashboard-slice";
import api from "../../service/api";
import {DialogContentText, TextField} from "@mui/material";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import {MoreHoriz} from "@mui/icons-material";

const CardModifiableTitle = () => {
    const inputRef = useRef();
    const dispatch = useDispatch();

    const item = useSelector(sts => sts.dashboard.card)
    console.log(item)
    const [open, setOpen] = useState(false);

    const handleSubmit = () => {
        const inputVal = inputRef.current.value.trim();
        if (inputVal) {
            dispatch(updateCard({
                ...item,
                title: inputVal
            }))
            setOpen(false)
            api.put('/api/v1/user/card/details', {
                cId: item.id,
                title: inputVal
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

        <DialogContentText
            onBlur={handleBlur}

            sx={{
                p: 1
            }}

            action={(
                <IconButton size='small' sx={{borderRadius: 1, mr: .5}}>
                    <MoreHoriz fontSize='small'/>
                </IconButton>
            )}
        >
            {
                open ? (
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
                                       fontSize: 'large',
                                       fontWeight: 'bold'
                                   },
                                   onKeyDown: handleKeyDown
                               }}
                               defaultValue={item.title}/>
                ) : (

                    <Typography
                        onClick={() => setOpen(true)}
                        sx={{
                            fontSize: 'large',
                            fontWeight: 'bold',
                            color: 'black',
                            paddingY: 0,
                            paddingX: 0,
                            border: '4px solid transparent'
                        }}>
                        {item.title}
                    </Typography>
                )
            }
        </DialogContentText>
    );
};
export default CardModifiableTitle