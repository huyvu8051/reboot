import {Close} from '@mui/icons-material'
import AddIcon from '@mui/icons-material/Add'
import LinkIcon from '@mui/icons-material/Link'
import {Avatar, Box, Chip, IconButton, Typography} from '@mui/material'
import Button from '@mui/material/Button'
import Dialog from '@mui/material/Dialog'
import DialogContent from '@mui/material/DialogContent'
import DialogTitle from '@mui/material/DialogTitle'
import Grid from '@mui/material/Grid'
import Link from '@mui/material/Link'
import TextField from '@mui/material/TextField'
import * as React from 'react'
import {useEffect, useRef, useState} from 'react'
import api from '../../../service/api'
import {$success} from '../../../util/snackbar-utils'

const AddMemberToWp = () => {
    const [open, setOpen] = React.useState(false)
    const [emails, setEmails] = useState([])
    const [selecteds, setSelecteds] = useState([])

    const [link, setLink] = useState('')

    const handleClickOpen = () => {
        setOpen(true)
    }

    const handleClose = () => {
        setOpen(false)
    }

    const [searchQuery, setSearchQuery] = useState('')

    useEffect(() => {
        const timer = setTimeout(() => {
            if (searchQuery.trim() !== '') {
                api.get('/api/v1/user/workspace/members', {
                    params: {
                        keyword: searchQuery
                    }
                }).then(r => {
                    console.log(r)
                    setEmails(r)
                })
            } else {
                setEmails([])
            }
        }, 500)

        return () => {
            clearTimeout(timer)
        }
    }, [searchQuery])


    function handleSearchChange(event) {
        setSearchQuery(event.target.value)
    }

    const handleOptionSelected = () => {
        return true // Always return true to force the option popup to show
    }


    function sendInvite() {
        api.post('/api/v1/user/workspace/members', {
            mems: [{
                email: 'chungta@gmail.com'
            }],
            msg: msgRef.current.value
        }).then(r => {
            $success('Invite send ✅')
        })
        setSelecteds([])
        setEmails([])
        setOpen(false)
        setLink('')

    }

    function handleChipDelete(option) {
        setSelecteds((prevArray) =>
            prevArray.filter((element) => element.username !== option.username)
        )
    }

    const validateEmail = (email) => {
        // Regular expression pattern for email validation
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

        // Check if the email matches the regex pattern
        return emailRegex.test(email)
    }

    const handleKeyPress = (event) => {
        if (event.key === 'Enter') {
            const value = event.target.value.trim()
            if (validateEmail(value)) {
                setSelecteds([{username: value, id: new Date()}, ...selecteds])
                event.target.value = ''
                setSearchQuery('')
            }
        }
    }

    const msgRef = useRef()

    return (
        <div>
            <IconButton size="small" sx={{color: 'black'}} onClick={handleClickOpen}>
                <AddIcon fontSize="small"/>
            </IconButton>
            <Dialog open={open} onClose={handleClose}>
                <DialogTitle sx={{pb: 0}} fontWeight="lighter">
                    Invite to Workspace
                    <IconButton
                        aria-label="close"
                        sx={{
                            position: 'absolute',
                            right: 8,
                            top: 8,
                            color: (theme) => theme.palette.grey[500]
                        }}
                        onClick={handleClose}
                    >
                        <Close/>
                    </IconButton>
                </DialogTitle>
                <DialogContent>
                    <Grid container columnSpacing={2} alignItems="start">
                        <Grid item flex={1}>
                            {
                                selecteds.map((option) => (
                                    <Chip
                                        key={option.id}
                                        label={option.username}
                                        onDelete={() => handleChipDelete(option)}
                                        style={{marginRight: 5}}
                                    />
                                ))}
                            <TextField
                                margin="dense"
                                autoFocus
                                variant="outlined"
                                placeholder="Email Address"
                                type="email"
                                onChange={handleSearchChange}
                                onKeyPress={handleKeyPress}
                            />

                            {
                                emails.filter((element) => !selecteds.includes(element)).map(option => (
                                    <Box key={option.id} sx={{
                                        margin: 1,
                                        cursor: 'pointer'
                                    }} display="flex" alignItems="center" paddingX={1}
                                         onClick={() => {

                                             setSelecteds([option, ...selecteds])
                                         }}>
                                        <Avatar src={option.pictureUrl}/>
                                        <Box ml={2}>
                                            <Typography variant="subtitle1">{option.fullName}</Typography>
                                            <Typography variant="body2"
                                                        color="textSecondary">{option.username}</Typography>
                                        </Box>
                                    </Box>
                                ))
                            }
                        </Grid>
                        {
                            selecteds.length > 0 && (
                                <Grid item>
                                    <Button sx={{textTransform: 'none', fontSize: 12}} variant="contained" size="small"
                                            onClick={sendInvite}>
                                        Send invites
                                    </Button>
                                </Grid>
                            )
                        }
                    </Grid>
                    {
                        selecteds.length > 0 && (
                            <Grid container>
                                <TextField
                                    inputRef={msgRef}
                                    margin="dense"
                                    size="small"
                                    fullWidth
                                    minRows={5}
                                    multiline
                                    variant="outlined"
                                    placeholder="Join this Workspace to start collaborating with me!"
                                />
                            </Grid>
                        )
                    }

                    <Grid container columnSpacing={2}>


                        <Grid item>
                            <IconButton sx={{borderRadius: 1}} size="small">
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
                                            $success('Link copied.')
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
                                    <Button size="small"
                                            onClick={() => setLink('')}
                                            variant="contained"
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

}

export default AddMemberToWp