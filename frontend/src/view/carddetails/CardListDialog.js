import {useSelector} from "react-redux";
import {Autocomplete, Card, CardContent, CardHeader, IconButton, Popover, TextField} from "@mui/material";
import * as React from "react";
import {useEffect, useState} from "react";
import Typography from "@mui/material/Typography";
import {CloseRounded} from "@mui/icons-material";
import api from "../../service/api";

const CardListDialog = () => {
    const card = useSelector(sts => sts.dashboard.card)
    const [boards, setBoards] = useState([])
    useEffect(() => {
        api.get('/api/v1/user/dashboard/card/boards-details').then(setBoards)
    }, [setBoards])
    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    const id = open ? 'simple-popover' : undefined;


    return (
        <div>
            <Typography sx={{
                textDecoration: 'underline',
                cursor: 'pointer',
                fontSize: 14,
                fontWeight: 400,
                lineHeight: '20px'
            }} aria-describedby={id}
                        onClick={handleClick}>
                in list {card.list.title}
            </Typography>
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
                <Card>
                    <CardHeader title='Move card'
                                action={(<IconButton size='small'><CloseRounded fontSize='small'/></IconButton>)}/>


                    <CardContent>
                        <Autocomplete
                            id="grouped-demo"
                            options={boards}
                            groupBy={(option) => option.wNm}
                            getOptionLabel={(option) => option.name}
                            sx={{width: 300}}
                            renderInput={(params) => <TextField {...params} label="With categories"/>}
                        />
                    </CardContent>
                </Card>
            </Popover>

        </div>
    );
};

export default CardListDialog