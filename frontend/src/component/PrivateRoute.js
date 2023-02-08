import React from 'react'
import {Route} from 'react-router-dom'
// import { AuthContext } from './checkAuth'

const PrivateRoute = ({component: Component, ...otherProps}) => {

    // const isPermited = (roles) =>{

    //     if(roles === 'ADMIN' || roles === 'DEV'){
    //         return true
    //     }

    //     return false
    // }

    //const [Employeelogin, getEmployeeLogin] = useState(localStorage.getItem('userAuthData') || "");
    // const dataEmpl = useSelector(state => {
    //     return state.userInfo;
    // });

    // const { dataUser } = dataEmpl;
    // console.log(dataUser);

    return (
        <Route
            {...otherProps}
            render={props => (<Component {...props} />)}
        />
    )

}


export default PrivateRoute