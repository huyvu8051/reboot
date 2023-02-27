import Box from "@mui/material/Box";
import {DragDropContext, Draggable, Droppable} from "react-beautiful-dnd";
import {Button, Card, CardActions, CardHeader, CardMedia, Divider, Paper} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Add, AddCard, EditOutlined, MoreHoriz} from "@mui/icons-material";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import {$error, $success} from "../../util/snackbar-utils";

const cards = Array.from(Array(5).keys()).map(e => ({
    id: e,
    title: 'Card title ' + e,
    cover_url: '',
}))
const list = Array.from(Array(5).keys()).map(e => ({
    id: e,
    title: 'List title ' + e,
    cover_url: '',
}))
const grid = 8;

const getListStyle = isDraggingOver => ({
    background: isDraggingOver ? "lightblue" : "lightgrey",
    // padding: grid,
    // width: 250
});

const getItemStyle = (isDragging, draggableStyle) => ({
    // some basic styles to make the items look a bit nicer
    userSelect: "none",
    // change background colour if dragging
    background: isDragging ? "lightgreen" : "white",

    // styles we need to apply on draggables
    ...draggableStyle
});

export const Board = () => {
    const onDragEnd = (result) => {
        console.log(result)
    }

    console.log('test')
    return <>
        <DragDropContext onDragEnd={onDragEnd}>

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
                            list.map((l, index) => (
                                <Droppable droppableId={'droppable' + l.id} key={l.id + 'drop'} >
                                    {(provided, snapshot) => (
                                        <Card id={'list-' + l.id}
                                              {...provided.droppableProps}
                                              ref={provided.innerRef}
                                              style={getListStyle(snapshot.isDraggingOver)}
                                              sx={{
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
                                                style={{
                                                    flex: 1,
                                                    overflow: 'auto',
                                                    backgroundColor: 'transparent',
                                                    boxShadow: 'none'
                                                }}>
                                                <List sx={{p: 0}}>
                                                    {
                                                        cards.map((e, index) => (
                                                            <Draggable key={e.id} draggableId={e.id + 'drag-id'} index={index}
                                                                       open={true}>
                                                                {(provided, snapshot) => (
                                                                    <ListItem ref={provided.innerRef}
                                                                              {...provided.draggableProps}
                                                                              {...provided.dragHandleProps}
                                                                              style={getItemStyle(
                                                                                  snapshot.isDragging,
                                                                                  provided.draggableProps.style
                                                                              )}
                                                                              sx={{p: .5}}
                                                                              key={e.id}>
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
                                                                                titleTypographyProps={{
                                                                                    fontSize: 'small',
                                                                                    fontWeight: 'bold'
                                                                                }}
                                                                                sx={{p: 1}}
                                                                                title={e.title}>
                                                                            </CardHeader>
                                                                            <CardActions onClick={() => $success('success')}
                                                                                         disableSpacing
                                                                                         sx={{}}>
                                                                                {/*<Grid>*/}
                                                                                {/*    <Chip*/}
                                                                                {/*        avatar={<Menu/>}*/}
                                                                                {/*        size='small'*/}
                                                                                {/*        label={'rtest'}*/}
                                                                                {/*    >*/}
                                                                                {/*    </Chip>*/}
                                                                                {/*    <Chip*/}
                                                                                {/*        avatar={<Task/>}*/}
                                                                                {/*        size='small'*/}
                                                                                {/*        label={'rtest'}*/}
                                                                                {/*    >*/}
                                                                                {/*    </Chip>*/}


                                                                                {/*</Grid>*/}
                                                                            </CardActions>
                                                                        </Card>
                                                                    </ListItem>
                                                                )}
                                                            </Draggable>
                                                        ))
                                                    }
                                                </List>
                                            </Paper>
                                            <CardActions sx={{display: 'flex', flex: 0}}>
                                                <Button
                                                    sx={{
                                                        flex: 1,
                                                        justifyContent: 'flex-start',
                                                        color: 'rgba(0, 0, 0, 0.54)'
                                                    }}
                                                    size='small'
                                                    startIcon={<Add/>}>
                                                    {'Add a card'}
                                                </Button>
                                                <IconButton size='small'>
                                                    <AddCard fontSize='small'/>
                                                </IconButton>
                                            </CardActions>
                                            <Divider/>

                                            {provided.placeholder}
                                        </Card>
                                    )}
                                </Droppable>

                            ))
                        }

                    </Box>

        </DragDropContext>
    </>
}