import * as React from 'react'
import {useEffect, useRef, useState} from 'react'
import EditorJS from '@editorjs/editorjs'

import './CardDescription.css'

import config from './editor-js-tools-config'
import api from "../../service/api";
import {useSelector} from "react-redux";

const CardDescription = () => {
    const editorContainerRef = useRef()
    const item = useSelector(sts => sts.dashboard.card)

    const [focused, setFocused] = useState(false)

    useEffect(() => {
        const editor = new EditorJS({
            logLevel: 'ERROR',
            holder: editorContainerRef.current,
            minHeight: '0px',
            placeholder: 'Type here...',
            tools: config,
            data: JSON.parse(item.description)
        })
        const editorElement = editorContainerRef.current
        const onFocusIn = ({currentTarget, relatedTarget}) => {
            if (currentTarget.contains(relatedTarget)) return
            setFocused(true);

        }
        const onFocusOut = ({currentTarget, relatedTarget}) => {
            if (currentTarget.contains(relatedTarget)) return
            setFocused(false);

            editor.save().then(out => {
                const data = JSON.stringify(out);
                console.log(JSON.parse(data))
                /*api.put('/api/v1/user/dashboard/card/details', {
                    id: item.id,
                    description: data
                }).then()*/
            })

        }
        editorElement.addEventListener("focusin", onFocusIn)
        editorElement.addEventListener("focusout", onFocusOut)
        return () => {
            editorElement.removeEventListener("focusin", onFocusIn)
            editorElement.removeEventListener("focusout", onFocusOut)
            editor.destroy()
        }


    }, [item])

    return <>
        <h5 style={{padding: 0, margin: 0}}>
            Description
        </h5>
        <div style={{
            marginLeft: -5,
            padding: focused ? 3 : 5,
            borderRadius: 5,
            border: focused ? '2px solid #1976d2' : 0,
        }} ref={editorContainerRef}/>
    </>
}
export default CardDescription