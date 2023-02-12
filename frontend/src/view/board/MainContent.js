import {Button} from "@mui/material";
import {useNavigate} from "react-router-dom";

const MainContent = () => {

    const navigate = useNavigate();
    return <>
        main content

        <Button onClick={()=>navigate('/error/404')}>ERR</Button>
    </>
}
export default MainContent