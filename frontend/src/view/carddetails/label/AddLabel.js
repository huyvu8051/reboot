import {useState} from "react";
import {Checkbox, FormGroup, IconButton, Popover, TextField} from "@mui/material";
import {AddCircleOutline, Close} from "@mui/icons-material";
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
                <FormControlLabel sx={{
                    '& .MuiTypography-body1': {
                        width: '100%'
                    },
                    margin: 0
                }} control={<Checkbox defaultChecked/>}
                                  label={<div style={{display: 'flex', width: '100%'}}>
                                      <p style={{width: '100%'}}>test</p>
                                      <IconButton
                                          disabled
                                          size="small"
                                          onClick={editLabelHandler}
                                      >
                                          <AddCircleOutline/>
                                      </IconButton>
                                  </div>}/>
            </FormGroup>
        </Popover>
    </>
}

export default AddLabel