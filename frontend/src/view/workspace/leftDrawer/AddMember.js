import * as React from 'react';
import {useState} from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import AddIcon from "@mui/icons-material/Add";
import {Autocomplete, Chip, IconButton, Typography} from "@mui/material";
import Grid from "@mui/material/Grid";
import LinkIcon from "@mui/icons-material/Link";
import Link from "@mui/material/Link";
import {Close} from "@mui/icons-material";

const AddMember = () => {
    const [open, setOpen] = React.useState(false);
    const [emails, setEmails] = useState([])

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

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
                    <Autocomplete
                        multiple
                        size='small'
                        options={emails.map((option) => option.title)}
                        freeSolo
                        renderTags={(value, getTagProps) =>
                            value.map((option, index) => (
                                <Chip variant="outlined" label={option} {...getTagProps({index})} />
                            ))
                        }
                        renderInput={(params) => (
                            <TextField
                                margin='dense'
                                autoFocus
                                {...params}
                                variant="outlined"
                                placeholder="Email Address"
                                type='email'
                            />
                        )}
                    />
                    <Grid container columnSpacing={2} alignItems='center'>
                        <Grid item>
                            <IconButton sx={{borderRadius: 1}} size='small'>
                                <LinkIcon/>
                            </IconButton>
                        </Grid>
                        <Grid item>
                            <Typography fontSize={12}>
                                Invite someone to this Workspace with a link
                            </Typography>
                            <Link fontSize={12} href="#" onClick={e => e.preventDefault()}>Copy link</Link>
                        </Grid>
                        <Grid item>
                            <Button size='small'

                                    variant='contained'
                                    sx={{textTransform: 'none', fontSize: 12}}
                                    >Disable link</Button>
                        </Grid>
                    </Grid>


                </DialogContent>
            </Dialog>
        </div>
    );
}

export default AddMember