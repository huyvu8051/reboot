import * as React from 'react'
import {styled} from '@mui/material/styles'
import Box from '@mui/material/Box'
import CssBaseline from '@mui/material/CssBaseline'
import MuiAppBar from '@mui/material/AppBar'
import Toolbar from '@mui/material/Toolbar'
import Typography from '@mui/material/Typography'
import IconButton from '@mui/material/IconButton'
import MenuIcon from '@mui/icons-material/Menu'
import {Outlet} from "@mui/icons-material";
import LeftDrawer from "./LeftDrawer";
import DrawerHeader from "./DrawerHeader";
import AddIcon from "@mui/icons-material/Add";
import {TextField} from "@mui/material";
import NewWorkspace from "./NewWorkspace";

const drawerWidth = 240

const Main = styled('main', {shouldForwardProp: (prop) => prop !== 'open'})(
    ({theme, open}) => ({
        flexGrow: 1,
        padding: theme.spacing(3),
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        marginLeft: `-${drawerWidth}px`,
        ...(open && {
            transition: theme.transitions.create('margin', {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
            marginLeft: 0,
        }),
    }),
)

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({theme, open}) => ({
    transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: `${drawerWidth}px`,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
    backgroundColor: 'white'
}))

export default function PersistentDrawerLeft(props) {
    const [open, setOpen] = React.useState(false)
    const handleToggle = () => {
        setOpen(!open)
    }

    return (
        <Box sx={{display: 'flex'}}>
            <CssBaseline/>
            <AppBar position='fixed' open={open}>
                <Toolbar>
                    <IconButton
                        aria-label='open drawer'
                        onClick={handleToggle}
                        edge='start'
                        sx={{mr: 2}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography variant='h6' noWrap component='div' color='black'>
                        Persistent drawer
                    </Typography>
                    <NewWorkspace/>

                </Toolbar>
            </AppBar>
            <LeftDrawer open={open}/>
            <Main open={open}>
                <DrawerHeader/>
                <Outlet/>
            </Main>
        </Box>
    )
}