import Box from '@mui/material/Box'
import {DragDropContext, Droppable} from 'react-beautiful-dnd'
import TaskList from './TaskList'
import AddNewList from "./AddNewList";
import {useDispatch, useSelector} from "react-redux";
import {useCallback} from "react";
import {updateLizt} from "../workspace/dashboard-slice";

const listSelector = sts => sts.dashboard.lizts
    .filter(e => !e.isDeleted)
    .sort((e1, e2) => e1.ordinal - e2.ordinal)

const getMiddleVal = (v1, v2) => {
    const diff = Math.abs(v1 - v2);
    const pow = Math.pow(10, Math.floor(Math.log10(diff)));

    const firstDigit = diff / pow;
    const rounded = Math.round(firstDigit) * pow;

    if (v2 > v1) {
        return v2 - (rounded / 2);
    }

    return v1 - (rounded / 2);
}

export const Board = () => {
    const lists = useSelector(listSelector);
    const dispatch = useDispatch()

    const onDragEnd = useCallback((result) => {
        if (!result.destination) return
        console.log(lists, result)

        if (result.destination.droppableId === 'board') {
            if (result.source.index === result.destination.index) return

            const src = lists[result.source.index]
            const des = lists[result.destination.index]

            console.log(src, des)

            let temp;
            if (src.ordinal < des.ordinal) {
                temp = lists[result.destination.index + 1] || {ordinal: lists[lists.length - 1].ordinal + 50}
            } else {
                temp = lists[result.destination.index - 1] || {ordinal: lists[0].ordinal - 50}
            }

            const middleVal = getMiddleVal(des.ordinal, temp.ordinal);

            dispatch(updateLizt({
                ...src,
                ordinal: middleVal
            }))


            return
        }


    }, [lists])
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