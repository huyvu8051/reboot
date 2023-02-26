import {Button} from '@mui/material'
import {useNavigate, useParams} from 'react-router-dom'
import {useEffect} from "react";
import api from "../../service/api";
import {useDispatch} from "react-redux";

const Boards = (props) => {
    const {bId} = useParams();

    const dispatch = useDispatch();

    const navigate = useNavigate()
    useEffect(() => {
        api.get('/api/v1/user/board', {
            params: {bId}
        }).then(r => {
            dispatch(r)
        })
    }, [bId])
    return <>
        main content

        <Button onClick={() => navigate('/error/404')}>ERR</Button>
    </>
}
export default Boards