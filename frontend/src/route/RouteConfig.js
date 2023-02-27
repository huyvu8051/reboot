import {createBrowserRouter} from 'react-router-dom'
import SignIn from '../view/signin/SignIn'
import {Home} from '../view/Home'
import PrivateRoute from '../component/PrivateRoute'
import Dashboard from '../view/workspace/Container'
import Boards from '../view/workspace/Boards'
import PageNotFound from '../view/error/PageNotFound'
import Unauthorized from '../view/error/Unauthorized'
import Members from '../view/members/Members'
import {Board} from '../view/board/Board'
import Workspace from "../view/workspace/Workspace";

const RouteConfig = createBrowserRouter([
    {
        path: '',
        element: <SignIn/>
    },
    {
        path: 'home',
        element: <><PrivateRoute/><Home/></>
    },
    {
        element: <><PrivateRoute/><Dashboard/></>,
        children: [
            {
                path: 'w',
                element: null
            },
            {
                path: 'w/:wId',
                element: <Workspace/>
            },
            {
                path: 'w/:wId/members',
                element: <Members/>
            },
            {
                path: 'b/:bId',
                element: <Workspace><Board/></Workspace>
            },
        ]
    },
    {
        path: 'error',
        children: [
            {
                path: '404',
                element: <PageNotFound/>
            },
            {
                path: '401',
                element: <Unauthorized/>
            }
        ]

    }

])
export default RouteConfig