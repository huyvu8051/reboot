import { useRoutes } from "react-router-dom";
import SignIn from "../view/SignIn";
import {Home} from "../view/Home";
import PrivateRoute from "../component/PrivateRoute";

export default function MyRoute() {
    const routes = useRoutes([
        {
            path: "/",
            element: <SignIn/>
        },
        {
            path: "/home",
            element: <PrivateRoute><Home/></PrivateRoute>
        }
    ]);
    return routes;
}