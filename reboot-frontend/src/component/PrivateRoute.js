import {useSelector} from 'react-redux'
import {useNavigate} from 'react-router-dom'

const PrivateRoute = () => {
    const username = useSelector(state => state.authentication.username)
    const navigate = useNavigate()
    if(username === ''){
        navigate('/error/401')
    }
    return null
}
export default PrivateRoute