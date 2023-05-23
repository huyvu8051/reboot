import {useState} from "react";
import {Checkbox, IconButton, Popover, TextField, Typography} from "@mui/material";
import {Close, Edit} from "@mui/icons-material";
import {useDispatch, useSelector} from "react-redux";
import {updateCardLabel} from "../../workspace/dashboard-slice";

const AddLabel = ({activator}) => {
    const dispatch = useDispatch()
    const [anchorEl, setAnchorEl] = useState()
    const boardLabels = useSelector(sts => sts.dashboard.boardLabels)
    const cardLabels = useSelector(sts => sts.dashboard.cardLabels)
    // console.log(boardLabels, cardLabels)

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;


    function handleChecked(data) {
        dispatch(updateCardLabel(data))
        console.log(data)


    }

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
            <IconButton sx={{position: 'absolute', right: 0, top: 0, borderRadius: 1, padding: 0, margin: .5}}
                        size='small'><Close
                fontSize='small'/></IconButton>
            <p style={{fontSize: 13, fontWeight: '400', margin: 0}} align='center'>Labels</p>
            <TextField placeholder='Search labels...' fullWidth inputProps={{style: {padding: 5}}}/>
            <p>Labels</p>
            {
                boardLabels.map(e => {
                    const find = cardLabels.find(item => item.labelId === e.id);

                    const checked = !(find?.isDeleted ?? true);
                    return (
                        <div key={e.id}
                             style={{
                                 display: 'flex',
                                 alignItems: 'center',
                                 justifyItems: 'center',
                                 gap: 2,
                                 padding: .5
                             }}>
                            <Checkbox
                                checked={checked}
                                onClick={(event) => {
                                    handleChecked({
                                        id: find?.id ?? null,
                                        isDeleted: !event.target.checked,
                                        labelId: e.id
                                    })
                                }} size='medium' sx={{padding: 0, margin: .5}}/>
                            <Typography bgcolor='green' color='white' borderRadius={1} px={1.5} py={.5} width='100%'
                                        sx={{fontWeight: 500, fontSize: 12, cursor: 'pointer'}}>{e.title}</Typography>
                            <IconButton size='small' style={{borderRadius: 5, padding: 0, margin: 1}}>
                                <Edit fontSize='small'/>
                            </IconButton>
                        </div>);
                })
            }
        </Popover>
    </>
}

export default AddLabel