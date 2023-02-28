import {Draggable, Droppable} from "react-beautiful-dnd";
import List from "@mui/material/List";
import CardItem from "./CardItem";
import {CardContent} from "@mui/material";

const getListStyle = isDraggingOver => ({
    background: isDraggingOver ? 'lightblue' : 'transparent',
    // padding: grid,
    // width: 250
})

export default ({ls}) => {
    return (
        <CardContent
            sx={{
                overflow: 'auto',
                backgroundColor: 'transparent',
                boxShadow: 'none',
                p:1

            }}>
            <Droppable droppableId={ls.id}
                       type={'QUOTE'}>
                {(provided, snapshot) => (
                    <List sx={{p: 0}}
                          {...provided.droppableProps}
                          ref={provided.innerRef}>
                        {
                            ls.cards.map((e, index2) => (
                                <Draggable key={e.id}
                                           draggableId={'item' + e.id}
                                           index={index2}
                                >
                                    {(provided2, snapshot2) => (
                                        <CardItem item={e} provided={provided2} snapshot={snapshot2}/>
                                    )}
                                </Draggable>
                            ))
                        }
                        {provided.placeholder}
                    </List>
                )}
            </Droppable>
        </CardContent>
    )
}