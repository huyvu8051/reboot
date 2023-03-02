import Box from '@mui/material/Box'
import {DragDropContext, Droppable} from 'react-beautiful-dnd'
import TaskList from './TaskList'
import AddNewList from "./AddNewList";
import {useDispatch, useSelector} from "react-redux";
import api from "../../service/api";
import {useCallback} from "react";
import {updateLiztOrdinal} from "../workspace/dashboard-slice";

export const Board = () => {
    const lists = useSelector(sts => sts.dashboard.lizts);
    const dispatch = useDispatch()

    const onDragEnd = useCallback((result) => {
        if (!result.destination) return
        // console.log(result)

        // change col ordinal
        if (result.destination.droppableId === 'board') {
            const srcEle = lists[result.source.index]
            const desEle = lists[result.destination.index]

            dispatch(updateLiztOrdinal(result))

            api.put('/api/v1/user/list', null, {
                params: {
                    lId: srcEle.id,
                    desId: desEle.id
                }
            }).then()

            return
        }

        const srcLs = lists.find(e => e.id === result.source.droppableId);
        const srcEle = srcLs.cards[result.source.index];
        srcLs.cards.splice(result.source.index, 1)
        const desLs = lists.find(e => e.id === result.destination.droppableId);
        // console.log('desLs', desLs)
        desLs.cards.splice(result.destination.index, 0, srcEle)

        // setLists(lists)

    })
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
                            overflow: 'auto',
                            p:'4px'
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