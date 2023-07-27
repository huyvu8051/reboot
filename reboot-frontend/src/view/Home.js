import {Button} from '@mui/material'
import {$success} from '../util/snackbar-utils'

export const Home = () => {
    return <>
        <h1>Home</h1>
        <Button onClick={()=>$success('Gud job boy')}>Toast</Button>
    </>
}