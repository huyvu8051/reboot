import Box from "@mui/material/Box";
import {
    Button,
    Card,
    CardActions,
    CardHeader,
    CardMedia,
    Chip,
    Divider,
    Grid,
    IconButton,
    List,
    ListItem,
    Paper
} from "@mui/material";
import {Add, AddCard, EditOutlined, Menu, MoreHoriz, Task} from "@mui/icons-material";
import {$error, $success} from "../../util/snackbar-utils";

const cards = Array.from(Array(20).keys()).map(e => ({
    id: e,
    title: 'Card title ' + e,
    cover_url: '',
}))
const list = Array.from(Array(10).keys()).map(e => ({
    id: e,
    title: 'List title ' + e,
    cover_url: '',
}))

export const Board = () => {

    return <>
        <Box

            sx={{
                // backgroundColor: 'red',
                height: '100%',
                display: 'flex',
                flexDirection: 'row',
                overflowX: 'auto',
                pt: 1
            }}
        >
            {
                list.map(l => (<Card id={'list-' + l.id} sx={{
                    minWidth: 300,
                    borderRadius: 0,
                    height: '100%',
                    ml: 2,
                    display: 'flex',
                    flexDirection: 'column'
                }}
                                     key={l.id}
                                     elevation={3}>
                    <CardHeader titleTypographyProps={{
                        fontSize: 'medium',
                        fontWeight: 'bold'
                    }}
                                sx={{p: 1.5, flex: 0}}
                                title={l.title}
                                action={<>
                                    <IconButton size='small'>
                                        <MoreHoriz fontSize='small'/>
                                    </IconButton>
                                </>}/>
                    <Paper
                        style={{flex: 1, overflow: 'auto', backgroundColor: 'transparent', boxShadow: 'none'}}>
                        <List sx={{p: 0}}>
                            {
                                cards.map(e => (<ListItem sx={{p: .5}} key={e.id}>
                                    <Card sx={{
                                        width: '100%',
                                        '--btn-edit-icon-color': 'rgba(255, 255, 255, 0)',
                                        '&:hover': {
                                            '--btn-edit-icon-color': 'black',
                                            '--btn-edit-bg-color': 'rgba(255, 255, 255, 0.2)'
                                        },
                                    }}
                                          elevation={2}>
                                        <IconButton size='small' sx={{
                                            borderRadius: 1,
                                            position: 'absolute',
                                            top: 6,
                                            right: 6,
                                            color: 'var(--btn-edit-icon-color)',
                                            backgroundColor: 'var(--btn-edit-bg-color)',
                                            '&:hover': {
                                                backgroundColor: 'rgba(255, 255, 255, 0.5)',
                                            },
                                        }}
                                                    onClick={(e) => $error('icon')}>
                                            <EditOutlined fontSize='small'/>
                                        </IconButton>
                                        <CardMedia
                                            onClick={() => $success('success')}
                                            sx={{height: 140}}
                                            image="https://mui.com/static/images/cards/contemplative-reptile.jpg"
                                            title="green iguana"
                                        />
                                        <CardHeader
                                            onClick={() => $success('success')}
                                            titleTypographyProps={{fontSize: 'small', fontWeight: 'bold'}}
                                            sx={{p: 1}}
                                            title={e.title}>
                                        </CardHeader>
                                        <CardActions onClick={() => $success('success')} disableSpacing sx={{}}>
                                            <Grid>
                                                <Chip
                                                    avatar={<Menu/>}
                                                    size='small'
                                                    label={'rtest'}
                                                >
                                                </Chip>
                                                <Chip
                                                    avatar={<Task/>}
                                                    size='small'
                                                    label={'rtest'}
                                                >
                                                </Chip>


                                            </Grid>
                                        </CardActions>
                                    </Card>
                                </ListItem>))
                            }
                        </List>
                    </Paper>
                    <CardActions sx={{display: 'flex', flex: 0}}>
                        <Button sx={{flex: 1, justifyContent: 'flex-start', color: 'rgba(0, 0, 0, 0.54)'}} size='small'
                                startIcon={<Add/>}>
                            {'Add a card'}
                        </Button>
                        <IconButton size='small'>
                            <AddCard fontSize='small'/>
                        </IconButton>
                    </CardActions>
                    <Divider/>
                </Card>))
            }

        </Box>
    </>
}