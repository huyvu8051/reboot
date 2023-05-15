import {useState} from "react";
import {Button, Popover} from "@mui/material";
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
        <Button aria-describedby={id} variant="contained" onClick={handleClick}>
            Open Popover
        </Button>
        <Popover
            id={id}
            open={open}
            anchorEl={anchorEl}
            onClose={handleClose}
            anchorOrigin={{
                vertical: 'bottom',
                horizontal: 'left',
            }}
        >
            <Typography sx={{p: 2}}>The content of the Popover.</Typography>
        </Popover>
    </>
}

export default AddLabel