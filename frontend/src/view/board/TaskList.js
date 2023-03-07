import {Box, Card, CardHeader} from "@mui/material";
import {Draggable, Droppable} from "react-beautiful-dnd";
import IconButton from "@mui/material/IconButton";
import {MoreHoriz} from "@mui/icons-material";
import List from "@mui/material/List";
import CardItem from "./CardItem";
import {useSelector} from "react-redux";
import AddNewCard from "./AddNewCard";

const getStyle = (prov, snap) => {
    // console.log(prov, snap)
    return {
        ...prov.draggableProps.style,
        ...snap.isDragging && !snap.isDropAnimating && {
            // transform: ' rotate(5deg) ' + prov.draggableProps.style.transform
        }
    }
}

export default ({item, index}) => {
    const cards = useSelector(sts => sts.dashboard.cards || [])

    return (
        <Draggable key={item.id}
                   draggableId={'list' + item.id}
                   index={index}
        >
            {(provided, snapshot) => (
                <Droppable droppableId={item.id + ''}
                           type='QUOTE'>
                    {(provided2, snapshot2) => (
                        <Box
                            ref={provided2.innerRef}
                            {...provided2.droppableProps}

                            classes='list-wrapper'
                            sx={{
                                // bgcolor: 'blue',
                                boxSizing: 'border-box',
                                margin: 0,
                            }}
                            elevation={0}>


                            <Card
                                ref={provided.innerRef}
                                {...provided.draggableProps}
                                {...provided.dragHandleProps}

                                className='list-content'
                                sx={{
                                    backgroundColor: 'rgb(235 236 240)',
                                    borderRadius: '3px',
                                    boxSizing: 'border-box',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    maxHeight: '100%',
                                    minWidth: '270px',
                                    mx: '4px'

                                }}

                                style={getStyle(provided, snapshot)}
                                elevation={0}>
                                <CardHeader
                                    sx={{
                                        p: 1
                                    }}
                                    titleTypographyProps={{
                                        fontSize: 'small',
                                        fontWeight: 'bold'
                                    }}
                                    title={item.title}
                                    action={(
                                        <IconButton size='small' sx={{borderRadius: 1, mr: .5}}>
                                            <MoreHoriz fontSize='small'/>
                                        </IconButton>
                                    )}
                                />
                                <List
                                    sx={{
                                        py: 0,
                                        px: 1,
                                        // backgroundColor: 'red',
                                        overflowY: 'auto'
                                    }}
                                >

                                    {
                                        cards.filter(e => e.liztId === item.id)
                                            .sort((e1, e2) => e1.ordinal - e2.ordinal)
                                            .map((e, index2) => (
                                                <Draggable
                                                    key={e.id}
                                                    draggableId={'item' + e.id}
                                                    index={index2}
                                                >
                                                    {(provided3, snapshot3) => (
                                                        <CardItem item={e} provided={provided3}
                                                                  snapshot={snapshot3}/>
                                                    )}
                                                </Draggable>
                                            ))
                                    }

                                    {provided2.placeholder}

                                </List>
                                <AddNewCard lId={item.id}/>
                            </Card>


                        </Box>
                    )}
                </Droppable>
            )}
        </Draggable>
    )
}