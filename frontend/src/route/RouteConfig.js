import {createBrowserRouter} from 'react-router-dom'
import SignIn from '../view/signin/SignIn'
import {Home} from '../view/Home'
import PrivateRoute from '../component/PrivateRoute'
import Dashboard from '../view/workspace/Container'
import Boards from '../view/workspace/Boards'
import PageNotFound from '../view/error/PageNotFound'
import Unauthorized from '../view/error/Unauthorized'
import Members from '../view/members/Members'
import {BoardContents} from '../view/board-contents/BoardContents'

const RouteConfig = createBrowserRouter([
    {
        path: '',
        element: <SignIn/>
    },
    {
        path: 'home',
        element: <PrivateRoute><Home/></PrivateRoute>
    },
    {
        element: <><PrivateRoute/><Dashboard/></>,
        children: [
            {
                path: 'w',
                element: <Boards/>
            },
            {
                path: 'w/:wpId',
                element: <Boards/>
            },
            {
                path: 'w/:wpId/members',
                element: <Members/>
            },
            {
                path: 'b/:bdId',
                element: <BoardContents/>
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