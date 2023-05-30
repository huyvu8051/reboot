import {Button, List} from '@mui/material'
import * as React from 'react'
import {useSelector} from 'react-redux'

import img from '../../asset/image/paella.jpg'

const formatDatetime = (dateTimeString) => {
    const dateTime = new Date(dateTimeString);

    const options = {
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        hour12: true
    };

    const formattedDateTime = dateTime.toLocaleString('en-US', options);
    return formattedDateTime

}
const actionButton = {
    style: {
        backgroundColor: 'rgba(9, 30, 66, 0.04)',
        color: 'inherit',
        textTransform: 'none',
        justifyContent: 'flex-start'
    },
    size: 'small',
    disableElevation: true,
    variant: 'contained'
}

function previewable(type) {
    if (['png', 'jpg', 'jpeg'].includes(type)) return true
    return false;
}

const CardAttachments = () => {
    const attachments = useSelector(sts => sts.dashboard.attachments)
    const bId = useSelector(sts => sts.dashboard.board?.id || null)

    return (
        <div>
            <h5 style={{padding: 0, margin: 0}}>
                Attachments
            </h5>
            <List>
                {
                    attachments.items.map((e, i) => {
                            let buttonStyle = {
                                textDecoration: 'underline',
                                margin: 3,
                            };
                            return (
                                <div key={i} style={{display: 'flex', flexDirection: 'row', gap: 5}}>
                                    <div style={{flex: 1, marginBottom: 2}}>
                                        {
                                            previewable(e.type) ? <>
                                                <img src={`/api/v1/resources/board/${bId}/${e.fileNm}`} alt='dick'
                                                     style={{height: 80, width: 112, borderRadius: 3}}/>
                                            </> : <div
                                                style={{
                                                    display: 'flex',
                                                    justifyContent: 'center',
                                                    alignItems: 'center',
                                                    height: 80,
                                                    width: 112,
                                                    borderRadius: 3,
                                                    textAlign: 'center',
                                                    backgroundColor: 'rgba(168,201,215,0.62)'
                                                }}>
                                                <p style={{fontWeight: 'bold'}}>{e.type}</p>
                                            </div>
                                        }
                                    </div>
                                    <div style={{flex: 5, display: 'block', margin: 0}}>
                                        <div style={{
                                            fontWeight: 'bold',
                                            fontSize: 14
                                        }}>
                                            {e.name}
                                        </div>
                                        <div style={{
                                            fontSize: 14,
                                        }}>
                                            <span>Added {formatDatetime(e.createdDate)}</span>
                                            <span style={buttonStyle}><a>Comment</a></span>
                                            <span style={buttonStyle}><a>Remove</a></span>
                                            <span style={buttonStyle}><a>Edit</a></span>
                                        </div>
                                    </div>
                                </div>
                            );
                        }
                    )
                }
            </List>

            <Button {...actionButton}>
                View all attachments ({attachments.totalCount - attachments.items.length} hidden)
            </Button>
            <br/>
            <Button {...actionButton}>
                Add an attachment
            </Button>

        </div>
    )
}

export default CardAttachments
