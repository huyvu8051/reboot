import EditorJS from '@editorjs/editorjs'
import {Button} from '@mui/material'
import * as React from 'react'
import {useEffect, useRef, useState} from 'react'
import {useSelector} from 'react-redux'
import api from '../../service/api'
import config from './editor-js-tools-config'

const CardActivity = () => {
    const cId = useSelector(sts => sts.dashboard.card.id)
    const editorContainerRef = useRef()
    const description = ''

    const [focused, setFocused] = useState(false)

    const [editor, setEditor] = useState()


    useEffect(() => {
        const editorInstance = new EditorJS({
            logLevel: 'ERROR',
            holder: editorContainerRef.current,
            minHeight: '0px',
            placeholder: 'Type here...',
            tools: config
        })
        setEditor(editorInstance)

        const editorElement = editorContainerRef.current
        const onFocusIn = ({currentTarget, relatedTarget}) => {
            if (currentTarget.contains(relatedTarget)) return
            setFocused(true)

        }
        const onFocusOut = ({currentTarget, relatedTarget}) => {
            if (currentTarget.contains(relatedTarget)){}
            // setFocused(false);
        }

        editorElement.addEventListener('focusin', onFocusIn)
        editorElement.addEventListener('focusout', onFocusOut)
        return () => {
            editorElement.removeEventListener('focusin', onFocusIn)
            editorElement.removeEventListener('focusout', onFocusOut)
            editorInstance.destroy()
        }


    }, [])

    // handle on socket update
    useEffect(() => {
        if (editor && editor.isReady && !focused && description) {
            editor.isReady.then(() => {
                editor.render(JSON.parse(description))
            })
        }
    }, [description, editor, focused])

    function handleSave() {
        editor.save().then(out => {
            api.put('/api/v1/user/dashboard/card/activity', {
                id: cId,
                description: JSON.stringify(out)
            }).then()
        })

        setFocused(false)
    }

    function handleCancel() {
        if (editor && editor.isReady && description) {
            editor.isReady.then(() => {
                editor.render(JSON.parse(description))
            })
        }
        setFocused(false)
    }

    return <>
        <h5 style={{padding: 0, margin: 0}}>
            Activity
        </h5>
        <div style={{
            marginLeft: -5,
            padding: focused ? 3 : 5,
            borderRadius: 5,
            border: focused ? '2px solid #1976d2' : 0
        }} ref={editorContainerRef}/>

        {
            focused && <>
                <Button onClick={handleSave}>Save</Button>
                <Button onClick={handleCancel}>Cancel</Button>
            </>
        }
    </>
}

export default CardActivity