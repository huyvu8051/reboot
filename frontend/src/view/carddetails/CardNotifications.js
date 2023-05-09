import {Done, VisibilityOutlined} from "@mui/icons-material";
import * as React from "react";
import {useSelector} from "react-redux";
import {Button} from "@mui/material";

const CardNotifications = () => {
    const card = useSelector(sts => sts.dashboard.card)

    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>
                Notifications
            </h5>
            <Button
                disableElevation
                size='small'
                variant='contained'
                color='inherit'
                sx={{borderRadius: 1}}
                style={{borderRadius: 3, margin: 2, backgroundColor: 'rgba(9, 30, 66, 0.04)', textTransform: 'none'}}
                startIcon={<VisibilityOutlined fontSize='small'/>}
                endIcon={card.notification && <Done/>}>
                {card.notification ? 'Watching' : 'Watch'}
            </Button>
        </div>
    )
}

export default CardNotifications