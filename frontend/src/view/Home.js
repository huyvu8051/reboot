import {Button} from "@mui/material";
import SnackbarUtils from "../service/SnackbarUtils";

export const Home = () => {
    return <>
        <h1>Home</h1>
        <Button onClick={()=>SnackbarUtils.success('Gud job boy')}>Toast</Button>
    </>
}