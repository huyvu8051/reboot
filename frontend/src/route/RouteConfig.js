import {createBrowserRouter} from 'react-router-dom'
import SignIn from '../view/signin/SignIn'
import {Home} from '../view/Home'
import PrivateRoute from '../component/PrivateRoute'
import Container from '../view/workspace/Container'
import PageNotFound from '../view/error/PageNotFound'
import Unauthorized from '../view/error/Unauthorized'
import Members from '../view/members/Members'
import {Board} from '../view/board/Board'
import Workspace from "../view/workspace/Workspace";
import CardDetails from "../view/card/CardDetails";

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
        element: <><PrivateRoute/><Container/></>,
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
            {
                path: 'c/:cId',
                element: <Workspace><Board/><CardDetails/></Workspace>
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