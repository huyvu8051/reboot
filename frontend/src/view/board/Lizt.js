import {Button, Card, CardActions, CardHeader, Divider} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {Add, AddCard, MoreHoriz} from "@mui/icons-material";
import {Draggable} from "react-beautiful-dnd";
import CardLs from "./CardLs";

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
                <Card id={'list-' + item.id}

                      ref={provided.innerRef}
                      {...provided.draggableProps}
                      {...provided.dragHandleProps}
                      style={getItemStyle(
                          snapshot.isDragging,
                          provided.draggableProps.style
                      )}
                      elevation={0}
                      sx={{
                          flexGrow: 1,/* Allow the columns to grow up to the maximum height of the container */
                          maxHeight: '100%', /* Set a maximum height for the columns relative to the container */
                          minWidth: '272px',
                          borderRadius: 1,
                          ml: 2,
                          display: 'flex',
                          flexDirection: 'column',
                      }}


                      elevation={0}>
                    <CardHeader

                        titleTypographyProps={{
                            fontSize: 'small',
                            fontWeight: 'bold'
                        }}
                        sx={{p: 1.5, flex: 0}}
                        title={item.title}
                        action={<>
                            <IconButton size='small'>
                                <MoreHoriz fontSize='small'/>
                            </IconButton>
                        </>}/>
                    <CardLs ls={item}/>
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


                </Card>
            )}
        </Draggable>
    )
}