import Box from '@mui/material/Box'
import {useCallback} from 'react'
import {DragDropContext, Droppable} from 'react-beautiful-dnd'
import {useDispatch, useSelector} from 'react-redux'
import api from '../../service/api'
import {getMiddleVal} from '../../util/dnd-utils'
import {updateCard, updateLizt} from '../workspace/dashboard-slice'
import AddNewList from './AddNewList'
import TaskList from './TaskList'


export const Board = () => {
    const lists = useSelector(sts => sts.dashboard.lizts
        .filter(e => !e.isDeleted)
        .sort((e1, e2) => e1.ordinal - e2.ordinal))

    const cards = useSelector(sts => sts.dashboard.cards
        .filter(e => !e.isDeleted)
        .sort((e1, e2) => e1.ordinal - e2.ordinal))

    const dispatch = useDispatch()

    const onDragEnd = useCallback((result) => {
        if (!result.destination) return

        // console.log(lists, result)

        if (result.destination.droppableId === 'board') {
            if (result.source.index === result.destination.index) return

            const src = lists[result.source.index]
            const des = lists[result.destination.index]

            let temp
            if (src.ordinal < des.ordinal) {
                temp = lists[result.destination.index + 1] || {ordinal: lists[lists.length - 1].ordinal + 50}
            } else {
                temp = lists[result.destination.index - 1] || {ordinal: lists[0].ordinal - 50}
            }

            const middleVal = getMiddleVal(des.ordinal, temp.ordinal)

            dispatch(updateLizt({
                ...src,
                ordinal: middleVal
            }))

            api.put('/api/v1/user/list', null, {
                params: {
                    lId: src.id,
                    ordinal: middleVal
                }
            }).then()

            return
        }

        if (result.source.droppableId === result.destination.droppableId) {
            const cardsLs = cards.filter(e => e.liztId === parseInt(result.destination.droppableId))

            const src = cardsLs[result.source.index]
            const des = cardsLs[result.destination.index]

            let temp
            if (src.ordinal < des.ordinal) {
                temp = cardsLs[result.destination.index + 1] || {ordinal: cardsLs[cardsLs.length - 1].ordinal + 50}
            } else {
                temp = cardsLs[result.destination.index - 1] || {ordinal: cardsLs[0].ordinal - 50}
            }

            const middleVal = getMiddleVal(des.ordinal, temp.ordinal)
            dispatch(updateCard({
                ...src,
                ordinal: middleVal
            }))

            api.put('/api/v1/user/card', null, {
                params: {
                    cId: src.id,
                    ordinal: middleVal
                }
            }).then()

            return
        }

        const srcCardLs = cards.filter(e => e.liztId === parseInt(result.source.droppableId))
        const desCardLs = cards.filter(e => e.liztId === parseInt(result.destination.droppableId))

        const src = srcCardLs[result.source.index]
        let des = desCardLs[result.destination.index]

        if (desCardLs.length === 0) {
            dispatch(updateCard({
                ...src,
                ordinal: 0,
                liztId: parseInt(result.destination.droppableId)
            }))

            api.put('/api/v1/user/card', null, {
                params: {
                    cId: src.id,
                    ordinal: 0,
                    lId: parseInt(result.destination.droppableId)
                }
            }).then()

            return
        }


        let temp
        if (result.destination.index >= desCardLs.length) {
            des = desCardLs[desCardLs.length - 1]
            temp = desCardLs[result.destination.index + 1] || {ordinal: desCardLs[desCardLs.length - 1].ordinal + 50}
        } else {
            temp = desCardLs[result.destination.index - 1] || {ordinal: desCardLs[0].ordinal - 50}
        }

        const middleVal = getMiddleVal(des.ordinal, temp.ordinal)
        dispatch(updateCard({
            ...src,
            ordinal: middleVal,
            liztId: parseInt(result.destination.droppableId)
        }))

        api.put('/api/v1/user/card', null, {
            params: {
                cId: src.id,
                ordinal: middleVal,
                lId: parseInt(result.destination.droppableId)
            }
        }).then()


    }, [lists, dispatch, cards])
    return <>
        <DragDropContext onDragEnd={onDragEnd}>
            <Droppable droppableId="board"
                       type="COLUMN"
                       direction="horizontal"
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