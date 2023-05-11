import IconButton from "@mui/material/IconButton";
import {Close, PhotoCamera} from "@mui/icons-material";
import * as React from "react";
import api from "../../service/api";
import {useDispatch, useSelector} from "react-redux";
import {updateCard} from "../workspace/dashboard-slice";
import DialogTitle from "@mui/material/DialogTitle";

const CardHeaderCover = ({handleClose}) => {
    const dispatch = useDispatch()
    const cId = useSelector(sts => sts.dashboard.card?.id || null)

    const bId = useSelector(sts => sts.dashboard.board?.id)
    const card = useSelector(sts => sts.dashboard.card || {})

    const handleFileChange = e => {

        const file = e.target.files[0];
        if (!file) return
        const formData = new FormData();
        formData.append("file", file);

        api.post("/api/v1/user/dashboard/card/attachment/" + cId, formData).then(r => {
            console.log(r)
            api.put('/api/v1/user/dashboard/card/details', {
                id: card.id,
                coverUrl: r
            }).then()

            dispatch(updateCard({
                ...card,
                coverUrl: r
            }))
        })

    }

    return <>
        {card.coverUrl && (
            <DialogTitle sx={{p: 0, position: 'relative'}}>
                <img
                    style={{
                        width: '100%',
                        maxHeight: 200,
                        objectFit: 'contain',
                    }}
                    src={`/api/v1/resources/board/${bId}/${card.coverUrl}`}
                    alt={'background'}>

                </img>
                <IconButton sx={{position: 'absolute', bottom: 5, right: 5}} color="primary" aria-label="upload picture"
                            component="label">
                    <input hidden accept="image/*" type="file" onChange={handleFileChange}/>
                    <PhotoCamera style={{color: 'gray'}}/>
                </IconButton>
            </DialogTitle>
        )}
        <IconButton onClick={handleClose} sx={{position: 'absolute', top: 5, right: 5}}>
            <Close/>
        </IconButton>
    </>;
};

export default CardHeaderCover