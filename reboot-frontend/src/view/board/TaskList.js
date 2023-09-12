import {Box, Card} from '@mui/material'
import List from '@mui/material/List'
import {Draggable, Droppable} from 'react-beautiful-dnd'
import {useSelector} from 'react-redux'
import AddNewCard from './AddNewCard'
import CardItem from './CardItem'
import TaskListHeader from './TaskListHeader'

const getStyle = (prov, snap) => {
    // console.log(prov, snap)
    return {
        ...prov.draggableProps.style,
        ...snap.isDragging && !snap.isDropAnimating && {
            // transform: ' rotate(5deg) ' + prov.draggableProps.style.transform
        }
    }
}

const TaskList = ({item, index}) => {
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
                           type="QUOTE">
                    {(dropProv, dropSnap) => (
                        <Box
                            classes="box"
                            sx={{
                                // bgcolor: 'blue',
                                boxSizing: 'border-box',
                                margin: 0
                            }}
                            elevation={0}>


                            <Card
                                ref={dragProv.innerRef}
                                {...dragProv.draggableProps}
                                {...dragProv.dragHandleProps}
                                className="list-content"
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

                                style={getStyle(dragProv, dragSnap)}
                                elevation={0}>
                                <TaskListHeader item={item}/>
                                <List
                                    className="List"
                                    ref={dropProv.innerRef}
                                    {...dropProv.droppableProps}
                                    sx={{
                                        py: 0,
                                        px: 1,
                                        // backgroundColor: 'red',
                                        overflowY: 'auto',
                                        minHeight: 10
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
export default TaskList