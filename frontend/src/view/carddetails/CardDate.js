import {Button, Checkbox} from "@mui/material";
import {KeyboardArrowDown} from "@mui/icons-material";
import * as React from "react";
import {useSelector} from "react-redux";

const CardDate = () => {
    const card = useSelector(sts => sts.dashboard.card)

    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>
                Dates
            </h5>
            <div>
                <Checkbox size='small' sx={{p: 0}}/>
                <Button
                    size='small'
                    disableElevation
                    variant='contained'
                    color='inherit'
                    style={{
                        borderRadius: 3,
                        margin: 2,
                        backgroundColor: 'rgba(9, 30, 66, 0.04)',
                        textTransform: 'none'
                    }}

                    endIcon={<KeyboardArrowDown/>}>
                    <span style={{padding: 2, margin: 2}}>May 8 - May 9 at 4:34 PM</span> <span
                    style={{backgroundColor: 'yellow', padding: 2, margin: 2}}>due soon</span>
                </Button>
            </div>

        </div>
    )
}

export default CardDate