import {Button, Card, CardActions, IconButton, TextField} from "@mui/material";
import {Add, Clear} from "@mui/icons-material";
import Box from "@mui/material/Box";
import {useRef, useState} from "react";
import api from "../../service/api";
import {$success} from "../../util/snackbar-utils";
import {useSelector} from "react-redux";

const AddNewList = () => {
    const [open, setOpen] = useState();

    const inputRef = useRef();

    const bId = useSelector(sts => sts.dashboard.board?.id || null);

    const handleSubmit = () => {
        if (bId) {
            api.post('/api/v1/user/list', null, {
                params: {
                    bId,
                    title: inputRef.current.value
                }
            }).then(() => {
                setOpen(false)
                // inputRef.current.value = ''
                $success('create success ✔')
            })
        }
    }

    return (
        <Box
            sx={{
                boxSizing: 'border-box',
                margin: '0 4px',
            }}
            elevation={0}>
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
                            sx={{
                                m: 1
                            }}
                            autoFocus
                            label='Enter list title...'
                            size='small'/>
                    )
                }


                <CardActions>
                    {
                        open && <>
                            <Button size='small' onClick={handleSubmit}>
                                Add
                            </Button>
                            <IconButton size='small' onClick={() => setOpen(false)}>
                                <Clear fontSize='small'/>
                            </IconButton>
                        </>
                    }
                    {
                        !open && (
                            <Button
                                sx={{
                                    flex: 1,
                                    justifyContent: 'flex-start',
                                    color: 'rgba(0, 0, 0, 0.54)',
                                    textTransform: 'none'
                                }}
                                size='small'
                                onClick={() => setOpen(true)}

                                startIcon={<Add/>}>
                                {'Add another list'}
                            </Button>
                        )
                    }
                </CardActions>
            </Card>


        </Box>
    )
};
export default AddNewList