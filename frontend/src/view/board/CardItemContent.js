import {Draggable, Droppable} from "react-beautiful-dnd";
import List from "@mui/material/List";
import CardItem from "./CardItem";
import IconButton from "@mui/material/IconButton";
import {Add, AddCard, MoreHoriz} from "@mui/icons-material";
import {Button, Card, CardActions, CardHeader} from "@mui/material";


export default ({ls}) => {
    return (
        <Droppable droppableId={ls.id}
                   type={'QUOTE'}>
            {(provided, snapshot) => (
                <Card className='list-content'
                      sx={{
                          backgroundColor: 'var(--ds-background-accent-gray-subtlest,#ebecf0)',
                          borderRadius: '3px',
                          boxSizing: 'border-box',
                          display: 'flex',
                          flexDirection: 'column',
                          position: 'relative',
                          whiteSpace: 'normal',
                          maxHeight: '100%',
                          width: '100%!important',
                      }}>
                    <CardHeader

                        titleTypographyProps={{
                            fontSize: 'small',
                            fontWeight: 'bold'
                        }}
                        title={'chung ta cua hien tai'}
                        action={<>
                            <IconButton size='small'>
                                <MoreHoriz fontSize='small'/>
                            </IconButton>
                        </>}/>
                    <List sx={{
                        p: 1,
                        backgroundColor: 'red',
                        overflowY: 'auto'
                    }}
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
            )}
        </Droppable>

    )
}