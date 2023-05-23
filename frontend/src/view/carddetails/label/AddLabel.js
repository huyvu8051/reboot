import {useState} from "react";
import {Button, Checkbox, FormGroup, IconButton, Popover, TextField, Typography} from "@mui/material";
import {AddCircleOutline, Close, Edit} from "@mui/icons-material";
import FormControlLabel from "@mui/material/FormControlLabel";

const AddLabel = ({activator}) => {
    const [anchorEl, setAnchorEl] = useState()

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;
    const editLabelHandler = () => {
        // Handle the icon button click event here
    };
    return <>
        {activator({'aria-describedby': id, onClick: handleClick})}
        <Popover
            id={id}
            open={open}
            anchorEl={anchorEl}
            onClose={handleClose}
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
            }}
            sx={{
                '& .MuiPaper-root': {
                    pt: .5,
                    pb: 1,
                    px: 1
                },
            }}
        >
            <IconButton sx={{position: 'absolute', right: 0, top: 0}} size='small'><Close
                fontSize='small'/></IconButton>
            <p style={{fontSize: 13, fontWeight: '400', margin: 0}} align='center'>Labels</p>
            <TextField placeholder='Search labels...' fullWidth inputProps={{style: {padding: 5}}}/>
            <p>Labels</p>
            <FormGroup>
                <div style={{display: 'flex', alignItems: 'center', justifyItems: 'center', gap: 2}}>
                    <Checkbox defaultChecked size='small'/>
                    <Typography bgcolor='red' color='white' borderRadius={1} px={1} py={.5} width='100%' sx={{fontWeight: '500', fontSize: 14}}>Feature</Typography>
                    <IconButton size='small' style={{borderRadius: 5}}>
                        <Edit fontSize='small'/>
                    </IconButton>
                </div>

            </FormGroup>
        </Popover>
    </>
}

export default AddLabel