import {CardHeader, IconButton, TextField} from '@mui/material';
import {MoreHoriz} from '@mui/icons-material';
import {useEffect, useRef, useState} from 'react';
import {useDispatch} from 'react-redux';
import {updateLizt} from '../workspace/dashboard-slice';
import api from '../../service/api';

const TaskListHeader = ({item}) => {
    const inputRef = useRef();
    const dispatch = useDispatch();
    const [inputValue, setInputValue] = useState(item.title);
    const [isFocused, setIsFocused] = useState(false);
    const handleFocus = () => {
        setIsFocused(true);
    };


    useEffect(() => {
        if (!isFocused) {
            setInputValue(item.title);
        }
    }, [item.title]);

    const handleSubmit = () => {
        const inputVal = inputValue.trim();
        if (inputVal && inputVal !== item.title) {
            dispatch(
                updateLizt({
                    ...item,
                    title: inputVal,
                })
            );
            api
                .put('/api/v1/user/list', null, {
                    params: {
                        lId: item.id,
                        ordinal: item.ordinal,
                        title: inputVal,
                    },
                })
                .then();
        } else {
            setInputValue(item.title);
        }
    };

    const handleBlur = ({currentTarget, relatedTarget}) => {
        if (currentTarget.contains(relatedTarget)) return;
        setIsFocused(false);
        handleSubmit();
    };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter' && !event.shiftKey) {
            event.preventDefault();
            inputRef.current.blur();
        }

       /* if(event.key === 'Escape'){
            event.preventDefault();
            setInputValue(item.title);
            inputRef.current.blur();
        }*/
    };

    const handleChange = (event) => {
        setInputValue(event.target.value);
    };

    return (
        <CardHeader
            sx={{
                p: 1,
            }}
            title={(
                <TextField
                    inputRef={inputRef}
                    fullWidth
                    multiline
                    onFocus={handleFocus

                }
                    onBlur={handleBlur}
                    sx={{
                        '& fieldset': {
                            borderWidth: 0,
                        },
                        '& .MuiInputBase-root': {
                            padding: 0.5,
                        },
                    }}
                    inputProps={{
                        style: {
                            fontSize: 'small',
                            fontWeight: 'bold',
                        },
                        onKeyDown: handleKeyDown,
                    }}
                    value={inputValue}
                    onChange={handleChange}
                />
            )}
            action={(
                <IconButton size="small" sx={{borderRadius: 1, mr: 0.5}}>
                    <MoreHoriz fontSize="small"/>
                </IconButton>
            )}
        />
    );
};

export default TaskListHeader;
