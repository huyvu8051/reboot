import * as React from 'react';
import {useEffect, useState} from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import AddIcon from "@mui/icons-material/Add";
import {Autocomplete, Avatar, Box, Chip, IconButton, Typography} from "@mui/material";
import Grid from "@mui/material/Grid";
import LinkIcon from "@mui/icons-material/Link";
import Link from "@mui/material/Link";
import {Close} from "@mui/icons-material";
import {$success} from "../../../util/snackbar-utils";
import api from "../../../service/api";

const AddMemberToWp = () => {
    const [open, setOpen] = React.useState(false);
    const [submitable, setSubmitable] = React.useState(false);
    const [emails, setEmails] = useState([])

    const [link, setLink] = useState('');

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const [searchQuery, setSearchQuery] = useState('');

    useEffect(() => {
        const timer = setTimeout(() => {
            if (searchQuery.trim() !== '') {
                api.get('/api/v1/user/workspace/members', {
                    params:{
                        keyword: searchQuery
                    }
                }).then(r=> {
                    console.log(r)
                    setEmails(r)
                })
            }
        }, 500);

        return () => {
            clearTimeout(timer);
        };
    }, [searchQuery]);


    function handleSearchChange(event) {
        setSearchQuery(event.target.value);
    }

    return (
        <div>
            <IconButton size='small' sx={{color: 'black'}} onClick={handleClickOpen}>
                <AddIcon fontSize='small'/>
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle sx={{pb: 0}} fontWeight='lighter'>
                    Invite to Workspace
                    <IconButton
                        aria-label="close"
                        sx={{
                            position: 'absolute',
                            right: 8,
                            top: 8,
                            color: (theme) => theme.palette.grey[500],
                        }}
                        onClick={handleClose}
                    >
                        <Close/>
                    </IconButton>
                </DialogTitle>
                <DialogContent>
                    <Grid container columnSpacing={2} alignItems='start'>
                        <Grid item flex={1}>
                            <Autocomplete
                                sx={{
                                    '& .MuiTextField-root': {
                                        margin: 0
                                    }
                                }}
                                clearOnEscape
                                onChange={(event, value) => setSubmitable(value.length > 0)}
                                multiple
                                size='small'
                                options={emails}
                                freeSolo
                                renderTags={(value, getTagProps) =>
                                    value.map((option, index) => (
                                        <Chip variant="outlined" label={option.username} {...getTagProps({index})} />
                                    ))
                                }
                                getOptionLabel={(option) => (option ? option.username : "")}

                                renderOption={(props, option) => (
                                    <Box display="flex" alignItems="center" paddingX={1} {...props}>
                                        <Avatar src={option.pictureUrl}/>
                                        <Box ml={2}>
                                            <Typography variant="subtitle1">{option.fullName}</Typography>
                                            <Typography variant="body2" color="textSecondary">{option.username}</Typography>
                                        </Box>
                                    </Box>
                                )}
                                renderInput={(params) => (
                                    <TextField
                                        margin='dense'
                                        autoFocus
                                        {...params}
                                        variant="outlined"
                                        placeholder="Email Address"
                                        type='email'
                                        onChange={handleSearchChange}
                                    />
                                )}
                            />
                        </Grid>
                        {
                            submitable && (
                                <Grid item>
                                    <Button sx={{textTransform: 'none', fontSize: 12}} variant='contained' size='small'>
                                        Send invites
                                    </Button>
                                </Grid>
                            )
                        }
                    </Grid>
                    {
                        submitable && (
                            <Grid container>
                                <TextField
                                    margin='dense'
                                    size='small'
                                    fullWidth
                                    minRows={5}
                                    multiline
                                    variant="outlined"
                                    placeholder="Join this Trello Workspace to start collaborating with me!"
                                />
                            </Grid>
                        )
                    }

                    <Grid container columnSpacing={2}>


                        <Grid item>
                            <IconButton sx={{borderRadius: 1}} size='small'>
                                <LinkIcon/>
                            </IconButton>
                        </Grid>
                        <Grid item>
                            <Typography fontSize={12}>
                                Invite someone to this Workspace with a link
                            </Typography>
                            {
                                link ? (
                                    <Link
                                        fontSize={12}
                                        href="#"
                                        onClick={e => {
                                            e.preventDefault()
                                            navigator.clipboard.writeText(link)
                                            $success("Link copied.")
                                        }}>
                                        Copy link
                                    </Link>
                                ) : (
                                    <Link
                                        fontSize={12}
                                        href="#"
                                        onClick={e => {
                                            e.preventDefault()
                                            setLink('https:youtube.com/')
                                        }}>
                                        Create link
                                    </Link>
                                )
                            }
                        </Grid>
                        {
                            link && (
                                <Grid item>
                                    <Button size='small'
                                            onClick={() => setLink('')}
                                            variant='contained'
                                            sx={{textTransform: 'none', fontSize: 12}}
                                    >Disable link</Button>
                                </Grid>
                            )
                        }
                    </Grid>


                </DialogContent>
            </Dialog>
        </div>
    )
        ;
}

export default AddMemberToWp