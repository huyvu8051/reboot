import {List} from "@mui/material";
import * as React from "react";
import {useSelector} from "react-redux";
import ListItem from "@mui/material/ListItem";

const CardActivity = () => {
    const card = useSelector(sts => sts.dashboard.card)
    // console.log(card)
    return <>
        <h5 style={{padding: 0, margin: 0}}>
            Activity
        </h5>
        <List>
            <ListItem>
                att
            </ListItem>
            <ListItem>
                att
            </ListItem>
            <ListItem>
                att
            </ListItem>
            <ListItem>
                att
            </ListItem>
            <ListItem>
                att
            </ListItem>

        </List>
    </>
}

export default CardActivity