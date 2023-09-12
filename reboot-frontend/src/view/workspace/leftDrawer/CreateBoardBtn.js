import {Close} from '@mui/icons-material'
import AddIcon from '@mui/icons-material/Add'
import {Card, CardActions, CardContent, CardHeader, CardMedia, Popover, TextField} from '@mui/material'
import Button from '@mui/material/Button'
import Divider from '@mui/material/Divider'
import IconButton from '@mui/material/IconButton'
import Typography from '@mui/material/Typography'
import * as React from 'react'
import {useRef} from 'react'
import {useNavigate} from 'react-router-dom'

import paella from '../../../asset/image/paella.jpg'
import api from '../../../service/api'
import {$dispatch} from '../../../util/eventbus-utils'
import {$success} from '../../../util/snackbar-utils'
import NewBoardVisibilitySelect from './CreateBoardVisibilitySelect'
import NewBoardWpSelect from './CreateBoardWpSelect'

const CreateBoardBtn = () => {
    const [anchorEl, setAnchorEl] = React.useState(null)

    const titleRef = useRef()
    const wpRef = useRef()
    const visibilityRef = useRef()

    const handlePopoverClose = () => {
        setAnchorEl(null)
    }

    const open = Boolean(anchorEl)

    const handleSettingMenu = (event) => {
        setAnchorEl(event.currentTarget)
    }

    const navigate = useNavigate()


    function handleCreateClick() {
        //console.log(title, wpRef.current.value, visibilityRef.current.value)
        const title = titleRef.current.value
        const wp = wpRef.current.value
        const visibility = visibilityRef.current.value

        api.put('/api/v1/user/board', {
            title,
            wp,
            visibility
        })
            .then(resp => {
                $success(`Create board \`${title}\` success`)
                $dispatch('boards.refresh', wp)
                handlePopoverClose()
                navigate(`/b/${resp}`)
            })
    }

    return (
        <>
            <IconButton size="small" sx={{color: 'black'}}
                        aria-controls="menu-setting"
                        aria-haspopup="true"
                        onClick={handleSettingMenu}>
                <AddIcon fontSize="small"/>
            </IconButton>
            <Popover
                id="mouse-over-popover"
                open={open}
                anchorEl={anchorEl}
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'right'
                }}
                keepMounted
                transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left'
                }}
                onClose={handlePopoverClose}
                disableRestoreFocus
                PaperProps={{elevation: 0}}
            >
                <Card sx={{maxWidth: 345, p: 1}} variant="outlined">
                    <CardHeader

                        sx={{p: 0, mb: 1}}
                        action={
                            <IconButton aria-label="close" onClick={handlePopoverClose}>
                                <Close/>
                            </IconButton>
                        }
                        title={
                            <Typography variant="h5" sx={{fontSize: '14px', textAlign: 'center'}}>
                                Create board
                            </Typography>
                        }
                    />

                    <Divider/>
                    <CardMedia
                        component="img"
                        height="194"
                        image={paella}
                        alt="Paella dish"
                    />
                    <CardContent sx={{p: 0, pt: 2}}>
                        <TextField
                            fullWidth
                            size="small"
                            label="Board title"
                            sx={{mb: 1}}
                            InputLabelProps={{shrink: true}}
                            inputRef={titleRef}
                        />
                        <NewBoardWpSelect childRef={wpRef}/>
                        <NewBoardVisibilitySelect childRef={visibilityRef}/>
                    </CardContent>
                    <CardActions sx={{p: 0, pt: 2}}>
                        <Button size="small" variant="contained" onClick={handleCreateClick}>Create</Button>
                        <Button size="small" variant="contained">Start with a template</Button>
                    </CardActions>
                </Card>
            </Popover>
        </>
    )
}
export default CreateBoardBtn