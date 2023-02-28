import {Box, Button, Card, CardActions, CardHeader} from "@mui/material";
import {Draggable, Droppable} from "react-beautiful-dnd";
import IconButton from "@mui/material/IconButton";
import {Add, AddCard, MoreHoriz} from "@mui/icons-material";
import List from "@mui/material/List";
import CardItem from "./CardItem";

const getStyle = (prov, snap) => {

    return {
        ...prov.draggableProps.style,
        ...snap.isDragging && {
            transform: ' rotate(5deg) ' + prov.draggableProps.style.transform
        }
    }
}

export default ({item, index}) => {
    return (
        <Draggable key={item.id}
                   draggableId={'list' + item.id}
                   index={index}
        >
            {(provided, snapshot) => (
                <Droppable droppableId={item.id}
                           type={'QUOTE'}>
                    {(provided2, snapshot2) => (
                        <Box
                            ref={provided2.innerRef}
                            {...provided2.droppableProps}

                            classes='list-wrapper'
                            sx={{
                                // bgcolor: 'blue',
                                boxSizing: 'border-box',
                                margin: '0 4px',
                            }}
                            elevation={0}>


                            <Card
                                ref={provided.innerRef}
                                {...provided.draggableProps}
                                {...provided.dragHandleProps}

                                className='list-content'
                                sx={{
                                    backgroundColor: 'var(--ds-background-accent-gray-subtlest,#ebecf0)',
                                    borderRadius: '3px',
                                    boxSizing: 'border-box',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    maxHeight: '100%',
                                    minWidth: '270px',

                                }}

                                style={getStyle(provided, snapshot)}
                                elevation={0}>
                                <CardHeader
                                    titleTypographyProps={{
                                        fontSize: 'small',
                                        fontWeight: 'bold'
                                    }}
                                    title={item.title}
                                    action={<>
                                        <IconButton size='small'>
                                            <MoreHoriz fontSize='small'/>
                                        </IconButton>
                                    </>}/>
                                <List
                                    sx={{
                                        p: 1,
                                        backgroundColor: 'red',
                                        overflowX: 'hidden',
                                        overflowY: 'auto'
                                    }}
                                >

                                    {
                                        item.cards.map((e, index2) => (
                                            <Draggable
                                                key={e.id}
                                                draggableId={'item' + e.id}
                                                index={index2}
                                            >
                                                {(provided3, snapshot3) => (
                                                    <CardItem item={e} provided={provided3} snapshot={snapshot3}/>
                                                )}
                                            </Draggable>
                                        ))
                                    }
                                    {provided2.placeholder}

                                </List>
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
                            </Card>


                        </Box>
                    )}
                </Droppable>
            )}
        </Draggable>
    )
}