import {createBrowserRouter} from 'react-router-dom'
import PrivateRoute from '../component/PrivateRoute'
import {Board} from '../view/board/Board'
import CardDetails from '../view/carddetails/CardDetails'
import PageNotFound from '../view/error/PageNotFound'
import Unauthorized from '../view/error/Unauthorized'
import {Home} from '../view/Home'
import Members from '../view/members/Members'
import Content from '../view/message/Content'
import Message from '../view/message/Message'
import SignIn from '../view/signin/SignIn'
import Container from '../view/workspace/Container'
import Workspace from '../view/workspace/Workspace'

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
                path: 'message',
                element: <Message></Message>
            },
            {
                path: 'message/:convId',
                element: <Message><Content></Content></Message>
            }
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