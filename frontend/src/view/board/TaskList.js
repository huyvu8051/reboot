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
    const cards = useSelector(sts => sts.dashboard.cards
        .filter(e => !e.isDeleted && e.liztId === item.id)
        .sort((e1, e2) => e1.ordinal - e2.ordinal))


    return (
        <Draggable key={item.id}
                   draggableId={'list' + item.id}
                   index={index}
        >
            {(dragProv, dragSnap) => (
                <Droppable droppableId={item.id + ''}
                           type='QUOTE'>
                    {(dropProv, dropSnap) => (
                        <Box
                            classes='box'
                            sx={{
                                // bgcolor: 'blue',
                                boxSizing: 'border-box',
                                margin: 0,
                            }}
                            elevation={0}>


                            <Card
                                ref={dragProv.innerRef}
                                {...dragProv.draggableProps}
                                {...dragProv.dragHandleProps}
                                className='list-content'
                                sx={{
                                    backgroundColor: 'rgb(235 236 240)',
                                    borderRadius: '3px',
                                    boxSizing: 'border-box',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    maxHeight: '100%',
                                    minWidth: '270px',
                                    mx: '4px',

                                }}

                                style={getStyle(dragProv, dragSnap)}
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
                                    ref={dropProv.innerRef}
                                    {...dropProv.droppableProps}
                                    sx={{
                                        py: 0,
                                        px: 1,
                                        // backgroundColor: 'red',
                                        overflowY: 'auto'
                                    }}
                                >

                                    {
                                        cards.map((e, index2) => (
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

                                    {dropProv.placeholder}
                                    <AddNewCard lId={item.id}/>
                                </List>

                            </Card>

                        </Box>
                    )}
                </Droppable>
            )}
        </Draggable>
    )
}