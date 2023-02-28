import Box from '@mui/material/Box'
import {DragDropContext, Droppable} from 'react-beautiful-dnd'
import TaskList from './TaskList'
import {useEffect, useState} from "react";
import {Button, Card, CardActions, IconButton, TextField} from "@mui/material";
import {Add, Clear} from "@mui/icons-material";
import AddNewList from "./AddNewList";

function cgen(id) {
    return Array.from(Array(Math.floor(Math.random() * 10)).keys()).map(e => ({
        id: id + '-' + e,
        title: 'Card title ' + e,
        cover_url: '',
    }))
}

const list2 = Array.from(Array(7).keys()).map(e => ({
    id: 'ls' + e,
    title: 'List title ' + e,
    cover_url: '',
    cards: cgen(e)
}))

const getList = () => {
    return Promise.resolve(list2);
}

export const Board = () => {


    const [lists, setLists] = useState([]);

    const onDragEnd = (result) => {
        if (!result.destination) return
        // console.log(result)

        // change col ordinal
        if (result.destination.droppableId === 'board') {
            const srcEle = lists[result.source.index]
            lists.splice(result.source.index, 1)
            lists.splice(result.destination.index, 0, srcEle)
            setLists(lists)
            return
        }

        const srcLs = lists.find(e => e.id === result.source.droppableId);
        const srcEle = srcLs.cards[result.source.index];
        srcLs.cards.splice(result.source.index, 1)
        const desLs = lists.find(e => e.id === result.destination.droppableId);
        // console.log('desLs', desLs)
        desLs.cards.splice(result.destination.index, 0, srcEle)

        setLists(lists)

    }

    useEffect(() => {
        getList()
            .then(setLists)
        console.log('fetch')
    }, [])

    return <>
        <DragDropContext onDragEnd={onDragEnd}>
            <Droppable droppableId='board'
                       type="COLUMN"
                       direction='horizontal'
            >
                {(provided, snapshot) => (
                    <Box
                        id={'test'}
                        ref={provided.innerRef}
                        {...provided.droppableProps}

                        sx={{
                            marginBottom: '8px',
                            paddingBottom: '8px',
                            display: 'flex',
                            flexDirection: 'row',
                            height: '100%',
                            // backgroundColor: 'green',
                            overflowX: 'auto',
                            overflowY: 'hidden'
                        }}

                    >
                        {
                            lists.map((l, index) => (
                                <TaskList
                                    item={l}
                                    index={index}
                                    key={l.id}
                                />
                            ))
                        }
                        {provided.placeholder}
                        <AddNewList/>
                    </Box>
                )}
            </Droppable>
        </DragDropContext>
    </>
}