import Box from '@mui/material/Box'
import {DragDropContext, Droppable} from 'react-beautiful-dnd'
import TaskList from './TaskList'
import AddNewList from "./AddNewList";
import {useDispatch, useSelector} from "react-redux";
import api from "../../service/api";
import {useCallback} from "react";
import {updateCardOrdinal, updateLiztOrdinal} from "../workspace/dashboard-slice";

export const Board = () => {
    const lists = useSelector(sts => sts.dashboard.lizts);
    const cards = useSelector(sts => sts.dashboard.cards);
    const dispatch = useDispatch()

    const onDragEnd = useCallback((result) => {
        if (!result.destination) return
        // console.log(result)

        // change col ordinal
        if (result.destination.droppableId === 'board') {
            if (result.source.index === result.destination.index) return

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

        dispatch(updateCardOrdinal(result))




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
                            p: '4px'
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