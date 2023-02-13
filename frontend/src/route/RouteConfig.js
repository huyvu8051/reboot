import {createBrowserRouter, useRoutes} from "react-router-dom";
import SignIn from "../view/signin/SignIn";
import {Home} from "../view/Home";
import PrivateRoute from "../component/PrivateRoute";
import Dashboard from "../view/board/BoardContainer";
import Boards from "../view/board/Boards";
import PageNotFound from "../view/error/PageNotFound";
import Unauthorized from "../view/error/Unauthorized";
import Members from "../view/members/Members";

const RouteConfig = createBrowserRouter([
    {
        path: "/",
        element: <SignIn/>
    },
    {
        path: "/home",
        element: <PrivateRoute><Home/></PrivateRoute>
    },
    {
        path: 'board/:wpCd',
        element: <><PrivateRoute/><Dashboard/></>,
        children: [
            {
                path: "",
                element: <Boards/>
            },
            {
                path: "members",
                element: <Members/>
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

]);
export default RouteConfig