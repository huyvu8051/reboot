import {useSelector} from "react-redux";
import {AvatarGroup} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import {Add} from "@mui/icons-material";
import * as React from "react";

const avatarStyle = {width: '26px', height: '26px'}
const CardMembers = () => {
    const cardMems = useSelector(sts => sts.dashboard.cardMems)

    function handleAddNewMember() {

    }

    return (

        <div style={{display: 'inline'}}>
            <h5 style={{
                padding: 0,
                margin: 0,
                fontSize: 12,
                fontWeight: 600,
                lineHeight: '20px'
            }}> Members</h5>
            <AvatarGroup spacing={1} sx={{flexDirection: 'row'}}>
                <Avatar onClick={handleAddNewMember} style={avatarStyle}><Add/></Avatar>
                {
                    cardMems.map(e => (
                        <Avatar key={e.id} alt={e.fullName} src={e.pictureUrl} style={avatarStyle}/>
                    ))
                }
            </AvatarGroup>
        </div>
    )
};

export default CardMembers