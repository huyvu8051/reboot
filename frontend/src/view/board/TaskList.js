import {Box, Card} from "@mui/material";
import {Draggable} from "react-beautiful-dnd";
import CardLs from "./CardItemContent";

const getItemStyle = (isDragging, draggableStyle) => ({
    background: isDragging ? "lightgreen" : "#EBECF0",
    ...draggableStyle
});

export default ({item, index}) => {
    return (
        <Draggable key={item.id}
                   draggableId={'list' + item.id}
                   index={index}
        >
            {(provided, snapshot) => (
                <Box ref={provided.innerRef}
                      {...provided.draggableProps}
                      {...provided.dragHandleProps}

                      elevation={0}

                      classes='list-wrapper'
                      sx={{
                          boxSizing: 'border-box',
                          display: 'inline-block',
                          height: '100%',
                          margin: '0 4px',
                          verticalAlign: 'top',
                          whiteSpace: 'nowrap',
                          minWidth: '272px',
                      }}
                      elevation={0}>

                    <CardLs ls={item}/>


                </Box>
            )}
        </Draggable>
    )
}