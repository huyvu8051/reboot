import {useState} from "react";
import {Button, Checkbox, Icon, IconButton, Popover, TextField} from "@mui/material";
import Typography from "@mui/material/Typography";
import {Close, Edit} from "@mui/icons-material";

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
            <IconButton sx={{position: 'absolute', right: 0, top: 0}} size='small'><Close fontSize='small'/></IconButton>
            <p style={{fontSize: 13,fontWeight: '400', margin: 0}} align='center'>Labels</p>
            <TextField placeholder='Search labels...' fullWidth inputProps={{style: {padding: 5}}}/>
            <p>Labels</p>
            <ul>
                <li><Checkbox/></li>
                <li><Button>Fearture</Button></li>
                <li><IconButton><Edit/></IconButton></li>
            </ul>
        </Popover>
    </>
}

export default AddLabel