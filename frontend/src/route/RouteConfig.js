import {useRoutes} from "react-router-dom";
import SignIn from "../view/signin/SignIn";
import {Home} from "../view/Home";
import PrivateRoute from "../component/PrivateRoute";
import {BoardContainer} from "../view/board/BoardContainer";
import MainContent from "../view/board/MainContent";
import Page404 from "../view/error/Page404";

const RouteConfig = () => useRoutes([
    {
        path: "/",
        element: <SignIn/>
    },
    {
        path: "/home",
        element: <PrivateRoute><Home/></PrivateRoute>
    },
    {
        path:'board',
        element: <><PrivateRoute/><BoardContainer/></>,
        children: [
            {
                path: "",
                element: <MainContent/>
            },
        ]
    },
    {
        path:'/error/404',
        element:<Page404/>

    }

]);
export default RouteConfig