import * as React from 'react';
import {styled} from '@mui/material/styles';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import {Avatar, Link, ListItemAvatar} from "@mui/material";
import DashboardIcon from '@mui/icons-material/Dashboard';
import PermIdentityIcon from '@mui/icons-material/PermIdentity';
import SettingsIcon from '@mui/icons-material/Settings';
import TableRowsIcon from '@mui/icons-material/TableRows';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import AddIcon from '@mui/icons-material/Add';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import {createSearchParams, useNavigate, useParams} from "react-router-dom";

const drawerWidth = 240;

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
);

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
}));

const DrawerHeader = styled('div')(({theme}) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));

export default function PersistentDrawerLeft(props) {
    const [open, setOpen] = React.useState(false);
    const handleToggle = () => {
        setOpen(!open);
    };

    const navigate = useNavigate();

    return (
        <Box sx={{display: 'flex'}}>
            <CssBaseline/>
            <AppBar position="fixed" open={open}>
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleToggle}
                        edge="start"
                        sx={{mr: 2}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography variant="h6" noWrap component="div">
                        Persistent drawer
                    </Typography>
                </Toolbar>
            </AppBar>
            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },
                }}
                variant="persistent"
                anchor="left"
                open={open}
            >
                <DrawerHeader>
                    <Avatar variant="rounded" alt="Remy Sharp" src="https://mui.com/static/images/avatar/1.jpg"/>
                    <Link sx={{ml: 2, textDecoration: 'none'}} href="/">viva_odc</Link>
                </DrawerHeader>
                <Divider/>
                <List dense>
                    <ListItem key="board" disablePadding>
                        <ListItemButton onClick={() => navigate("/board/" + props.match.params.wpId)}>
                            <ListItemIcon>
                                <DashboardIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Board"/>
                        </ListItemButton>
                    </ListItem>
                    <ListItem key="members" disablePadding>
                        <ListItemButton>
                            <ListItemIcon>
                                <PermIdentityIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Members"/>
                        </ListItemButton>
                        <IconButton sx={{color: 'black'}}>
                            <AddIcon/>
                        </IconButton>
                    </ListItem>
                    <ListItem key="settings" disablePadding>
                        <ListItemButton>
                            <ListItemIcon>
                                <SettingsIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Settings"/>

                        </ListItemButton>
                        <IconButton sx={{color: 'black'}}>
                            <ExpandMoreIcon/>
                        </IconButton>
                    </ListItem>

                </List>

                <Divider/>
                <List dense>
                    <ListItem key="wp-views" disablePadding>
                        <ListItemButton>
                            <ListItemText primary="Workspace views"/>

                        </ListItemButton>
                        <IconButton sx={{color: 'black'}}>
                            <AddIcon/>
                        </IconButton> </ListItem>
                    <ListItem key="table" disablePadding>
                        <ListItemButton>
                            <ListItemIcon>
                                <TableRowsIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Table"/>
                        </ListItemButton>
                    </ListItem>
                    <ListItem key="calendar" disablePadding>
                        <ListItemButton>
                            <ListItemIcon>
                                <CalendarMonthIcon/>
                            </ListItemIcon>
                            <ListItemText primary="Calendar"/>
                        </ListItemButton>
                    </ListItem>
                </List>

                <Divider/>
                <List dense>
                    <ListItem key="your-boards" disablePadding>
                        <ListItemButton>
                            <ListItemText primary="Your boards"/>

                        </ListItemButton>
                        <IconButton sx={{color: 'black'}}>
                            <AddIcon/>
                        </IconButton>
                    </ListItem>
                    <ListItem key="board" disablePadding>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar variant="rounded" alt="Remy Sharp"
                                        src="https://mui.com/static/images/avatar/1.jpg"/>
                            </ListItemAvatar>
                            <ListItemText primary="Board"/>
                        </ListItemButton>
                    </ListItem>
                </List>

            </Drawer>
            <Main open={open}>
                <DrawerHeader/>
                <Typography paragraph>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
                    tempor incididunt ut labore et dolore magna aliqua. Rhoncus dolor purus non
                    enim praesent elementum facilisis leo vel. Risus at ultrices mi tempus
                    imperdiet. Semper risus in hendrerit gravida rutrum quisque non tellus.
                    Convallis convallis tellus id interdum velit laoreet id donec ultrices.
                    Odio morbi quis commodo odio aenean sed adipiscing. Amet nisl suscipit
                    adipiscing bibendum est ultricies integer quis. Cursus euismod quis viverra
                    nibh cras. Metus vulputate eu scelerisque felis imperdiet proin fermentum
                    leo. Mauris commodo quis imperdiet massa tincidunt. Cras tincidunt lobortis
                    feugiat vivamus at augue. At augue eget arcu dictum varius duis at
                    consectetur lorem. Velit sed ullamcorper morbi tincidunt. Lorem donec massa
                    sapien faucibus et molestie ac.
                </Typography>
                <Typography paragraph>
                    Consequat mauris nunc congue nisi vitae suscipit. Fringilla est ullamcorper
                    eget nulla facilisi etiam dignissim diam. Pulvinar elementum integer enim
                    neque volutpat ac tincidunt. Ornare suspendisse sed nisi lacus sed viverra
                    tellus. Purus sit amet volutpat consequat mauris. Elementum eu facilisis
                    sed odio morbi. Euismod lacinia at quis risus sed vulputate odio. Morbi
                    tincidunt ornare massa eget egestas purus viverra accumsan in. In hendrerit
                    gravida rutrum quisque non tellus orci ac. Pellentesque nec nam aliquam sem
                    et tortor. Habitant morbi tristique senectus et. Adipiscing elit duis
                    tristique sollicitudin nibh sit. Ornare aenean euismod elementum nisi quis
                    eleifend. Commodo viverra maecenas accumsan lacus vel facilisis. Nulla
                    posuere sollicitudin aliquam ultrices sagittis orci a.
                </Typography>
            </Main>
        </Box>
    );
}