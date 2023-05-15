import {useState} from "react";
import {Popover, TextField} from "@mui/material";
import Typography from "@mui/material/Typography";

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
                    p: 1
                }
            }}
        >
            <TextField placeholder='Search labels...' fullWidth inputProps={{style: {padding: 5}}}/>
        </Popover>
    </>
}

export default AddLabel