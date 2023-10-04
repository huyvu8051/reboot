import {Close} from '@mui/icons-material'
import AddIcon from '@mui/icons-material/Add'
import {Card, CardActions, CardContent, CardHeader, Popover, TextField} from '@mui/material'
import Button from '@mui/material/Button'
import Divider from '@mui/material/Divider'
import IconButton from '@mui/material/IconButton'
import Typography from '@mui/material/Typography'
import * as React from 'react'
import {useRef} from 'react'
import api from '../../service/api'
import {$success} from '../../util/snackbar-utils'

const CreateBoardBtn = () => {
    const [anchorEl, setAnchorEl] = React.useState(null)

    const titleRef = useRef()

    const handlePopoverClose = () => {
        setAnchorEl(null)
    }

    const open = Boolean(anchorEl)

    const handleSettingMenu = (event) => {
        setAnchorEl(event.currentTarget)
    }

    // const navigate = useNavigate()


    function handleCreateClick() {
        const title = titleRef.current.value

        api.post('/api/v1/message/conv/save', {nm: title})
            .then(resp => {
                $success(`Create conversation \`${title}\` success`)

                /*api.post('/api/v1/message/conv/list', {nm: title})
                    .then(resp => {
                        console.log(resp)
                    })*/
                handlePopoverClose()
            })
    }

    const handleKeyDown = (event) => {
        if (event.keyCode === 13) {
            handleCreateClick()
        }
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
                                Create conversation
                            </Typography>
                        }
                    />

                    <Divider/>
                    <CardContent sx={{p: 0, pt: 2}}>
                        <TextField
                            fullWidth
                            size="small"
                            label="Conversation name"
                            sx={{mb: 1}}
                            InputLabelProps={{shrink: true}}
                            inputRef={titleRef}
                            onKeyDown={handleKeyDown}
                        />
                    </CardContent>
                    <CardActions sx={{p: 0, pt: 2}}>
                        <Button size="small" variant="contained" onClick={handleCreateClick}>Create</Button>
                    </CardActions>
                </Card>
            </Popover>
        </>
    )
}
export default CreateBoardBtn