import {AvatarGroup, Button, Dialog, Grid, Typography} from "@mui/material";
import * as React from "react";
import {useCallback, useState} from "react";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

import bgImg from '../../asset/background/photo-1674413146454-41e62f015153.jpg'
import Avatar from "@mui/material/Avatar";
import {Add} from "@mui/icons-material";

const CardDetails = () => {
    const [open, setOpen] = useState(true);
    const navigate = useNavigate();
    const handleClickOpen = () => {
        setOpen(true);
    };

    const bId = useSelector(sts => sts.dashboard.board?.id || null);
    const card = useSelector(sts => sts.dashboard.card);

    const handleClose = useCallback(() => {
        setOpen(false);
        navigate(`/b/${bId}`)
    }, [bId])

    return card && (
        <div>
            <Button variant="outlined" onClick={handleClickOpen}>
                Open alert dialog
            </Button>
            <Dialog
                open={open}
                scroll='body'
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"

            >
                <Grid container flexDirection='column'>
                    <Grid item alignContent='center'>
                        <img style={{maxHeight: 160}} src={bgImg}/>
                    </Grid>
                    <Grid item alignContent='center'>

                        <Typography>
                            {card.title}
                        </Typography>
                    </Grid>
                    <Grid container item flexDirection='row'>
                        <Grid container item flexDirection='column'>
                            <AvatarGroup max={20} spacing={0}>
                                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg"/>
                                <Avatar alt="Travis Howard" src="/static/images/avatar/2.jpg"/>
                                <Avatar alt="Cindy Baker" src="/static/images/avatar/3.jpg"/>
                                <Avatar alt="Agnes Walker" src="/static/images/avatar/4.jpg"/>
                                <Avatar>
                                    <Add/>
                                </Avatar>
                            </AvatarGroup>
                        </Grid>
                        <Grid container item flexDirection='column'>

                        </Grid>
                    </Grid>
                </Grid>
            </Dialog>
        </div>
    );
}

export default CardDetails